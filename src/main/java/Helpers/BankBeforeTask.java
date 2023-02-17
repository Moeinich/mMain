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
        return mMain.shouldBank;
    }
    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.isEmpty()) {
            mMain.state = "Inv empty, move on";
            mMain.shouldBank = false;
        }

        if (Inventory.isNotEmpty() && mMain.shouldBank) {
            if (Bank.nearest().tile().distanceTo(Players.local()) > 5) {
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
        }
        return false;
    }
}
