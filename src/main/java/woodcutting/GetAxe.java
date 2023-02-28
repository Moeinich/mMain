package woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import org.powbot.dax.api.DaxWalker;

import helpers.InteractionsHelper;
import helpers.Task;
import script.mMain;

public class GetAxe extends Task {

    @Override
    public boolean activate() {
        return Players.local().isRendered() && Inventory.stream().id(WoodcuttingData.withdrawAxe()).isEmpty();
    }
    @Override
    public boolean execute() {
        if (Bank.nearest().tile().distanceTo(Players.local()) > 5) {
            mMain.state = "Get axe - GoToBank";
            DaxWalker.walkToBank();
        }
        if (!Bank.opened() && Inventory.stream().id(WoodcuttingData.withdrawAxe()).isEmpty()) {
            mMain.state = "Get axe - Withdraw";
            if (Bank.open()) {
                InteractionsHelper.depositAndWithdraw(WoodcuttingData.withdrawAxe(), 1);
                Bank.close();
                Condition.wait( () -> !Bank.opened(), 250, 50);
            }
        }
        return false;
    }
}