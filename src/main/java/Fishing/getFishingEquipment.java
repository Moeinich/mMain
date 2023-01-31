package Fishing;

import org.powbot.api.Condition;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

import Assets.ItemList;
import Assets.Task;
import Assets.skillData;
import script.mMain;

public class getFishingEquipment extends Task {
    @Override
    public boolean activate() {
        if (Bank.open() && Inventory.stream().id(ItemList.SMALL_FISHING_NET_303).count() == 0 && Skill.Fishing.realLevel() <= 19) {
            return true;
        }
        if (Bank.open() && Inventory.stream().id(ItemList.FLY_FISHING_ROD_309).count() == 0 && Inventory.stream().id(ItemList.FEATHER_314).count() == 0 && Skill.Fishing.realLevel() >= 20) {
            return true;
        }
        return false;
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Get equipment";

        if (Skills.realLevel(Constants.SKILLS_FISHING) <= 19) {
                Condition.wait(Bank::open, 50, 10);
                Bank.depositInventory();
                Bank.withdraw(ItemList.SMALL_FISHING_NET_303, 1);
        }

        if (Skills.realLevel(Constants.SKILLS_FISHING) >= 20) {
                Bank.depositInventory();
                Bank.withdraw(ItemList.FLY_FISHING_ROD_309, 1);
                Bank.withdraw(ItemList.FEATHER_314, Bank.Amount.ALL);
        }
    }
}
