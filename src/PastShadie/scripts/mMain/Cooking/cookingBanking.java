package src.PastShadie.scripts.mMain.Cooking;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class cookingBanking extends Task {
    @Override
    public boolean activate() {
        return Inventory.stream().id(ItemList.RAW_SHRIMPS_317,
                                     ItemList.RAW_ANCHOVIES_321,
                                     ItemList.RAW_TROUT_335,
                                     ItemList.RAW_SALMON_331).count() == 0;
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