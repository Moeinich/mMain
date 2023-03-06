package helpers.questTasks;

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
        mMain.state = "Druidic Ritual";
        QuestInitializer.druidicRitual.run();
        return false;
    }
}
