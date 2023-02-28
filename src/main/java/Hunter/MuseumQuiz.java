package Hunter;

import org.powbot.api.rt4.Varpbits;

import Helpers.Task;
import Quests.NaturalHistory.NaturalHistory;
import Quests.QuestData.Models.QuestInformation;
import Quests.QuestData.QuestVarpbits;

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
