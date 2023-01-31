package Fishing;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Assets.ItemList;
import Assets.Task;
import script.mMain;

public class depositFish extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() && Bank.opened();
    }
    @Override
    public void execute() {
        mMain.State = "Deposit fish";
        Bank.depositAllExcept(ItemList.SMALL_FISHING_NET_303, ItemList.FLY_FISHING_ROD_309, ItemList.FEATHER_314);
    }
}