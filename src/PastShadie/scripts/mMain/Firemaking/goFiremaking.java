package src.PastShadie.scripts.mMain.Firemaking;

import org.powbot.api.rt4.Movement;
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

    public static int fmSpot = 1;
    @Override
    public void execute() {
        System.out.print("We are going to the firemaking area");
        if (fmSpot == 1 && !skillData.firemakingGE1.equals(Players.local())) {
            Movement.builder(skillData.firemakingGE1).setRunMin(45).setRunMax(75).move();
        }
        if (fmSpot == 2 && !skillData.firemakingGE2.equals(Players.local())) {
            Movement.builder(skillData.firemakingGE2).setRunMin(45).setRunMax(75).move();
        }
        if (fmSpot == 3 && !skillData.firemakingGE2.equals(Players.local())) {
            Movement.builder(skillData.firemakingGE3).setRunMin(45).setRunMax(75).move();
        }
    }
}