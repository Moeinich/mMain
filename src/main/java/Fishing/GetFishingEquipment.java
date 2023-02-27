package Fishing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.dax.api.DaxWalker;

import Helpers.InteractionsHelper;
import Helpers.ItemList;
import Helpers.Task;
import script.mMain;

public class GetFishingEquipment extends Task {
    @Override
    public boolean activate() {
        if (Skill.Fishing.realLevel() <= 19 && Game.tab(Game.Tab.INVENTORY) && Inventory.stream().name("Small fishing net").isEmpty()) {
            return true;
        }
        if (Skill.Fishing.realLevel() >= 20 && Game.tab(Game.Tab.INVENTORY) && Inventory.stream().name("Fly fishing rod").isEmpty() && Inventory.stream().name("Feather").isEmpty()) {
            return true;
        }
        return false;
    }
    @Override
    public boolean execute() {
        mMain.state = "Go to bank";
        if (Bank.nearest().tile().distanceTo(Players.local()) > 4) {
            DaxWalker.walkToBank();
        }

        if (Bank.nearest().tile().distanceTo(Players.local()) <= 6 && Bank.inViewport() && !Bank.opened()) {
            mMain.state = "Get equipment";
            if (Skills.realLevel(Constants.SKILLS_FISHING) <= 19) {
                InteractionsHelper.depositAndWithdraw(ItemList.SMALL_FISHING_NET_303, 1);
                Bank.close();
                Condition.wait( () -> !Bank.opened(), 250, 50);
            }
            if (Skills.realLevel(Constants.SKILLS_FISHING) >= 20) {
                if (Inventory.stream().id(ItemList.FLY_FISHING_ROD_309).isEmpty()) {
                    InteractionsHelper.depositAndWithdraw(ItemList.FLY_FISHING_ROD_309, 1);
                    Condition.wait( () -> Inventory.stream().name("Fly fishing rod").isNotEmpty(),150, 50);
                }
                if (Inventory.stream().id(ItemList.FEATHER_314).isEmpty()) {
                    InteractionsHelper.withdrawItem(ItemList.FEATHER_314, -1);
                    Condition.wait( () -> Inventory.stream().name("Feather").isNotEmpty(),150, 50);
                }
                Bank.close();
                Condition.wait( () -> !Bank.opened(), 250, 50);
            }
        }
        return false;
    }
}
