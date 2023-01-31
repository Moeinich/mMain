package Smithing;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import Assets.Task;
import Assets.skillData;
import script.mMain;

public class smithingBanking extends Task {
    @Override
    public boolean activate() {
        return !Bank.opened()
                && Inventory.stream().id(skillData.smithingOres).count() == 0
                && Inventory.stream().id(skillData.smithingBars).count() == 0;
    }


    @Override
    public void execute() {
        mMain.State = "Banking";
        Locatable nearestBank = Bank.nearest();

        if (Bank.inViewport() && nearestBank.tile().distanceTo(Players.local()) < 4) {
            Bank.open();
        } else {
            Movement.builder(nearestBank).setRunMin(45).setRunMax(75).move();
        }
    }
}