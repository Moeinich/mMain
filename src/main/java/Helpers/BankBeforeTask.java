package Helpers;

import org.powbot.api.Condition;
import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.dax.api.DaxWalker;
import org.powbot.dax.teleports.Teleport;

import script.mMain;

public class BankBeforeTask extends Task {
    public boolean activate() {
        if(mMain.ShouldBank = true) {
            return true;
        } else return false;
    }

    @Override
    public void execute() {
        if (Inventory.isEmpty()) {
            mMain.State = "Inventory empty, moving on.. ";
            if (Bank.opened()) {
                if (Bank.close()) {
                    Condition.wait( () -> !Bank.opened(), 150, 50);
                }
            } else mMain.ShouldBank = false;

        }
        
        Locatable nearestBank = Bank.nearest();
        if (nearestBank.tile().distanceTo(Players.local()) > 5) {
        mMain.State = "Bank before task";
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
                    Bank.close();
                }
            }
        }
    }
}
