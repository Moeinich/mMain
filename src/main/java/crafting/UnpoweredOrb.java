package crafting;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import helpers.InteractionsHelper;
import helpers.ItemList;
import helpers.Task;
import script.mMain;

public class UnpoweredOrb extends Task {
    int CombineWithItemID = ItemList.MOLTEN_GLASS_1775;
    int ToolID = ItemList.GLASSBLOWING_PIPE_1785;
    int WidgetID = 270;
    int ComponentID = 19;

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_CRAFTING) >= 46;
    }

    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY) && (Inventory.stream().id(ToolID).isEmpty() || Inventory.stream().id(CombineWithItemID).isEmpty())) {
            mMain.state = "Banking loop";
            bank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && !Bank.opened() && Inventory.stream().id(CombineWithItemID).isNotEmpty()) {
            mMain.state = "craft loop";
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
        mMain.state = "Checking tool..";
        if (Inventory.stream().id(ToolID).isEmpty()) {
            InteractionsHelper.depositAndWithdraw(ToolID, 1);
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
            InteractionsHelper.withdrawItem(CombineWithItemID, 27);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 500, 50);
        }
    }
    private void craft() {
        while (Inventory.stream().id(CombineWithItemID).count() >= 1) {
            InteractionsHelper.combineItems(ToolID, CombineWithItemID, WidgetID, ComponentID);
        }
    }
}