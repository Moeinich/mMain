package Helpers;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import script.mMain;

public class GoToBank extends Task{
    Locatable nearestBank = Bank.nearest();
    @Override
    public boolean activate() {
        return nearestBank.tile().distanceTo(Players.local()) > 5;
    }
    @Override
    public void execute() {
        mMain.State = "Walking to bank";
        Movement.moveToBank();
    }
}