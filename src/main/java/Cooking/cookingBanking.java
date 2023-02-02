package Cooking;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Helpers.Task;
import Helpers.skillData;
import script.mMain;

public class cookingBanking extends Task {
    @Override
    public boolean activate() {
        return (Inventory.stream().id(skillData.rawFish).count() == 0 && !Bank.opened());
    }
    @Override
    public void execute() {
        mMain.State = "Banking";
        Locatable nearestBank = Bank.nearest();
        if (Bank.inViewport() && nearestBank.tile().distanceTo(Players.local()) < 5) {
            Bank.open();
        } else {
            Movement.moveToBank();
        }
    }
}