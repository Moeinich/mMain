package Fishing;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.*;

import Helpers.ItemList;
import Helpers.Task;
import script.mMain;

public class fishBanking extends Task {
    @Override
    public boolean activate() {
        return Inventory.stream().id(ItemList.SMALL_FISHING_NET_303, ItemList.FLY_FISHING_ROD_309).count() == 0
                || Inventory.isFull();
    }
    @Override
    public void execute() {
        mMain.State = "Banking";
        Locatable nearestBank = Bank.nearest();
        if (Bank.inViewport() && nearestBank.tile().distanceTo(Players.local()) < 4) {
            Bank.open();
        } else {
            Movement.moveToBank();
        }
    }
}