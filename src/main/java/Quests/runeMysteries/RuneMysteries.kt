package quests.runeMysteries

import quests.common.CommonMethods
import quests.common.SimpleConversationStep
import quests.common.base.BaseQuest
import quests.common.base.BaseQuestStep
import quests.common.models.ItemRequirementCondition
import quests.common.models.QuestInformation
import quests.common.models.QuestRequirements
import quests.runeMysteries.RuneMysteriesConstants.CONVERSATION_AUBURY
import quests.runeMysteries.RuneMysteriesConstants.CONVERSATION_DUKE_START
import quests.runeMysteries.RuneMysteriesConstants.CONVERSATION_SEDRIDOR
import quests.runeMysteries.RuneMysteriesConstants.NAME_AUBURY
import quests.runeMysteries.RuneMysteriesConstants.NAME_DUKE_HORACIO
import quests.runeMysteries.RuneMysteriesConstants.NAME_SEDRIDOR
import quests.runeMysteries.RuneMysteriesConstants.TILE_AUBURY
import quests.runeMysteries.RuneMysteriesConstants.TILE_DUKE_HORACIO
import quests.runeMysteries.RuneMysteriesConstants.TILE_SEDRIDOR

class RuneMysteries(information: QuestInformation) : BaseQuest(information) {
    private val startQuestStep = SimpleConversationStep(
        NAME_DUKE_HORACIO, TILE_DUKE_HORACIO, CONVERSATION_DUKE_START,
        "Talking to Duke.", information
    )
    private val talkToSedridor = SimpleConversationStep(
        NAME_SEDRIDOR, TILE_SEDRIDOR, CONVERSATION_SEDRIDOR,
        "Talking to Sedridor", information
    )
    private val talkToAubury = SimpleConversationStep(
        NAME_AUBURY, TILE_AUBURY, CONVERSATION_AUBURY,
        "Talking to Aubury", information
    )
    private val talkToSedridorAgain = SimpleConversationStep(
        NAME_SEDRIDOR, TILE_SEDRIDOR, arrayOf(),
        "Finishing Quest", information
    )

    override fun addRequirements(): QuestRequirements {
        val itemRequirements: List<ItemRequirementCondition> = ArrayList()
        return QuestRequirements(itemRequirements, listOf())
    }

    override fun getQuestStep(stepPosition: Int): BaseQuestStep? {
        when (stepPosition) {
            0 -> return startQuestStep
            1, 2 -> return talkToSedridor
            3, 4 -> return talkToAubury
            5 -> return talkToSedridorAgain
            6 -> {
                information.complete = true
                CommonMethods.closeQuestComplete()
            }
        }
        return null
    }
}