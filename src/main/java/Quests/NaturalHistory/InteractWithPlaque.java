package Quests.NaturalHistory;

import static Quests.NaturalHistory.NaturalHistoryConstants.COMPONENT_QUESTION;
import static Quests.NaturalHistory.NaturalHistoryConstants.NAME_PLAQUE;
import static Quests.NaturalHistory.NaturalHistoryConstants.WIDGET_ANSWER;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Components;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Varpbits;
import org.powbot.api.rt4.Widgets;

import Quests.QuestData.Conditions;
import Quests.QuestData.QuestInformation;
import Quests.QuestData.SimpleObjectStep;

public class InteractWithPlaque extends SimpleObjectStep {
    private final NaturalHistoryConstants.Question[] answers;

    public InteractWithPlaque(int shiftCount, Tile buttonTile, NaturalHistoryConstants.Question[] answers, QuestInformation information) {
        super(buttonTile,
                null,
                () -> Objects.stream().name(NAME_PLAQUE).within(buttonTile, 2).first(),
                (GameObject go) -> go.interact(NaturalHistoryConstants.ACTION_PLAQUE),
                () -> Conditions.waitUntilComponentAppears(WIDGET_ANSWER, COMPONENT_QUESTION).call(),
                "Interacting with plaque", information,
                () -> Varpbits.varpbit(1014, shiftCount, 0x3) != 3);
        this.answers = answers;
    }

    public void run() {
        Component question = questionComponent();
        questionComponent();
        if (!question.text().isEmpty()) {
            String questionText = question.text();
            NaturalHistoryConstants.Question answer = null;
            for (NaturalHistoryConstants.Question a : answers) {
                if (a.question.equalsIgnoreCase(questionText)) {
                    answer = a;
                    break;
                }
            }
            if (answer == null) {
                System.out.println("Unable to find answer for question " + questionText);
                return;
            }
            System.out.println("Answering question: " + questionText + " with " + answer.answer);
            Component answerComponent = Components.stream(WIDGET_ANSWER).filtered((Component c) -> !c.text().equalsIgnoreCase(questionText)).text(answer.answer).first();
            Condition.sleep(Random.nextInt(200, 450)); // Otherwise way too fast
            if (answerComponent != null && answerComponent.click()) {
                boolean result = Condition.wait(() -> {Component component = questionComponent();
                    return !component.text().equalsIgnoreCase(questionText);
                }, 150, 20);
                System.out.println("Answer result is " + result);
            }
        } else {
            run();
        }
    }

    private Component questionComponent() {
        return Widgets.component(WIDGET_ANSWER, COMPONENT_QUESTION);
    }
}