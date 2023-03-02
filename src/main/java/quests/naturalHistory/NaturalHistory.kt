package quests.naturalHistory

import quests.naturalHistory.NaturalHistoryConstants.ANSWER_CAMEL
import quests.naturalHistory.NaturalHistoryConstants.ANSWER_DRAGON
import quests.naturalHistory.NaturalHistoryConstants.ANSWER_KALPHITE_QUEEN
import quests.naturalHistory.NaturalHistoryConstants.ANSWER_LEECH
import quests.naturalHistory.NaturalHistoryConstants.ANSWER_LIZARD
import quests.naturalHistory.NaturalHistoryConstants.ANSWER_MOLE
import quests.naturalHistory.NaturalHistoryConstants.ANSWER_MONKEY
import quests.naturalHistory.NaturalHistoryConstants.ANSWER_PENGUIN
import quests.naturalHistory.NaturalHistoryConstants.ANSWER_SEA_SLUGS
import quests.naturalHistory.NaturalHistoryConstants.ANSWER_SNAIL
import quests.naturalHistory.NaturalHistoryConstants.ANSWER_SNAKE
import quests.naturalHistory.NaturalHistoryConstants.ANSWER_TERRORBIRD
import quests.naturalHistory.NaturalHistoryConstants.ANSWER_TORTOISE
import quests.naturalHistory.NaturalHistoryConstants.ANSWER_WYVERN
import quests.naturalHistory.NaturalHistoryConstants.CAMEL_SHIFT_COUNT
import quests.naturalHistory.NaturalHistoryConstants.CONVERSATION_FINISH_ORLANDO_SMITH
import quests.naturalHistory.NaturalHistoryConstants.CONVERSATION_ORLANDO_SMITH
import quests.naturalHistory.NaturalHistoryConstants.DRAGON_SHIFT_COUNT
import quests.naturalHistory.NaturalHistoryConstants.KALPHITE_QUEEN_SHIFT_COUNT
import quests.naturalHistory.NaturalHistoryConstants.LEECH_SHIFT_COUNT
import quests.naturalHistory.NaturalHistoryConstants.LIZARD_SHIFT_COUNT
import quests.naturalHistory.NaturalHistoryConstants.MOLE_SHIFT_COUNT
import quests.naturalHistory.NaturalHistoryConstants.MONKEY_SHIFT_COUNT
import quests.naturalHistory.NaturalHistoryConstants.NAME_ORLANDO_SMITH
import quests.naturalHistory.NaturalHistoryConstants.PENGUIN_SHIFT_COUNT
import quests.naturalHistory.NaturalHistoryConstants.SEA_SLUGS_SHIFT_COUNT
import quests.naturalHistory.NaturalHistoryConstants.SNAIL_SHIFT_COUNT
import quests.naturalHistory.NaturalHistoryConstants.SNAKE_SHIFT_COUNT
import quests.naturalHistory.NaturalHistoryConstants.TERRORBIRD_SHIFT_COUNT
import quests.naturalHistory.NaturalHistoryConstants.TILE_CAMEL
import quests.naturalHistory.NaturalHistoryConstants.TILE_DRAGON
import quests.naturalHistory.NaturalHistoryConstants.TILE_KALPHITE_QUEEN
import quests.naturalHistory.NaturalHistoryConstants.TILE_LEECH
import quests.naturalHistory.NaturalHistoryConstants.TILE_LIZARD
import quests.naturalHistory.NaturalHistoryConstants.TILE_MOLE
import quests.naturalHistory.NaturalHistoryConstants.TILE_MONKEY
import quests.naturalHistory.NaturalHistoryConstants.TILE_ORLANDO_SMITH
import quests.naturalHistory.NaturalHistoryConstants.TILE_PENGUIN
import quests.naturalHistory.NaturalHistoryConstants.TILE_SEA_SLUGS
import quests.naturalHistory.NaturalHistoryConstants.TILE_SNAIL
import quests.naturalHistory.NaturalHistoryConstants.TILE_SNAKE
import quests.naturalHistory.NaturalHistoryConstants.TILE_TERRORBIRD
import quests.naturalHistory.NaturalHistoryConstants.TILE_TORTOISE
import quests.naturalHistory.NaturalHistoryConstants.TILE_WYVERN
import quests.naturalHistory.NaturalHistoryConstants.TORTOISE_SHIFT_COUNT
import quests.naturalHistory.NaturalHistoryConstants.WYVERN_SHIFT_COUNT
import quests.common.base.BaseQuest
import quests.common.base.BaseQuestStep
import quests.common.CommonMethods
import quests.common.models.QuestInformation
import quests.common.models.QuestRequirements
import quests.common.QuestTaskList
import quests.common.SimpleConversationStep

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
        return if (stepPosition == 0) {
            startQuest
        } else if (stepPosition == 1073741822) {
            finishQuest
        } else if (stepPosition == -1073741826){
            information.complete = true
            CommonMethods.closeQuestComplete()
            null
        }else {
            logger.info("Step $stepPosition")
            answerQuestions().processStep()
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