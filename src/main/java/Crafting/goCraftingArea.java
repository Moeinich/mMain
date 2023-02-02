package Crafting;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import Helpers.Task;
import Helpers.skillData;
import script.mMain;

public class goCraftingArea extends Task {
    @Override
    public boolean activate() {
        return !skillData.craftingArea.contains(Players.local().tile());
    }

    @Override
    public void execute() {
        if (!Players.local().tile().equals(skillData.movementThieving())) {
            mMain.State = "Walking to Crafting spot";
            Movement.builder(skillData.movementCrafting()).setRunMin(45).setRunMax(75).move();
        }
    }
}
