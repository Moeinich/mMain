package com.open.quester.common

import org.powbot.api.Area
import org.powbot.api.Tile
import org.powbot.api.rt4.Bank
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Players
import quests.common.base.BaseQuestStep
import quests.common.helpers.CombatHelper
import quests.common.models.QuestInformation
import java.util.concurrent.Callable

class WalkToArea(val area: Area, val tile: Tile, val stepName: String, val callable: Callable<Boolean>, val information: QuestInformation, extraFood:Array<String> = arrayOf()
) : BaseQuestStep() {

    private val food = arrayOf(*extraFood, *information.foodName)

    override fun shouldExecute(): Boolean {
        return callable.call()
    }

    override fun run() {
        if (Bank.opened()) {
            Bank.close()
        }

        Movement.builder(tile)
            .setRunMin(10)
            .setRunMax(30)
            .setWalkUntil {
                if (CombatHelper.shouldEat(*food)) {
                    CombatHelper.eatFood(*food)
                }
                area.contains(Players.local()) }
            .move()
    }

    override fun stepName(): String {
        return stepName
    }

}