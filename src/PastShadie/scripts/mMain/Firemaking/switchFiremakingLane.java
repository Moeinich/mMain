package src.PastShadie.scripts.mMain.Firemaking;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class switchFiremakingLane extends Task {
    @Override
    public boolean activate() {
        return Inventory.stream().id(skillData.logs).count() == 0;
    }

    @Override
    public void execute() {
        System.out.println("Current lane: " + goFiremaking.fmSpot);

        if (Inventory.stream().id(skillData.logs).count() == 0) {
            goFiremaking.fmSpot += 1;
        }
        if (goFiremaking.fmSpot == 4) {
            goFiremaking.fmSpot = 1;
        }
    }
}