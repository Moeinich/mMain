package Smithing;

import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class GoSmithing extends Task {
    @Override
    public boolean activate() {
        if (!SkillData.smithingAreaEdgeville.contains(Players.local().tile()) && Inventory.stream().id(SkillData.smithingOres).count() == 28) {
            return true;
        }
        if (!SkillData.smithingTileVarrockWest.equals(Players.local().tile()) && Inventory.stream().id(SkillData.bronzeBar).count() == 27) {
            return true;
        }
        return false;
    }

    @Override
    public boolean execute() {
        mMain.state = "Go smithing";
        if (Inventory.stream().id(SkillData.smithingOres).count() == 28) {
            Movement.builder(SkillData.smithingAreaEdgeville.getRandomTile()).setRunMin(45).setRunMax(75).move();
        }
        if (Inventory.stream().id(SkillData.smithingBars).count() == 27) {
            Movement.builder(SkillData.smithingTileVarrockWest).setRunMin(45).setRunMax(75).move();
        }
        return false;
    }
}