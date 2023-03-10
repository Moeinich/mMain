package helpers.questTasks;

import org.powbot.api.Random;
import org.powbot.api.rt4.Varpbits;

import helpers.extentions.Task;
import quests.QuestInitializer;
import quests.common.QuestVarpbits;
import script.mMain;

public class doClientOfKourend extends Task {
    @Override
    public boolean activate() {
        return Varpbits.varpbit(QuestVarpbits.CLIENT_OF_KOUREND.getQuestVarbit()) != QuestVarpbits.CLIENT_OF_KOUREND.getFinishedValue();
    }
    @Override
    public boolean execute() {
        mMain.state = "Quest: " + QuestVarpbits.CLIENT_OF_KOUREND.getQuestName();
        if (mMain.runtime.timeLeft() <= 30000) {
            System.out.println("Runtime reset due to Client of Kourend");
            mMain.runtime.reset(Random.nextInt(mMain.MIN_TIME_LIMIT, mMain.MAX_TIME_LIMIT));

        }        QuestInitializer.clientOfKourend.run();
        return false;
    }
}
