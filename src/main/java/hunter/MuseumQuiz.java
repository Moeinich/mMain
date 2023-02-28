package hunter;

import org.powbot.api.rt4.Varpbits;

import helpers.Task;
import quests.NaturalHistory.NaturalHistory;
import quests.Common.Models.QuestInformation;
import quests.Common.QuestVarpbits;

public class MuseumQuiz extends Task {
    @Override
    public boolean activate() {
        return Varpbits.varpbit(QuestVarpbits.NATURAL_HISTORY.getQuestVarbit()) != QuestVarpbits.NATURAL_HISTORY.getFinishedValue();
    }
    @Override
    public boolean execute() {
        NaturalHistory naturalHistory = new NaturalHistory(new QuestInformation(QuestVarpbits.NATURAL_HISTORY, new String[]{"Cake"}, null, null));
        naturalHistory.run();
        return false;
    }
}
