package quests.NaturalHistory

import quests.NaturalHistory.NaturalHistoryConstants.ANSWER_CAMEL
import quests.NaturalHistory.NaturalHistoryConstants.ANSWER_DRAGON
import quests.NaturalHistory.NaturalHistoryConstants.ANSWER_KALPHITE_QUEEN
import quests.NaturalHistory.NaturalHistoryConstants.ANSWER_LEECH
import quests.NaturalHistory.NaturalHistoryConstants.ANSWER_LIZARD
import quests.NaturalHistory.NaturalHistoryConstants.ANSWER_MOLE
import quests.NaturalHistory.NaturalHistoryConstants.ANSWER_MONKEY
import quests.NaturalHistory.NaturalHistoryConstants.ANSWER_PENGUIN
import quests.NaturalHistory.NaturalHistoryConstants.ANSWER_SEA_SLUGS
import quests.NaturalHistory.NaturalHistoryConstants.ANSWER_SNAIL
import quests.NaturalHistory.NaturalHistoryConstants.ANSWER_SNAKE
import quests.NaturalHistory.NaturalHistoryConstants.ANSWER_TERRORBIRD
import quests.NaturalHistory.NaturalHistoryConstants.ANSWER_TORTOISE
import quests.NaturalHistory.NaturalHistoryConstants.ANSWER_WYVERN
import quests.NaturalHistory.NaturalHistoryConstants.CAMEL_SHIFT_COUNT
import quests.NaturalHistory.NaturalHistoryConstants.CONVERSATION_FINISH_ORLANDO_SMITH
import quests.NaturalHistory.NaturalHistoryConstants.CONVERSATION_ORLANDO_SMITH
import quests.NaturalHistory.NaturalHistoryConstants.DRAGON_SHIFT_COUNT
import quests.NaturalHistory.NaturalHistoryConstants.KALPHITE_QUEEN_SHIFT_COUNT
import quests.NaturalHistory.NaturalHistoryConstants.LEECH_SHIFT_COUNT
import quests.NaturalHistory.NaturalHistoryConstants.LIZARD_SHIFT_COUNT
import quests.NaturalHistory.NaturalHistoryConstants.MOLE_SHIFT_COUNT
import quests.NaturalHistory.NaturalHistoryConstants.MONKEY_SHIFT_COUNT
import quests.NaturalHistory.NaturalHistoryConstants.NAME_ORLANDO_SMITH
import quests.NaturalHistory.NaturalHistoryConstants.PENGUIN_SHIFT_COUNT
import quests.NaturalHistory.NaturalHistoryConstants.SEA_SLUGS_SHIFT_COUNT
import quests.NaturalHistory.NaturalHistoryConstants.SNAIL_SHIFT_COUNT
import quests.NaturalHistory.NaturalHistoryConstants.SNAKE_SHIFT_COUNT
import quests.NaturalHistory.NaturalHistoryConstants.TERRORBIRD_SHIFT_COUNT
import quests.NaturalHistory.NaturalHistoryConstants.TILE_CAMEL
import quests.NaturalHistory.NaturalHistoryConstants.TILE_DRAGON
import quests.NaturalHistory.NaturalHistoryConstants.TILE_KALPHITE_QUEEN
import quests.NaturalHistory.NaturalHistoryConstants.TILE_LEECH
import quests.NaturalHistory.NaturalHistoryConstants.TILE_LIZARD
import quests.NaturalHistory.NaturalHistoryConstants.TILE_MOLE
import quests.NaturalHistory.NaturalHistoryConstants.TILE_MONKEY
import quests.NaturalHistory.NaturalHistoryConstants.TILE_ORLANDO_SMITH
import quests.NaturalHistory.NaturalHistoryConstants.TILE_PENGUIN
import quests.NaturalHistory.NaturalHistoryConstants.TILE_SEA_SLUGS
import quests.NaturalHistory.NaturalHistoryConstants.TILE_SNAIL
import quests.NaturalHistory.NaturalHistoryConstants.TILE_SNAKE
import quests.NaturalHistory.NaturalHistoryConstants.TILE_TERRORBIRD
import quests.NaturalHistory.NaturalHistoryConstants.TILE_TORTOISE
import quests.NaturalHistory.NaturalHistoryConstants.TILE_WYVERN
import quests.NaturalHistory.NaturalHistoryConstants.TORTOISE_SHIFT_COUNT
import quests.NaturalHistory.NaturalHistoryConstants.WYVERN_SHIFT_COUNT
import quests.Common.Base.BaseQuest
import quests.Common.Base.BaseQuestStep
import quests.Common.CommonMethods
import quests.Common.Models.QuestInformation
import quests.Common.Models.QuestRequirements
import quests.Common.QuestTaskList
import quests.Common.SimpleConversationStep

