package Mining;


import org.powbot.api.Condition;
import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.dax.api.DaxWalker;

import Helpers.InteractionsHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class GetPickaxe extends Task {
    @Override
    public boolean activate() {
return Game.tab(Game.Tab.INVENTORY) && Inventory.stream().id(SkillData.pickaxes).isEmpty();}

    @Override
    public void execute() {
        Locatable nearestBank = Bank.nearest();

        if (nearestBank.tile().distanceTo(Players.local()) > 5) {
            mMain.State = "Moving to bank";
            DaxWalker.walkToBank();
        }
        if (!Bank.opened()) {
            mMain.State = "Open bank";
            Bank.open();
        }
        if (Bank.opened()) {
            mMain.State = "Get pickaxe";
            InteractionsHelper interactionsHelper = new InteractionsHelper();
            interactionsHelper.DepositAndWithdraw(SkillData.withdrawPickaxe(), 1);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 250, 50);
        }
    }
}