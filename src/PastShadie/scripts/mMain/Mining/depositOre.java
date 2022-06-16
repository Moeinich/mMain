package src.PastShadie.scripts.mMain.Mining;

import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class depositOre extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() && Bank.opened();
    }
    @Override
    public void execute() {
        if (Bank.opened()) {
            Bank.depositAllExcept(ItemList.BRONZE_PICKAXE_1265, ItemList.STEEL_PICKAXE_1269, ItemList.MITHRIL_PICKAXE_1273, ItemList.ADAMANT_PICKAXE_1271, ItemList.RUNE_PICKAXE_1275);
            Bank.close();
        }
    }
}