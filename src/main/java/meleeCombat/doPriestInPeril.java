package meleeCombat;

import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Varpbits;
import org.powbot.api.rt4.walking.model.Skill;

import helpers.extentions.Task;
import quests.common.QuestVarpbits;
import quests.common.models.QuestInformation;
import quests.priestInPeril.PriestInPeril;
import script.mMain;

public class doPriestInPeril extends Task {
    PriestInPeril priestInPeril = new PriestInPeril(new QuestInformation(QuestVarpbits.PRIEST_IN_PERIL, new String[]{"Lobster"}, "Adamant scimitar", null));
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
        priestInPeril.run();
        return false;
    }
}