package helpers.questTasks;

import org.powbot.api.Random;
import org.powbot.api.rt4.Varpbits;

import helpers.extentions.Task;
import quests.QuestInitializer;
import quests.common.QuestVarpbits;
import script.mMain;

public class doRuneMysteries extends Task {

    @Override
    public boolean activate() {
        return Varpbits.varpbit(QuestVarpbits.RUNE_MYSTERIES.getQuestVarbit()) != QuestVarpbits.RUNE_MYSTERIES.getFinishedValue();
    }
    @Override
    public boolean execute() {
        mMain.state = "Quest: " + QuestVarpbits.RUNE_MYSTERIES.getQuestName();
        if (mMain.runtime.timeLeft() <= 30000) {
            System.out.println("Runtime reset due to Rune Mysteries");
            mMain.runtime.reset(Random.nextInt(mMain.MIN_TIME_LIMIT, mMain.MAX_TIME_LIMIT));

        }        QuestInitializer.runeMysteries.run();
        return false;
    }
}
