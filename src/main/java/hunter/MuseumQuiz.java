package hunter;

import org.powbot.api.rt4.Varpbits;

import helpers.extentions.Task;
import quests.common.QuestVarpbits;
import quests.naturalHistory.NaturalHistory;
import quests.common.models.QuestInformation;
import script.mMain;

public class MuseumQuiz extends Task {
    @Override
    public boolean activate() {
        return Varpbits.varpbit(QuestVarpbits.NATURAL_HISTORY.getQuestVarbit()) != QuestVarpbits.NATURAL_HISTORY.getFinishedValue();
    }
    @Override
    public boolean execute() {
        mMain.state = "Museum quiz";
        NaturalHistory naturalHistory = new NaturalHistory(new QuestInformation(QuestVarpbits.NATURAL_HISTORY, new String[]{"Cake"}, null, null));
        naturalHistory.run();
        return false;
    }
}