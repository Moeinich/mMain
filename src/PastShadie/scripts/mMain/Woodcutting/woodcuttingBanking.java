package src.PastShadie.scripts.mMain.Woodcutting;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.mMain;

public class woodcuttingBanking extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() || Inventory.stream().id(ItemList.BRONZE_AXE_1351, ItemList.STEEL_AXE_1353, ItemList.MITHRIL_AXE_1355, ItemList.ADAMANT_AXE_1357, ItemList.RUNE_AXE_1359).count() == 0;
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Banking";
        Locatable nearestBank = Bank.nearest();
        if (Bank.inViewport() && nearestBank.tile().distanceTo(Players.local()) < 2) {
            Bank.open();
        } else {
            Movement.moveToBank();
        }
    }
}