package Cooking;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Skills;
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
    public void execute() {
        if (Game.tab(Game.Tab.INVENTORY) && (Inventory.stream().id(ToolID).isEmpty() || Inventory.stream().id(CombineWithItemID).isEmpty())) {
            mMain.State = "Banking loop";
            bank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && !Bank.opened() && Inventory.stream().id(CombineWithItemID, ToolID).isNotEmpty()) {
            mMain.State = "craft loop";
            craft();
        }
        if (ScriptManager.INSTANCE.isStopping()) {
            ScriptManager.INSTANCE.stop();
        }
    }

    private void bank() {
        checkTool();
        withdrawItems();
    }

    private void checkTool() {
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        mMain.State = "Checking tool..";
        if (Inventory.stream().id(ToolID).isEmpty()) {
            interactionsHelper.DepositAndWithdraw(ToolID, 14);
        }

    }
    private void withdrawItems() {
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        mMain.State = "Withdraw items";
        if (!Bank.opened() && Bank.inViewport()) {
            Bank.open();
        }
        if (Bank.stream().id(CombineWithItemID).isNotEmpty()) {
            Bank.depositAllExcept(ToolID);
            interactionsHelper.WithdrawItem(CombineWithItemID, 27);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 500, 20);
        }
    }
    private void craft() {
        while (Inventory.stream().id(CombineWithItemID).count() >= 1) {
            InteractionsHelper interactionsHelper = new InteractionsHelper();
            interactionsHelper.CombineItems(ToolID, CombineWithItemID, WidgetID, ComponentID);
        }
    }
}