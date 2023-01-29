package Firemaking;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Assets.ItemList;
import Assets.Task;
import script.mMain;

public class withdrawTinderbox extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && Inventory.stream().id(ItemList.TINDERBOX_590).count() == 0;
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Withdraw Tinderbox";
        Bank.depositInventory();
        Bank.withdraw(ItemList.TINDERBOX_590, 1);
    }
}