package src.PastShadie.scripts.mMain.Fishing;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class fishBanking extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() || (Skills.realLevel(Constants.SKILLS_FISHING) >= 20 && Inventory.stream().id(ItemList.SMALL_FISHING_NET_303).count() >= 1);
    }
    @Override
    public void execute() {
        Bank.open();
    }
}