package helpers.questTasks;

import org.powbot.api.rt4.Varpbits;

import helpers.extentions.Task;
import quests.QuestInitializer;
import quests.common.QuestVarpbits;
import script.mMain;

public class DoMuseumQuiz extends Task {
    @Override
    public boolean activate() {
        return Varpbits.varpbit(QuestVarpbits.NATURAL_HISTORY.getQuestVarbit()) != QuestVarpbits.NATURAL_HISTORY.getFinishedValue();
    }
    @Override
    public boolean execute() {
        mMain.state = "Quest: " + QuestVarpbits.NATURAL_HISTORY.getQuestName();
        QuestInitializer.naturalHistory.run();
        return false;
    }
}