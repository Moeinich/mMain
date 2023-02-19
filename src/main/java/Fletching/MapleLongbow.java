package Fletching;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import Helpers.interactionHelper;
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
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY) && !Bank.opened() && Inventory.stream().id(CombineWithItemID).isNotEmpty()) {
            mMain.state = "Fletching bows";
            fletch();
        }
        if (Game.tab(Game.Tab.INVENTORY) && !Bank.opened() && Inventory.stream().id(BowStringID).isNotEmpty()) {
            mMain.state = "Stringing bows";
            stringing();
        }

        if (Game.tab(Game.Tab.INVENTORY) && (Inventory.stream().id(ToolID).isEmpty() || Inventory.stream().id(CombineWithItemID).isEmpty()) || Inventory.stream().id(BowID).isEmpty()) {
            if (!Bank.opened()) {
                Bank.open();
            }
            if (Bank.stream().id(CombineWithItemID).isNotEmpty()) {
                mMain.state = "Banking loop";
                BankForFletching();
            }
            if (Bank.stream().id(BowID).isNotEmpty() && Bank.stream().id(BowStringID).isNotEmpty()) {
                mMain.state = "Bank Stringing";
                BankForStringing();
            }
        }
        if (ScriptManager.INSTANCE.isStopping()) {
            ScriptManager.INSTANCE.stop();
        }
        return false;
    }

    private void BankForFletching() {
        checkTool();
        withdrawItems();
    }
    private void BankForStringing() {
        interactionHelper.depositAndWithdraw(BowID, 14);
        interactionHelper.withdrawItem(BowStringID, 14);
        Bank.close();
        Condition.wait( () -> !Bank.opened(), 500, 50);
    }

    private void checkTool() {
        mMain.state = "Checking tool..";
        if (Inventory.stream().id(ToolID).isEmpty()) {
            interactionHelper.depositAndWithdraw(ToolID, 1);
        }

    }
    private void withdrawItems() {
        mMain.state = "Withdraw items";
        if (!Bank.opened()) {
            Bank.open();
            Condition.wait(Bank::opened, 500, 50);
        }
        if (Inventory.stream().id(ToolID).isNotEmpty()) {
            Bank.depositAllExcept(ToolID);
            interactionHelper.withdrawItem(CombineWithItemID, 27);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 500, 50);
        }
    }
    private void fletch() {
        while (Inventory.stream().id(CombineWithItemID).count() >= 1) {
            interactionHelper.combineItems(ToolID, CombineWithItemID, WidgetID, ComponentID);
        }
    }
    private void stringing() {
        while (Inventory.stream().id(BowStringID).count() >= 1) {
            interactionHelper.combineItems(BowID, BowStringID, WidgetID, StringComponentID);
        }
    }
}
