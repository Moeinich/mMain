package Mining;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class MiningBanking extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull() ||
               Inventory.stream().id(SkillData.pickaxes).count() == 0;
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