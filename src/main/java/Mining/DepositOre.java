package Mining;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;


public class DepositOre extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() && Bank.opened();
    }

    @Override
    public void execute() {
        mMain.State = "Deposit ores";
            Bank.depositAllExcept(SkillData.pickaxes);
            Bank.close();
    }
}