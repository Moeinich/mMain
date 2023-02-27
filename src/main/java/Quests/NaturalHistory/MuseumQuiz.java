package Quests.NaturalHistory;

import org.powbot.api.rt4.Varpbits;

import Helpers.Task;
import Quests.QuestData.QuestVarpbits;

public class MuseumQuiz extends Task {
    @Override
    public boolean activate() {
        return Varpbits.varpbit(QuestVarpbits.NATURAL_HISTORY.getQuestVarbit()) != QuestVarpbits.NATURAL_HISTORY.getFinishedValue();
    }
    @Override
    public boolean execute() {

        return false;
    }
}
