package Helpers;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import org.powbot.dax.api.DaxWalker;
import org.powbot.dax.teleports.Teleport;

import script.mMain;

public class BankBeforeTask extends Task {
    public boolean activate() {
        if(mMain.shouldBank == true) {
            return true;
        } else return false;
    }
    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.isEmpty()) {
            mMain.state = "Inv empty, move on";
            if (Bank.opened()) {
                if (Bank.close()) {
                    Condition.wait( () -> !Bank.opened(), 150, 50);
                }
            }
            mMain.shouldBank = false;
        }

        if (Bank.nearest().tile().distanceTo(Players.local()) > 5 && Inventory.isNotEmpty() && mMain.shouldBank) {
        mMain.state = "Bank before task";
        DaxWalker.blacklistTeleports(Teleport.CASTLE_WARS_MINIGAME, Teleport.SOUL_WARS_MINIGAME, Teleport.CLAN_WARS_MINIGAME);
        DaxWalker.walkToBank();
        }
        if (Bank.nearest().tile().distanceTo(Players.local()) <= 5) {
            if (!Bank.opened() && Bank.inViewport()) {
                Bank.open();
            }
            if (Bank.opened()) {
                if(Bank.depositInventory()) {
                    Condition.wait(Inventory::isEmpty, 150, 50);
                    Bank.close();
                }
            }
        }
        return false;
    }
}
