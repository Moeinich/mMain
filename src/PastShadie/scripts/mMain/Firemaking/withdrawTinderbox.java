package src.PastShadie.scripts.mMain.Firemaking;

import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class withdrawTinderbox extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && Inventory.stream().id(ItemList.TINDERBOX_590).count() == 0;
    }
    @Override
    public void execute() {
        Bank.depositInventory();
        Bank.withdraw(ItemList.TINDERBOX_590, 1);
    }
}