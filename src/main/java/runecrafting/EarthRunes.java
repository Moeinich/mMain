package runecrafting;

import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

import helpers.extentions.Task;
import script.mMain;

public class EarthRunes extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Skill.Runecrafting) <= 9;
    }
    @Override
    public boolean execute() {
        mMain.state = "Make earth runes";
        return false;
    }
}
