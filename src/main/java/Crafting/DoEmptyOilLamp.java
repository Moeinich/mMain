package Crafting;

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

public class DoEmptyOilLamp extends Task {
    int CombineWithItemID = ItemList.MOLTEN_GLASS_1775;
    int ToolID = ItemList.GLASSBLOWING_PIPE_1785;
    int WidgetID = 270;
    int ComponentID = 15;

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_CRAFTING) <= 12 && Skills.realLevel(Constants.SKILLS_CRAFTING) < 33;
    }

    @Override
    public void execute() {
        if (Inventory.stream().id(CombineWithItemID).count() == 0 && Game.tab(Game.Tab.INVENTORY)) {
            mMain.State = "Banking loop";
            bank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && !Bank.opened() && Inventory.stream().id(CombineWithItemID).contains()) {
            mMain.State = "craft loop";
            craft();
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
        mMain.State = "Check tool";
        if (Inventory.stream().id(ToolID).count() == 0) {
            interactionsHelper.CheckInventoryItemAndWithdraw(ToolID);
        }

    }
    private void withdrawLogs() {
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        mMain.State = "Withdraw items";
        if (!Bank.opened()) {
            Bank.open();
        }
        if (Inventory.stream().id(CombineWithItemID).count() == 0) {
            Bank.depositAllExcept(ToolID);
            interactionsHelper.WithdrawItem(CombineWithItemID, 27);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 500, 20);
        }
    }
    private void craft() {
        while (Inventory.stream().id(CombineWithItemID).count() >= 1) {
            CombineItems(ToolID, CombineWithItemID, WidgetID, ComponentID);
        }
    }
    public void CombineItems(int ToolID, int CombineWithItemID, int WidgetID, int ComponentID) {
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        interactionsHelper.CombineItems(ToolID, CombineWithItemID, WidgetID, ComponentID);
    }
}