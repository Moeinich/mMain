package src.PastShadie.scripts.mMain.Cooking;

import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class depositFish extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() && Bank.opened() &&
                        Inventory.stream().id(ItemList.RAW_SHRIMPS_317,
                        ItemList.RAW_ANCHOVIES_321,
                        ItemList.RAW_TROUT_335,
                        ItemList.RAW_SALMON_331).count() == 0;
    }
    @Override
    public void execute() {
        Bank.depositInventory();
    }
}