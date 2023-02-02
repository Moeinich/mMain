package Crafting;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.walking.model.Skill;

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
            Movement.moveTo(SkillData.movementCrafting());
        }
    }
}
