package Smithing;

import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import Helpers.PlayerHelper;
import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class GoSmithing extends Task {
    @Override
    public boolean activate() {
        if (!PlayerHelper.withinArea(smithingData.smithingArea)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean execute() {
        PlayerHelper.walkToTile(smithingData.smithingArea.getRandomTile());
        return false;
    }
}