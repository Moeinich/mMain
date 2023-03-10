package helpers.questTasks;

import org.powbot.api.Random;
import org.powbot.api.rt4.Varpbits;

import helpers.extentions.Task;
import quests.QuestInitializer;
import quests.common.QuestVarpbits;
import script.mMain;

public class doTempleOfTheEye extends Task {
    @Override
    public boolean activate() {
        return Varpbits.varpbit(QuestVarpbits.TEMPLE_OF_THE_EYE.getQuestVarbit()) != QuestVarpbits.TEMPLE_OF_THE_EYE.getFinishedValue();
    }
    @Override
    public boolean execute() {
        mMain.state = "Quest: " + QuestVarpbits.TEMPLE_OF_THE_EYE.getQuestName();
        mMain.runtime.reset(Random.nextInt(mMain.MIN_TIME_LIMIT, mMain.MAX_TIME_LIMIT));
        QuestInitializer.templeOfTheEye.run();
        return false;
    }
}
