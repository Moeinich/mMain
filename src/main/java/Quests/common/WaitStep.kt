package com.open.quester.common

import org.powbot.api.Condition
import org.powbot.api.Random
import quests.common.base.BaseQuestStep

class WaitStep(val condition : () -> Boolean?, val stepText: String) : BaseQuestStep() {
    override fun shouldExecute(): Boolean {
        return condition.invoke() ?: true
    }

    override fun run() {
        Condition.sleep(Random.nextInt(100,150))
    }

    override fun stepName(): String {
        return stepText
    }
}