package com.open.quester.common

import helpers.extentions.Conditions
import org.powbot.api.Condition
import org.powbot.api.rt4.Bank
import org.powbot.api.rt4.Chat
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Item
import quests.common.base.BaseQuestStep
import quests.common.helpers.InteractionsHelper
import java.util.concurrent.Callable

class CombineItemStep(
    private val firstItemName: String,
    private val secondItemName: String,
    private val stepText: String,
    private val shouldExecute: Callable<Boolean>,
    val animation: Boolean = false,
    val chatOptions: Array<String> = arrayOf(),
) : BaseQuestStep() {


    override fun shouldExecute(): Boolean {
        return shouldExecute.call()
    }

    override fun run() {
        if (Bank.opened() && !Bank.close()) {
            return
        }

        val firstItem = Inventory.stream().name(firstItemName).first()
        val secondItem = Inventory.stream().name(secondItemName).first()

        if (firstItem == Item.Nil ||
            secondItem == Item.Nil
        ) {
            return
        }

        val itemsCount = Inventory.stream().name(firstItemName, secondItemName).count(true)

        if (InteractionsHelper.useItemOn(firstItem, secondItem)) {
            if (animation) {
                Condition.wait(Conditions.waitUntilNonIdleAnimation())
                Condition.wait(Conditions.waitUntilIdleAnimation())
            }
            Condition.wait {
                val newItemCount = Inventory.stream().name(firstItemName, secondItemName).count(true)
                Chat.chatting() || itemsCount != newItemCount
            }
            if (Chat.chatting()) {
                Chat.completeChat(*chatOptions)
            }
        }
    }

    override fun stepName(): String {
        return stepText
    }
}