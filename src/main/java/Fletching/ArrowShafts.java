package Fletching;

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

public class ArrowShafts extends Task {
    int CombineWithItemID = ItemList.LOGS_1511;
    int ToolID = ItemList.KNIFE_946;
    int WidgetID = 270;
    int ComponentID = 14;

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_FLETCHING) < 10;
    }

    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY) && (Inventory.stream().id(ToolID).isEmpty() || Inventory.stream().id(CombineWithItemID).isEmpty())) {
            mMain.state = "Banking loop";
            bank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && !Bank.opened() && Inventory.stream().id(CombineWithItemID).isNotEmpty()) {
            mMain.state = "craft loop";
            fletch();
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
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        mMain.state = "Checking tool..";
        if (Inventory.stream().id(ToolID).isEmpty()) {
            interactionsHelper.depositAndWithdraw(ToolID, 1);
        }

    }
    private void withdrawItems() {
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        mMain.state = "Withdraw items";
        if (!Bank.opened()) {
            Bank.open();
            Condition.wait( () -> Bank.opened(), 500, 50);
        }
        if (Inventory.stream().id(ToolID).isNotEmpty()) {
            Bank.depositAllExcept(ToolID);
            interactionsHelper.withdrawItem(CombineWithItemID, 27);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 500, 50);
        }
    }
    private void fletch() {
        while (Inventory.stream().id(CombineWithItemID).count() >= 1) {
            InteractionsHelper interactionsHelper = new InteractionsHelper();
            interactionsHelper.combineItems(ToolID, CombineWithItemID, WidgetID, ComponentID);
        }
    }
}
