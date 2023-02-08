package Herblore;

import org.powbot.api.Condition;
import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Varpbits;
import org.powbot.mobile.script.ScriptManager;

import Helpers.InteractionsHelper;
import Helpers.ItemList;
import Helpers.Task;
import script.mMain;

public class AttackPotions extends Task {
    private enum DRUIDIC_RITUAL {
        DRUIDIC_RITUAL(80); //completed er = 4!
        int var;
        DRUIDIC_RITUAL(int var){
            this.var = var;
        }
        public int getValue(){
            return (int) (Varpbits.value(var) * .1);
        }
        public static boolean checkedCompletion = false;
        public static int DruidicRitualValue = 0;
    }

    int CombineWithItemID = ItemList.GUAM_POTION_UNF_91;
    int ToolID = ItemList.EYE_OF_NEWT_221;
    int WidgetID = 270;
    int ComponentID = 14;

    public boolean activate() {
        Locatable nearestBank = Bank.nearest();
        return nearestBank.tile().distanceTo(Players.local()) < 10;
    }
    @Override
    public void execute() {
        //We need to check if Druidic ritual is completed
        if (!DRUIDIC_RITUAL.checkedCompletion) {
            mMain.State = "Checking quest completion";
            DRUIDIC_RITUAL.DruidicRitualValue = DRUIDIC_RITUAL.DRUIDIC_RITUAL.getValue();
            DRUIDIC_RITUAL.checkedCompletion = true;
        }
        if (DRUIDIC_RITUAL.DruidicRitualValue != 4) {
            mMain.taskRunning.set(false);
        }

        if (Game.tab(Game.Tab.INVENTORY) && (Inventory.stream().id(ToolID).count() == 0 || Inventory.stream().id(CombineWithItemID).count() == 0)) {
            mMain.State = "Banking loop";
            bank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && !Bank.opened() && Inventory.stream().id(CombineWithItemID).count() > 0) {
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
        if (!Bank.opened() && Bank.inViewport()) {
            Bank.open();
        }
        if (Bank.stream().id(CombineWithItemID).isNotEmpty() && Inventory.stream().id(ToolID).isNotEmpty()) {
            interactionsHelper.WithdrawItem(CombineWithItemID, 14);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 500, 20);
        }
        else {
            mMain.State = "Out of materials, switching..";
            Bank.close();
            mMain.taskRunning.set(false); //Stop if we dont have materials!
        }
    }
    private void craft() {
        while (Inventory.stream().id(CombineWithItemID).count() >= 1) {
            InteractionsHelper interactionsHelper = new InteractionsHelper();
            interactionsHelper.CombineItems(ToolID, CombineWithItemID, WidgetID, ComponentID);
        }
    }
}
