package Woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;

import Helpers.InteractionsHelper;
import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class GetAxe extends Task {

    @Override
    public boolean activate() {
        return Inventory.stream().id(SkillData.wcAxes).count() == 0;
    }
    @Override
    public void execute() {
        Locatable nearestBank = Bank.nearest();


        if (!Bank.inViewport()) {
            mMain.State = "Moving to bank";
            Movement.moveTo(nearestBank);
        }
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        if (Bank.opened()) {
            mMain.State = "getting axe";
            Bank.open();
            interactionsHelper.DepositAndWithdraw(SkillData.withdrawAxe(), 1);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 250, 50);
        }
    }
}