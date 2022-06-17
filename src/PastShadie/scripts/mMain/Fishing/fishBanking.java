package src.PastShadie.scripts.mMain.Fishing;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.*;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;

public class fishBanking extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() || (Skills.realLevel(Constants.SKILLS_FISHING) >= 20 && Inventory.stream().id(ItemList.SMALL_FISHING_NET_303).count() >= 1);
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