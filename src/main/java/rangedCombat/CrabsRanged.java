package rangedCombat;

import org.powbot.api.rt4.Combat;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

import helpers.extentions.Task;
import helpers.tasks.KillCrabs;

public class CrabsRanged extends Task {

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_RANGE) >= 30
                && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 30;
    }

    @Override
    public boolean execute() {
        if (!Combat.style(Combat.Style.AGGRESSIVE)) {
            Combat.style(Combat.Style.AGGRESSIVE);
        }

        KillCrabs.doCrabs();
        return false;
    }
}
