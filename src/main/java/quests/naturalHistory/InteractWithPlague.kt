package quests.naturalHistory

import quests.naturalHistory.NaturalHistoryConstants.ACTION_PLAQUE
import quests.naturalHistory.NaturalHistoryConstants.COMPONENT_QUESTION
import quests.naturalHistory.NaturalHistoryConstants.NAME_PLAQUE
import quests.naturalHistory.NaturalHistoryConstants.WIDGET_ANSWER
import quests.common.base.SimpleObjectStep
import helpers.extentions.Conditions
import quests.common.models.QuestInformation
import org.powbot.api.Condition
import org.powbot.api.Random
import org.powbot.api.Tile
import org.powbot.api.rt4.*

class InteractWithPlaque(
    shiftCount: Int,
    buttonTile: Tile,
    private val answers: Array<Question>,
    information: QuestInformation
) : SimpleObjectStep(
    buttonTile,
    arrayOf(),
    { Objects.stream().name(NAME_PLAQUE).within(buttonTile, 2).first() },
    { go: GameObject -> go.interact(ACTION_PLAQUE) },
    { Conditions.waitUntilComponentAppears(WIDGET_ANSWER, COMPONENT_QUESTION).call() },
    "Interacting with plaque",
    information,
    { Varpbits.varpbit(1014, shiftCount, 0x3) != 3 },
) {

    override fun run() {
        val question = questionComponent()
        if (question != Component.Nil && question.text().isNotEmpty()) {
            val questionText = question.text()
            val answer = answers.firstOrNull { it.question.equals(questionText, true) }
            if (answer == null) {
                logger.info("Unable to find answer for question $questionText")
                return
            }
            logger.info("Answering question: $question with $answer")
            val answerComponent =
                Components.stream(WIDGET_ANSWER).filtered { it.text() != questionText }.text(answer.answer).first()
            Condition.sleep(Random.nextInt(200, 450)) // Otherwise way too fast
            if (answerComponent != Component.Nil && answerComponent.click()) {
                val result = Condition.wait {
                    val component = questionComponent()
                    component == Component.Nil || component.text() != questionText
                }
                logger.info("Answer result is $result")
            }
        } else {
            super.run()
        }
    }

    private fun questionComponent(): Component {
        return Widgets.component(WIDGET_ANSWER, COMPONENT_QUESTION)
    }
}