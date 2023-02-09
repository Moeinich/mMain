package Helpers;

import org.powbot.api.Condition;
import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.dax.api.DaxWalker;
import org.powbot.dax.teleports.Teleport;

import script.mMain;

public class GoToBank extends Task{
    Locatable nearestBank = Bank.nearest();
    @Override
    public boolean activate() {
        return nearestBank.tile().distanceTo(Players.local()) > 5;
    }
    @Override
    public void execute() {
        if (Inventory.stream().name("Coin pouch").isNotEmpty()) {
            Item CoinPouch = Inventory.stream().name("Coin pouch").first();
            mMain.State = "Opening pouches";
            if (CoinPouch.interact("Open-all", "Coin pouch") && !Players.local().inMotion()) {
                Condition.wait( () -> Inventory.stream().name("Coin pouch").isEmpty(), 200,50);
            }
        }
        if (nearestBank.tile().distanceTo(Players.local()) > 5) {
            mMain.State = "Walking to bank";
            DaxWalker.blacklistTeleports(Teleport.SOUL_WARS_MINIGAME);
            Movement.moveToBank();
        }
    }
}