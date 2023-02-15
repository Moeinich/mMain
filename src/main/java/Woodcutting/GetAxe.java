package Woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import org.powbot.dax.api.DaxWalker;

import Helpers.InteractionsHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class GetAxe extends Task {

    @Override
    public boolean activate() {
        return Inventory.stream().id(SkillData.wcAxes).isEmpty();
    }
    @Override
    public boolean execute() {
        Game.tab(Game.Tab.INVENTORY);
        if (Bank.nearest().tile().distanceTo(Players.local()) > 5) {
            mMain.state = "Get axe - GoToBank";
            DaxWalker.walkToBank();
        }
        if (!Bank.opened() && Inventory.stream().id(SkillData.wcAxes).isEmpty()) {
            mMain.state = "Get axe - Withdraw";
            if (Bank.open()) {
                InteractionsHelper interactionsHelper = new InteractionsHelper();
                interactionsHelper.depositAndWithdraw(SkillData.withdrawAxe(), 1);
                Bank.close();
                Condition.wait( () -> !Bank.opened(), 250, 50);
            }
        }
        return false;
    }
}