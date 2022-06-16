package src.PastShadie.scripts.mMain.Cooking;

import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class depositFish extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() && Bank.opened();
    }
    @Override
    public void execute() {
        //
    }
}