package Smithing;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class SmithingBanking extends Task {
    @Override
    public boolean activate() {
        return !Bank.opened()
                && Inventory.stream().id(SkillData.smithingOres).count() == 0
                && Inventory.stream().id(SkillData.smithingBars).count() == 0;
    }


    @Override
    public boolean execute() {
        mMain.State = "Banking";
        Locatable nearestBank = Bank.nearest();

        if (Bank.inViewport() && nearestBank.tile().distanceTo(Players.local()) < 4) {
            Bank.open();
        } else {
            Movement.builder(nearestBank).setRunMin(45).setRunMax(75).move();
        }
        return false;
    }
}