package Thieving;

import org.powbot.api.Condition;
import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.dax.api.DaxWalker;
import org.powbot.dax.teleports.Teleport;

import Helpers.Task;
import script.mMain;

public class ShouldBank extends Task {
    Locatable nearestBank = Bank.nearest();
    public boolean activate() {
        return mMain.ShouldBank;
    }

    @Override
    public void execute() {
        if (nearestBank.tile().distanceTo(Players.local()) > 5) {
        mMain.State = "Walking to bank";
        DaxWalker.blacklistTeleports(Teleport.SOUL_WARS_MINIGAME);
        Movement.moveToBank();
        }
        if (nearestBank.tile().distanceTo(Players.local()) <= 5) {
            if (!Bank.opened() && Bank.inViewport()) {
                Bank.open();
            }
            if (Bank.opened()) {
                if(Bank.depositInventory()) {
                    Condition.wait( () -> Inventory.isEmpty(), 150, 50);
                }
            }
            if (Inventory.isEmpty()) {
                Bank.close();
                mMain.ShouldBank = false;
            }
        }
    }
}
