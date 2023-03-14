package fletching;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import javax.tools.Tool;

import helpers.InteractionsHelper;
import helpers.PlayerHelper;
import helpers.extentions.ItemList;
import helpers.extentions.Task;
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
        if (Game.tab(Game.Tab.INVENTORY) && (!PlayerHelper.hasItem(ToolID) || !PlayerHelper.hasItem(CombineWithItemID))) {
            mMain.state = "Banking loop";
            bank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && !Bank.opened() && PlayerHelper.hasItem(CombineWithItemID)) {
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
        mMain.state = "Checking tool..";
        if (!PlayerHelper.hasItem(ToolID)) {
            InteractionsHelper.depositAndWithdraw(ToolID, 1);
        }

    }
    private void withdrawItems() {
        mMain.state = "Withdraw items";
        if (!Bank.opened()) {
            Bank.open();
            Condition.wait(Bank::opened, 150, 50);
        }
        if (PlayerHelper.hasItem(ToolID)) {
            Bank.depositAllExcept(ToolID);
            InteractionsHelper.withdrawItem(CombineWithItemID, -1);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 500, 50);
        }
    }
    private void fletch() {
        while (Inventory.stream().id(CombineWithItemID).count() >= 1) {
            InteractionsHelper.combineItems(ToolID, CombineWithItemID, WidgetID, ComponentID);
        }
    }
}
