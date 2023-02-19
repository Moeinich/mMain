package Mining;


import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import org.powbot.dax.api.DaxWalker;

import Helpers.interactionHelper;
import Helpers.Task;
import script.mMain;

public class GetPickaxe extends Task {
    @Override
    public boolean activate() {
return Inventory.stream().id(miningData.pickaxes).isEmpty();}

    @Override
    public boolean execute() {
        Game.tab(Game.Tab.INVENTORY);
        if (Bank.nearest().tile().distanceTo(Players.local()) > 5) {
            mMain.state = "Moving to bank";
            DaxWalker.walkToBank();
        }
        if (!Bank.opened()) {
            mMain.state = "Get pickaxe - Withdraw";
            if (Bank.open()) {
                interactionHelper.depositAndWithdraw(miningData.withdrawPickaxe(), 1);
                Bank.close();
                Condition.wait( () -> !Bank.opened(), 250, 50);
            }
        }
        return false;
    }
}