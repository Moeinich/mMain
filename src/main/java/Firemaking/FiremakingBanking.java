package Firemaking;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Helpers.ItemList;
import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class FiremakingBanking extends Task {
    @Override
    public boolean activate() {
        return Inventory.stream().id(SkillData.logs).isEmpty()
                && !Bank.opened()
                || Inventory.stream().id(ItemList.TINDERBOX_590).isEmpty();
    }
    @Override
    public void execute() {
        mMain.State = "Banking";
        Locatable nearestBank = Bank.nearest();
        if (Bank.inViewport() && nearestBank.tile().distanceTo(Players.local()) < 4) {
            Bank.open();
        } else {
            Movement.moveToBank();
        }
    }
}