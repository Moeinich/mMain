package Fletching;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.walking.model.Skill;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class GoFletchingArea extends Task {
    @Override
    public boolean activate() {
        Locatable nearestBank = Bank.nearest();
        return !Bank.inViewport() || nearestBank.tile().distanceTo(Players.local()) < 5;
    }

    @Override
    public void execute() {
        mMain.State = "Walking to Fletching spot";
        Movement.moveToBank();
    }
}