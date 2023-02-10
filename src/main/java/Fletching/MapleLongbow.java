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

public class MapleLongbow extends Task {
    int CombineWithItemID = ItemList.MAPLE_LOGS_1517;
    int ToolID = ItemList.KNIFE_946;
    int BowStringID = ItemList.BOW_STRING_1777;
    int BowID = ItemList.MAPLE_LONGBOW_U_62;
    int WidgetID = 270;
    int ComponentID = 16;
    int StringComponentID = 14;


    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_FLETCHING) >= 55 && Skills.realLevel(Constants.SKILLS_FLETCHING) < 70;
    }

    @Override
    public void execute() {
        if (Game.tab(Game.Tab.INVENTORY) && !Bank.opened() && Inventory.stream().id(CombineWithItemID).isNotEmpty()) {
            mMain.State = "Fletching bows";
            fletch();
        }
        if (Game.tab(Game.Tab.INVENTORY) && !Bank.opened() && Inventory.stream().id(BowStringID).isNotEmpty()) {
            mMain.State = "Stringing bows";
            stringing();
        }

        if (Game.tab(Game.Tab.INVENTORY) && (Inventory.stream().id(ToolID).isEmpty() || Inventory.stream().id(CombineWithItemID).isEmpty()) || Inventory.stream().id(BowID).isEmpty()) {
            if (!Bank.opened()) {
                Bank.open();
            }
            if (Bank.stream().id(CombineWithItemID).isNotEmpty()) {
                mMain.State = "Banking loop";
                BankForFletching();
            }
            if (Bank.stream().id(BowID).isNotEmpty() && Bank.stream().id(BowStringID).isNotEmpty()) {
                mMain.State = "Bank Stringing";
                BankForStringing();
            }
        }
        if (ScriptManager.INSTANCE.isStopping()) {
            ScriptManager.INSTANCE.stop();
        }
    }

    private void BankForFletching() {
        checkTool();
        withdrawItems();
    }
    private void BankForStringing() {
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        interactionsHelper.DepositAndWithdraw(BowID, 14);
        interactionsHelper.WithdrawItem(BowStringID, 14);
        Bank.close();
        Condition.wait( () -> !Bank.opened(), 500, 50);
    }

    private void checkTool() {
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        mMain.State = "Checking tool..";
        if (Inventory.stream().id(ToolID).isEmpty()) {
            interactionsHelper.DepositAndWithdraw(ToolID, 1);
        }

    }
    private void withdrawItems() {
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        mMain.State = "Withdraw items";
        if (!Bank.opened()) {
            Bank.open();
            Condition.wait( () -> Bank.opened(), 500, 50);
        }
        if (Inventory.stream().id(ToolID).isNotEmpty()) {
            Bank.depositAllExcept(ToolID);
            interactionsHelper.WithdrawItem(CombineWithItemID, 27);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 500, 50);
        }
    }
    private void fletch() {
        while (Inventory.stream().id(CombineWithItemID).count() >= 1) {
            InteractionsHelper interactionsHelper = new InteractionsHelper();
            interactionsHelper.CombineItems(ToolID, CombineWithItemID, WidgetID, ComponentID);
        }
    }
    private void stringing() {
        while (Inventory.stream().id(BowStringID).count() >= 1) {
            InteractionsHelper interactionsHelper = new InteractionsHelper();
            interactionsHelper.CombineItems(BowID, BowStringID, WidgetID, StringComponentID);
        }
    }
}
