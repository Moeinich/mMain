package Firemaking;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Assets.ItemList;
import Assets.Task;
import Assets.skillData;
import script.mMain;

public class firemakingBanking extends Task {
    @Override
    public boolean activate() {
        return Inventory.stream().id(skillData.logs).count() == 0
                && !Bank.opened()
                || Inventory.stream().id(ItemList.TINDERBOX_590).count() == 0;
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Banking";
        Locatable nearestBank = Bank.nearest();
        if (Bank.inViewport() && nearestBank.tile().distanceTo(Players.local()) < 4) {
            Bank.open();
        } else {
            Movement.moveToBank();
        }
    }
}