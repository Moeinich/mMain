package mining;


import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import org.powbot.dax.api.DaxWalker;

import helpers.InteractionsHelper;
import helpers.PlayerHelper;
import helpers.extentions.Task;
import script.mMain;
import woodcutting.WoodcuttingData;

public class GetPickaxe extends Task {
    @Override
    public boolean activate() {
        return Players.local().isRendered() && PlayerHelper.hasItem(MiningData.withdrawPickaxe());
    }

    @Override
    public boolean execute() {
        if (Bank.nearest().tile().distanceTo(Players.local()) > 5) {
            mMain.state = "Moving to bank";
            DaxWalker.walkToBank();
        }
        if (!Bank.opened() && PlayerHelper.hasItem(MiningData.withdrawPickaxe())) {
            mMain.state = "Get pickaxe - Withdraw";
            if (Bank.open()) {
                InteractionsHelper.depositAndWithdraw(MiningData.withdrawPickaxe(), 1);
                Bank.close();
                Condition.wait( () -> !Bank.opened(), 250, 50);
            }
        }
        return false;
    }
}