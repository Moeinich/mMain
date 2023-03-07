package meleeCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;

import helpers.extentions.Task;
import helpers.tasks.killCrabs;

public class Crabs extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_STRENGTH) >= 30
                && Skills.realLevel(Constants.SKILLS_ATTACK) >= 30
                && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 30;
    }

    @Override
    public boolean execute() {
        killCrabs.doCrabs();
        return false;
    }
}
