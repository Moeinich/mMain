package Woodcutting;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class DepositLogs extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() && Bank.opened();
    }
    @Override
    public void execute() {
        mMain.State = "Deposit logs";
        Bank.depositAllExcept(SkillData.wcAxes);
    }
}