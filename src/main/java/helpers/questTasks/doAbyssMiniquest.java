package helpers.questTasks;

import org.powbot.api.rt4.Varpbits;

import helpers.extentions.Task;
import quests.QuestInitializer;
import quests.common.QuestVarpbits;
import script.mMain;

public class doAbyssMiniquest extends Task {
    @Override
    public boolean activate() {
        return Varpbits.varpbit(QuestVarpbits.ENTER_THE_ABYSS.getQuestVarbit()) != QuestVarpbits.ENTER_THE_ABYSS.getFinishedValue();
    }
    @Override
    public boolean execute() {
        mMain.state = "Quest: " + QuestVarpbits.ENTER_THE_ABYSS.getQuestName();
        QuestInitializer.enterTheAbyss.run();
        return false;
    }
}
