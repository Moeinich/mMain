package Firemaking;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Inventory;

import Assets.ItemList;
import Assets.Task;
import Assets.skillData;
import script.mMain;

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
        mMain.State = "Go to FM spot";
        Movement.builder(skillData.moveToFiremakingSpot()).setRunMin(45).setRunMax(75).move();
    }
}