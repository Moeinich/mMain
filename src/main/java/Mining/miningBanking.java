package Mining;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import Assets.Task;
import Assets.skillData;
import script.mMain;

public class miningBanking extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() ||
               Inventory.stream().id(skillData.pickaxes).count() == 0;
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