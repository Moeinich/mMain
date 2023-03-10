package helpers.questTasks;

import org.powbot.api.Random;
import org.powbot.api.rt4.Varpbits;

import helpers.extentions.Task;
import quests.QuestInitializer;
import quests.common.QuestVarpbits;
import script.mMain;

public class doDruidicRitual extends Task {
    @Override
    public boolean activate() {
        return Varpbits.varpbit(QuestVarpbits.DRUIDIC_RITUAL.getQuestVarbit()) != QuestVarpbits.DRUIDIC_RITUAL.getFinishedValue();
    }
    @Override
    public boolean execute() {
        mMain.state = "Quest: " + QuestVarpbits.DRUIDIC_RITUAL.getQuestName();
        mMain.runtime.reset(Random.nextInt(mMain.MIN_TIME_LIMIT, mMain.MAX_TIME_LIMIT));
        QuestInitializer.druidicRitual.run();
        return false;
    }
}
