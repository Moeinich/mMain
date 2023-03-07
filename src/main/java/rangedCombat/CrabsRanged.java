package rangedCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;

import helpers.extentions.Task;
import helpers.tasks.killCrabs;

public class CrabsRanged extends Task {

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_RANGE) >= 30
                && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 30;
    }

    @Override
    public boolean execute() {
        killCrabs.doCrabs();
        return false;
    }
}
