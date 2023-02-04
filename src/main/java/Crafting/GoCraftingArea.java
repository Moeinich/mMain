package Crafting;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.walking.model.Skill;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class GoCraftingArea extends Task {
    Locatable nearestBank = Bank.nearest();
    @Override
    public boolean activate() {
        return !Bank.inViewport() || nearestBank.tile().distanceTo(Players.local()) < 10;
    }
    @Override
    public void execute() {
            mMain.State = "Walking to Crafting spot";
            Movement.moveToBank();
    }
}