class NaturalHistory(information: QuestInformation) : BaseQuest(information) {

    private val startQuest = SimpleConversationStep(
        NAME_ORLANDO_SMITH,
        TILE_ORLANDO_SMITH,
        CONVERSATION_ORLANDO_SMITH,
        "Talking to Orlando Smith",
        information
    )

    private val finishQuest = SimpleConversationStep(
        NAME_ORLANDO_SMITH,
        TILE_ORLANDO_SMITH,
        CONVERSATION_FINISH_ORLANDO_SMITH,
        "Finishing Quest",
        information
    )

    override fun addRequirements(): QuestRequirements {
        return QuestRequirements(listOf(), listOf())
    }

    override fun getQuestStep(stepPosition: Int): BaseQuestStep? {
        if (stepPosition == 0) {
            return startQuest
        } else if (stepPosition == 1073741822) {
            return finishQuest
        } else if (stepPosition == -1073741826){
            information.complete = true
            CommonMethods.closeQuestComplete()
            return null
        }else {
            logger.info("Step $stepPosition")
            return answerQuestions().processStep()
        }
    }

    private fun answerQuestions(): QuestTaskList {
        return QuestTaskList(
            InteractWithPlaque(LIZARD_SHIFT_COUNT, TILE_LIZARD, ANSWER_LIZARD, information),
            InteractWithPlaque(TORTOISE_SHIFT_COUNT, TILE_TORTOISE, ANSWER_TORTOISE, information),
            InteractWithPlaque(DRAGON_SHIFT_COUNT, TILE_DRAGON, ANSWER_DRAGON, information),
            InteractWithPlaque(WYVERN_SHIFT_COUNT, TILE_WYVERN, ANSWER_WYVERN, information),
            InteractWithPlaque(SNAIL_SHIFT_COUNT, TILE_SNAIL, ANSWER_SNAIL, information),
            InteractWithPlaque(SNAKE_SHIFT_COUNT, TILE_SNAKE, ANSWER_SNAKE, information),
            InteractWithPlaque(SEA_SLUGS_SHIFT_COUNT, TILE_SEA_SLUGS, ANSWER_SEA_SLUGS, information),
            InteractWithPlaque(MONKEY_SHIFT_COUNT, TILE_MONKEY, ANSWER_MONKEY, information),
            InteractWithPlaque(KALPHITE_QUEEN_SHIFT_COUNT, TILE_KALPHITE_QUEEN, ANSWER_KALPHITE_QUEEN, information),
            InteractWithPlaque(TERRORBIRD_SHIFT_COUNT, TILE_TERRORBIRD, ANSWER_TERRORBIRD, information),
            InteractWithPlaque(PENGUIN_SHIFT_COUNT, TILE_PENGUIN, ANSWER_PENGUIN, information),
            InteractWithPlaque(MOLE_SHIFT_COUNT, TILE_MOLE, ANSWER_MOLE, information),
            InteractWithPlaque(CAMEL_SHIFT_COUNT, TILE_CAMEL, ANSWER_CAMEL, information),
            InteractWithPlaque(LEECH_SHIFT_COUNT, TILE_LEECH, ANSWER_LEECH, information),
        )
    }
}