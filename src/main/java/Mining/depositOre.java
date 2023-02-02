package Mining;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Helpers.Task;
import Helpers.skillData;
import script.mMain;


public class depositOre extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() && Bank.opened();
    }

    @Override
    public void execute() {
        mMain.State = "Deposit ores";
            Bank.depositAllExcept(skillData.pickaxes);
            Bank.close();
    }
}