package Cooking;

import Helpers.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import Helpers.SkillData;
import script.mMain;

public class DepositFish extends Task {
    @Override
    public boolean activate() {
        System.out.print("Activate deposit fish");
        return Inventory.isFull() && Bank.opened() && Inventory.stream().id(SkillData.cookedFish).count() >= 1;
    }
    @Override
    public void execute() {
        mMain.State = "Depositing fish";
        Bank.depositInventory();
    }
}