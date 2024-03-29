package com.open.quester.common

import org.powbot.api.Tile
import org.powbot.api.rt4.Chat
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Players
import quests.common.base.BaseQuestStep
import quests.common.helpers.CombatHelper
import quests.common.models.QuestInformation
import java.util.concurrent.Callable

class WalkToExactTile(
    val tile: Tile,
    val stepName: String,
    val callable: Callable<Boolean>,
    val information: QuestInformation,
    val conversation: Array<String> = arrayOf()
) : BaseQuestStep() {
    override fun shouldExecute(): Boolean {
        return callable.call()
    }

    override fun run() {
        if (Chat.chatting()) {
            Chat.completeChat()
        }
        Movement.builder(tile)
            .setRunMin(10)
            .setRunMax(30)
            .setWalkUntil {
                if (CombatHelper.shouldEat(*information.foodName)) {
                    CombatHelper.eatFood(*information.foodName)
                }
                tile == Players.local().tile()
            }
            .move()
    }

    override fun stepName(): String {
        return stepName
    }
}