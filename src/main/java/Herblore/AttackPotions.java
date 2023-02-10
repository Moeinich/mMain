package Herblore;

import org.powbot.api.Condition;
import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Varpbits;
import org.powbot.mobile.script.ScriptManager;

import Helpers.InteractionsHelper;
import Helpers.ItemList;
import Helpers.Task;
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
    public void execute() {
        if (Skills.realLevel(Constants.SKILLS_HERBLORE) < 3) {
            mMain.State = "Druidic ritual not done";
            mMain.taskRunning.set(false);
        }

        if (Skills.realLevel(Constants.SKILLS_HERBLORE) >= 3 && Game.tab(Game.Tab.INVENTORY) && (Inventory.stream().id(ToolID).isEmpty() || Inventory.stream().id(CombineWithItemID).isEmpty())) {
            mMain.State = "Banking loop";
            bank();
        }
        if (Skills.realLevel(Constants.SKILLS_HERBLORE) >= 3 && Game.tab(Game.Tab.INVENTORY) && !Bank.opened() && Inventory.stream().id(CombineWithItemID, ToolID).isNotEmpty()) {
            mMain.State = "craft loop";
            craft();
        }
        if (ScriptManager.INSTANCE.isStopping()) {
            ScriptManager.INSTANCE.stop();
        }
    }

    private void bank() {
        GetEyes();
        GetUnfinishedPotions();
    }

    private void GetEyes() {
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        mMain.State = "Grabbing eyes";
        if (Inventory.stream().id(ToolID).count() == 0) {
            interactionsHelper.DepositAndWithdraw(ToolID, 14);
        }

    }
    private void GetUnfinishedPotions() {
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        mMain.State = "Grabbing Guam pot(unf)";
        if (Bank.stream().id(CombineWithItemID).isNotEmpty() && Inventory.stream().id(ToolID).isNotEmpty()) {
            interactionsHelper.WithdrawItem(CombineWithItemID, 14);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 500, 50);
        }
    }
    private void craft() {
        while (Inventory.stream().id(CombineWithItemID).count() >= 1) {
            InteractionsHelper interactionsHelper = new InteractionsHelper();
            interactionsHelper.CombineItems(ToolID, CombineWithItemID, WidgetID, ComponentID);
        }
    }
}
