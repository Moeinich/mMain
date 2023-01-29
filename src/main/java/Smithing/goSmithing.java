package Smithing;

import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import Assets.Task;
import Assets.skillData;
import script.mMain;

public class goSmithing extends Task {
    @Override
    public boolean activate() {
        if (!skillData.smithingAreaEdgeville.contains(Players.local().tile()) && Inventory.stream().id(skillData.smithingOres).count() == 28) {
            return true;
        }
        if (!skillData.smithingTileVarrockWest.equals(Players.local().tile()) && Inventory.stream().id(skillData.bronzeBar).count() == 27) {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        mMain.scriptStatus = "Go smithing";
        if (Inventory.stream().id(skillData.smithingOres).count() == 28) {
            Movement.builder(skillData.smithingAreaEdgeville.getRandomTile()).setRunMin(45).setRunMax(75).move();
        }
        if (Inventory.stream().id(skillData.smithingBars).count() == 27) {
            Movement.builder(skillData.smithingTileVarrockWest).setRunMin(45).setRunMax(75).move();
        }
    }
}