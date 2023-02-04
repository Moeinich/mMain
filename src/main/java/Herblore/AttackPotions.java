package Herblore;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class AttackPotions extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_HERBLORE) >= 3;
    }
    @Override
    public void execute() {
    }
}
