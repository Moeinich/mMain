package src.PastShadie.scripts.mMain.Firemaking;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

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
        mMain.scriptStatus = "Go to FM spot";
        Movement.builder(skillData.moveToFiremakingSpot()).setRunMin(45).setRunMax(75).move();
    }
}