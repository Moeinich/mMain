package runecrafting;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

import helpers.extentions.Task;
import script.mMain;

public class EarthRunes extends Task {
    Area outsideAltar = new Area(new Tile(3302, 3478, 0), new Tile(3309, 3472, 0));
    Area insideAltar = new Area(new Tile(2627, 4862, 0), new Tile(2685, 4814, 0));
    Area middleofAltarArea = new Area(new Tile(2652, 4844, 0), new Tile(2663, 4833, 0));
    Area varrockEastBank = new Area(new Tile(3250, 3423, 0), new Tile(3257, 3419, 0));

    @Override
    public boolean activate() {
        return Skills.realLevel(Skill.Runecrafting) <= 9;
    }
    @Override
    public boolean execute() {
        mMain.state = "Make earth runes";
        if (Inventory.stream().name("Pure essence").isEmpty()) {
            //Do some banking and move to the bank.
            Movement.moveTo(varrockEastBank.getRandomTile());
        }
        if (Inventory.stream().name("Pure essence").isNotEmpty()) {

        }
        return false;
    }
}
