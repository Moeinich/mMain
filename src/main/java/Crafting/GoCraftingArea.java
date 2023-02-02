package Crafting;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class GoCraftingArea extends Task {
    @Override
    public boolean activate() {
        return !SkillData.craftingArea.contains(Players.local().tile());
    }

    @Override
    public void execute() {
        if (!Players.local().tile().equals(SkillData.movementCrafting())) {
            mMain.State = "Walking to Crafting spot";
            Movement.builder(SkillData.movementCrafting()).setRunMin(45).setRunMax(75).move();
        }
    }
}
