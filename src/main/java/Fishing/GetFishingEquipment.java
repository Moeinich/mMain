package Fishing;

import org.powbot.api.Condition;
import org.powbot.api.Locatable;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

import Helpers.InteractionsHelper;
import Helpers.ItemList;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class GetFishingEquipment extends Task {
    @Override
    public boolean activate() {
        if (Skill.Fishing.realLevel() <= 19 && Inventory.stream().id(ItemList.SMALL_FISHING_NET_303).count() == 0) {
            return true;
        }
        if (Skill.Fishing.realLevel() >= 20 && Inventory.stream().id(ItemList.FLY_FISHING_ROD_309).count() == 0 || Inventory.stream().id(ItemList.FEATHER_314).count() == 0) {
            return true;
        }
        return false;
    }
    @Override
    public void execute() {
        Locatable nearestBank = Bank.nearest();
        mMain.State = "Go to bank";
        if (nearestBank.tile().distanceTo(Players.local()) < 4 && !Bank.inViewport()) {
            Movement.moveTo(nearestBank);
        }
        mMain.State = "Get equipment";
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        if (!Bank.opened()) {
            if (Skills.realLevel(Constants.SKILLS_FISHING) <= 19) {
                mMain.State = "getting small fishing net";
                Condition.wait(Bank::open, 50, 10);
                interactionsHelper.DepositAndWithdraw(ItemList.SMALL_FISHING_NET_303, 1);
                Bank.close();
                Condition.wait( () -> !Bank.opened(), 250, 50);
            }
            if (Skills.realLevel(Constants.SKILLS_FISHING) >= 20) {
                if (Inventory.stream().id(ItemList.FLY_FISHING_ROD_309).count() == 0) {
                    interactionsHelper.DepositAndWithdraw(ItemList.FLY_FISHING_ROD_309, 1);
                }
                if (Inventory.stream().id(ItemList.FEATHER_314).count() == 0) {
                    interactionsHelper.WithdrawItem(ItemList.FEATHER_314, 5000);
                }
                Bank.close();
            }
        }
    }
}
