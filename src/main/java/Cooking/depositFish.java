package Cooking;

import Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import Assets.skillData;
import script.mMain;

public class depositFish extends Task {
    @Override
    public boolean activate() {
        System.out.print("Activate deposit fish");
        return Inventory.isFull() && Bank.opened() && Inventory.stream().id(skillData.cookedFish).count() >= 1;
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Depositing fish";
        Bank.depositInventory();
    }
}