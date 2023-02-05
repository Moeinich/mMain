package Mining;


import org.powbot.api.Condition;
import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.dax.api.DaxWalker;
import org.powbot.dax.teleports.Teleport;

import Helpers.InteractionsHelper;
import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class GetPickaxe extends Task {
    @Override
    public boolean activate() {
return Inventory.stream().id(SkillData.pickaxes).count() == 0;}

    @Override
    public void execute() {
        Locatable nearestBank = Bank.nearest();

        if (nearestBank.tile().distanceTo(Players.local()) > 5) {
            mMain.State = "Moving to bank";
            Movement.moveToBank();
        }

        InteractionsHelper interactionsHelper = new InteractionsHelper();
        if (!Bank.opened()) {
            mMain.State = "Getting pickaxe";
            Bank.open();
            interactionsHelper.DepositAndWithdraw(SkillData.withdrawPickaxe(), 1);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 250, 50);
        }
    }
}