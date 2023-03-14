package herblore;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import org.powbot.mobile.script.ScriptManager;

import helpers.InteractionsHelper;
import helpers.PlayerHelper;
import helpers.extentions.ItemList;
import helpers.extentions.Task;
import script.mMain;

public class AttackPotions extends Task {
    int CombineWithItemID = ItemList.GUAM_POTION_UNF_91;
    int ToolID = ItemList.EYE_OF_NEWT_221;
    int WidgetID = 270;
    int ComponentID = 14;

    public boolean activate() {
        return  Bank.nearest().tile().distanceTo(Players.local()) < 10;
    }
    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY) && (!PlayerHelper.hasItem(ToolID) || !PlayerHelper.hasItem(CombineWithItemID))) {
            mMain.state = "Banking loop";
            bank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && !Bank.opened() && PlayerHelper.hasItem(ToolID, CombineWithItemID)) {
            mMain.state = "craft loop";
            craft();
        }
        if (ScriptManager.INSTANCE.isStopping()) {
            ScriptManager.INSTANCE.stop();
        }
        return false;
    }

    private void bank() {
        GetEyes();
        GetUnfinishedPotions();
    }

    private void GetEyes() {
        mMain.state = "Grabbing eyes";
        if (!PlayerHelper.hasItem(ToolID)) {
            InteractionsHelper.depositAndWithdraw(ToolID, 14);
        }

    }
    private void GetUnfinishedPotions() {
        mMain.state = "Grabbing Guam pot(unf)";
        if (PlayerHelper.hasItem(ToolID)) {
            Bank.depositAllExcept(ToolID);
            InteractionsHelper.withdrawItem(CombineWithItemID, 14);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 500, 50);
        }
    }
    private void craft() {
        while (Inventory.stream().id(CombineWithItemID).isNotEmpty() && Inventory.stream().id(ToolID).isNotEmpty()) {
            InteractionsHelper.combineItems(ToolID, CombineWithItemID, WidgetID, ComponentID);
        }
    }
}
