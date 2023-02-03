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

public class DoLongbow extends Task {
    int CombineWithItemID = ItemList.LOGS_1511;
    int ToolID = ItemList.KNIFE_946;
    int WidgetID = 270;
    int ComponentID = 17;

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_FLETCHING) >= 10 && Skills.realLevel(Constants.SKILLS_FLETCHING) < 20;
    }

    @Override
    public void execute() {
        if (Inventory.stream().id(CombineWithItemID).count() == 0 && Game.tab(Game.Tab.INVENTORY)) {
            mMain.State = "Banking loop";
            bank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && !Bank.opened() && Inventory.stream().id(CombineWithItemID).count() > 0) {
            mMain.State = "Fletch loop";
            fletch();
        }
        if (ScriptManager.INSTANCE.isStopping()) {
            ScriptManager.INSTANCE.stop();
        }
    }

    private void bank() {
        checkKnife();
        withdrawItems();
    }

    private void checkKnife() {
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        mMain.State = "Checking tool..";
        if (Inventory.stream().id(ToolID).count() == 0) {
            interactionsHelper.CheckInventoryItemAndWithdraw(ToolID);
        }

    }
    private void withdrawItems() {
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        mMain.State = "Withdraw items";
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
            InteractionsHelper interactionsHelper = new InteractionsHelper();
            interactionsHelper.CombineItems(ToolID, CombineWithItemID, WidgetID, ComponentID);
        }
    }
}
