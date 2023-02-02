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

public class DoWillowLongbow extends Task {
    int CombineWithItemID = ItemList.WILLOW_LOGS_1519;
    int ToolID = ItemList.KNIFE_946;
    int WidgetID = 270;
    int ComponentID = 16;

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_FLETCHING) >= 40 && Skills.realLevel(Constants.SKILLS_FLETCHING) < 50;
    }

    @Override
    public void execute() {
        if (Inventory.stream().id(CombineWithItemID).count() == 0 && Game.tab(Game.Tab.INVENTORY)) {
            mMain.State = "Banking loop";
            bank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && !Bank.opened() && Inventory.stream().id(CombineWithItemID).contains()) {
            mMain.State = "Fletch loop";
            fletch();
        }
        if (ScriptManager.INSTANCE.isStopping()) {
            ScriptManager.INSTANCE.stop();
        }
    }

    private void bank() {
        checkTool();
        withdrawLogs();
    }

    private void checkTool() {
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        mMain.State = "Check knife";
        if (Inventory.stream().id(ToolID).count() == 0) {
            interactionsHelper.CheckInventoryItemAndWithdraw(ToolID);
        }

    }
    private void withdrawLogs() {
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        mMain.State = "Withdraw logs";
        if (!Bank.opened()) {
            Bank.open();
        }
        if (Bank.stream().id(CombineWithItemID).count() >= 1) {
            Bank.depositAllExcept(ToolID);
            interactionsHelper.WithdrawItem(CombineWithItemID, 27);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 500, 20);
        } else {
            Bank.close();
            mMain.taskRunning.set(false); //Stop if we dont have materials!
        }
    }
    private void fletch() {
        while (Inventory.stream().id(CombineWithItemID).count() >= 1) {
            CombineItems(ToolID, CombineWithItemID, WidgetID, ComponentID);
        }
    }
    public void CombineItems(int ToolID, int CombineWithItemID, int WidgetID, int ComponentID) {
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        interactionsHelper.CombineItems(ToolID, CombineWithItemID, WidgetID, ComponentID);
    }
}
