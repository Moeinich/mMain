package helpers.questTasks;

import org.powbot.api.Random;
import org.powbot.api.rt4.Varpbits;

import helpers.extentions.Task;
import quests.QuestInitializer;
import quests.common.QuestVarpbits;
import script.mMain;

public class doXMarksTheSpot extends Task {
    @Override
    public boolean activate() {
        return Varpbits.varpbit(QuestVarpbits.X_MARKS_THE_SPOT.getQuestVarbit()) != QuestVarpbits.X_MARKS_THE_SPOT.getFinishedValue();
    }
    @Override
    public boolean execute() {
        mMain.state = "Quest: " + QuestVarpbits.X_MARKS_THE_SPOT.getQuestName();
        mMain.runtime.reset(Random.nextInt(mMain.MIN_TIME_LIMIT, mMain.MAX_TIME_LIMIT));
        QuestInitializer.xMarksTheSpot.run();
        return false;
    }
}
