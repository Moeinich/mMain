package src.PastShadie.scripts.mMain.Cooking;

import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class depositFish extends Task {
    @Override
    public boolean activate() {
        System.out.print("Activate deposit fish");
        return Inventory.isFull() && Bank.opened();
    }
    @Override
    public void execute() {
        Bank.depositInventory();
    }
}