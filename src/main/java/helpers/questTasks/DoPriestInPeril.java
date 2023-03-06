package helpers.questTasks;

import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Varpbits;
import org.powbot.api.rt4.walking.model.Skill;

import quests.QuestInitializer;
import helpers.extentions.Task;
import quests.common.QuestVarpbits;
import script.mMain;

public class DoPriestInPeril extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Skill.Defence) >= 30
                && Skills.realLevel(Skill.Attack) >= 30
                && Skills.realLevel(Skill.Strength) >= 30
                && Varpbits.varpbit(QuestVarpbits.PRIEST_IN_PERIL.getQuestVarbit()) != QuestVarpbits.PRIEST_IN_PERIL.getFinishedValue();
    }
    @Override
    public boolean execute() {
        mMain.state = "Priest in Peril";
        QuestInitializer.priestInPeril.run();
        return false;
    }
}