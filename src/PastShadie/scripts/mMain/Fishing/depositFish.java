package src.PastShadie.scripts.mMain.Fishing;

import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.mMain;

public class depositFish extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() && Bank.opened();
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Deposit fish";
        Bank.depositAllExcept(ItemList.SMALL_FISHING_NET_303, ItemList.FLY_FISHING_ROD_309, ItemList.FEATHER_314);
    }
}