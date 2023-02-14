package Firemaking;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Skills;

import Helpers.InteractionsHelper;
import Helpers.ItemList;
import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class GetMaterials extends Task {
    @Override
    public boolean activate() {
        return Inventory.stream().id(SkillData.logs).isEmpty() || Inventory.stream().id(ItemList.TINDERBOX_590).isEmpty();
    }
    @Override
    public boolean execute() {
        if (Skills.realLevel(Constants.SKILLS_FIREMAKING) >= 70) {
            mMain.State = "Firemaking done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        mMain.State = "Banking";
        Locatable nearestBank = Bank.nearest();
        if (Bank.inViewport() && nearestBank.tile().distanceTo(Players.local()) < 4) {
            Bank.open();
        } else {
            Movement.moveToBank();
        }

        if (Bank.opened() && Inventory.stream().id(ItemList.TINDERBOX_590).isEmpty()) {
            mMain.State = "Withdraw Tinderbox";
            InteractionsHelper interactionsHelper = new InteractionsHelper();
            interactionsHelper.depositAndWithdraw(ItemList.TINDERBOX_590, 1);
        }
        if (Bank.opened() && Inventory.stream().id(SkillData.logs).isEmpty()) {
            mMain.State = "Withdraw logs";
            if (Bank.stream().id(ItemList.LOGS_1511).isNotEmpty() || Bank.stream().id(ItemList.OAK_LOGS_1521).isNotEmpty() || Bank.stream().id(ItemList.WILLOW_LOGS_1519).isNotEmpty()) {
                InteractionsHelper interactionsHelper = new InteractionsHelper();
                interactionsHelper.withdrawItem(SkillData.withdrawLogs(), 27);
                Bank.close();
            }
        }
        return false;
    }
}