package src.PastShadie.scripts.mMain.Woodcutting;

import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class depositLogs extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() && Bank.opened();
    }
    @Override
    public void execute() {
        System.out.println("We are depositing logs!");
        Bank.depositAllExcept(ItemList.BRONZE_AXE_1351, ItemList.STEEL_AXE_1353, ItemList.MITHRIL_AXE_1355, ItemList.ADAMANT_AXE_1357, ItemList.RUNE_AXE_1359);
    }
}