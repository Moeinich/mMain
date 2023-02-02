package Woodcutting;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class GetAxe extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && Inventory.stream().id(SkillData.wcAxes).count() == 0;
    }
    @Override
    public void execute() {
        mMain.State = "Getting an axe";
        int amountToWithdraw = 1;

        Bank.depositInventory();
        Bank.withdraw(SkillData.withdrawAxe(), amountToWithdraw);
    }
}