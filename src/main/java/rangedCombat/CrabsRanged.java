package rangedCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;

import helpers.CombatHelper;
import helpers.extentions.Task;

public class CrabsRanged extends Task {

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_RANGE) >= 30
                && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 30;
    }

    @Override
    public boolean execute() {
        CombatHelper.doCrabs();
        return false;
    }
}
