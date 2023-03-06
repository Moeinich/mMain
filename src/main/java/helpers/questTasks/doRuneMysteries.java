package helpers.questTasks;

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
        mMain.state = "Museum quiz";
        QuestInitializer.runeMysteries.run();
        return false;
    }
}
