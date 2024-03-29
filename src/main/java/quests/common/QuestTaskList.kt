package quests.common

import quests.common.base.BaseQuestStep


class QuestTaskList(vararg questList: BaseQuestStep) {
    private val _quests: Array<BaseQuestStep> = arrayOf(*questList)

    fun processStep(): BaseQuestStep? {
        return _quests.firstOrNull { obj: BaseQuestStep -> obj.shouldExecute() }
    }
}