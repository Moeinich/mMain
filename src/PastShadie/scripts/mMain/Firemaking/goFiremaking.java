package src.PastShadie.scripts.mMain.Firemaking;

import org.powbot.api.rt4.Players;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class goFiremaking extends Task {
    @Override
    public boolean activate() {
        return Inventory.stream().id(skillData.logs).count() >= 27
                && Inventory.stream().id(ItemList.TINDERBOX_590).count() == 1
                && !skillData.firemakingArea.contains(Players.local());
    }
    @Override
    public void execute() {
        System.out.print("We are going to the firemaking area");

    }
}