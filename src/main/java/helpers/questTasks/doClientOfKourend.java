package helpers.questTasks;

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
        QuestInitializer.clientOfKourend.run();
        return false;
    }
}
