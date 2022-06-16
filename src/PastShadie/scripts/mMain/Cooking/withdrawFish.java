package src.PastShadie.scripts.mMain.Cooking;

import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class withdrawFish extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && Inventory.isEmpty() &&
                        Inventory.stream().id(ItemList.SHRIMPS_315,
                        ItemList.ANCHOVIES_319,
                        ItemList.TROUT_333,
                        ItemList.SALMON_329).count() == 0;
    }
    @Override
    public void execute() {
        //Withdraw some fish...
    }
}