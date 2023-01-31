package Woodcutting;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Assets.Task;
import Assets.skillData;
import script.mMain;

public class depositLogs extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() && Bank.opened();
    }
    @Override
    public void execute() {
        mMain.State = "Deposit logs";
        Bank.depositAllExcept(skillData.wcAxes);
    }
}