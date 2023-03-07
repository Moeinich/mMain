package runecrafting;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

import helpers.extentions.Task;
import script.mMain;

public class EarthRunes extends Task {
    Area outsideAltar = new Area(new Tile(3302, 3478, 0), new Tile(3309, 3472, 0));
    Area insideAltar = new Area(new Tile(3302, 3478, 0), new Tile(3309, 3472, 0));

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
