package Helpers;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Players;
import org.powbot.dax.api.DaxWalker;
import org.powbot.dax.teleports.Teleport;

import script.mMain;

public class goToBank extends Task{
    @Override
    public boolean activate() {
        return Bank.nearest().tile().distanceTo(Players.local()) > 5;
    }
    @Override
    public boolean execute() {
        if (Inventory.stream().name("Coin pouch").isNotEmpty()) {
            Item CoinPouch = Inventory.stream().name("Coin pouch").first();
            mMain.state = "Opening pouches";
            if (CoinPouch.interact("Open-all", "Coin pouch") && !Players.local().inMotion()) {
                Condition.wait( () -> Inventory.stream().name("Coin pouch").isEmpty(), 200,50);
            }
        }
        if (Bank.nearest().tile().distanceTo(Players.local()) > 5) {
            mMain.state = "Walking to bank";
            DaxWalker.blacklistTeleports(Teleport.CASTLE_WARS_MINIGAME, Teleport.SOUL_WARS_MINIGAME, Teleport.CLAN_WARS_MINIGAME);
            DaxWalker.walkToBank();
        }
        return false;
    }
}