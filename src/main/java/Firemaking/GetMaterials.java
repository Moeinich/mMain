package Firemaking;

import org.powbot.api.Condition;
import org.powbot.api.Locatable;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

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
                Condition.wait( () -> (Inventory.stream().id(SkillData.logs).isNotEmpty()), 250, 50);
                Bank.close();
            }
        }
        return false;
    }
}