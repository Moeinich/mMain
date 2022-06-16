package src.PastShadie.scripts.mMain.Assets;

import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

public class doBanking extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() || Inventory.stream().id(ItemList.BRONZE_PICKAXE_1265, ItemList.STEEL_PICKAXE_1269, ItemList.MITHRIL_PICKAXE_1273, ItemList.ADAMANT_PICKAXE_1271, ItemList.RUNE_PICKAXE_1275).count() == 0;
    }

    @Override
    public void execute() {
        Locatable nearestBank = Bank.nearest();

        if (Bank.inViewport() && nearestBank.tile().distanceTo(Players.local()) < 2) {
            Bank.open();
        } else {
            Movement.moveToBank();
        }
    }
}