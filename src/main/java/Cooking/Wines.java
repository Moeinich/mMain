package Cooking;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.dax.api.DaxWalker;
import org.powbot.mobile.script.ScriptManager;

import Helpers.InteractionsHelper;
import Helpers.ItemList;
import Helpers.Task;
import script.mMain;

public class Wines extends Task {
    int CombineWithItemID = ItemList.GRAPES_1987;
    int ToolID = ItemList.JUG_OF_WATER_1937;
    int WidgetID = 270;
    int ComponentID = 14;

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_COOKING) >= 35;
    }

    @Override
    public boolean execute() {
        mMain.state = "Go to bank";
        if (Bank.nearest().tile().distanceTo(Players.local()) > 4) {
            DaxWalker.walkToBank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && (Inventory.stream().id(ToolID).isEmpty() || Inventory.stream().id(CombineWithItemID).isEmpty())) {
            mMain.state = "Banking loop";
            bank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && !Bank.opened() && Inventory.stream().id(CombineWithItemID, ToolID).isNotEmpty()) {
            mMain.state = "Craft loop";
            craft();
        }
        if (ScriptManager.INSTANCE.isStopping()) {
            ScriptManager.INSTANCE.stop();
        }
        return false;
    }

    private void bank() {
        checkTool();
        withdrawItems();
    }

    private void checkTool() {
        InteractionsHelper InteractionsHelper = new InteractionsHelper();
        mMain.state = "Get jugs";
        if (Inventory.stream().id(ToolID).isEmpty()) {
            InteractionsHelper.depositAndWithdraw(ToolID, 14);
        }

    }
    private void withdrawItems() {
        InteractionsHelper InteractionsHelper = new InteractionsHelper();
        mMain.state = "Withdraw items";
        if (Bank.stream().id(CombineWithItemID).isNotEmpty()) {
            InteractionsHelper.withdrawItem(CombineWithItemID, 14);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 500, 50);
        }
    }
    private void craft() {
        while (Inventory.stream().id(CombineWithItemID).count() >= 1) {
            InteractionsHelper InteractionsHelper = new InteractionsHelper();
            InteractionsHelper.combineItems(ToolID, CombineWithItemID, WidgetID, ComponentID);
        }
    }
}
