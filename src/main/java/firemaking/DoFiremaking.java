package firemaking;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

import helpers.ItemList;
import helpers.PlayerHelper;
import helpers.Task;
import quests.Common.Conditions;
import script.mMain;

public class DoFiremaking extends Task {
    public static int fmSpot = 1;
    int CurrentXP = Skills.experience(Skill.Firemaking);
    @Override
    public boolean activate() {
        return Inventory.stream().id(FiremakingData.logs).isNotEmpty() && Inventory.stream().id(ItemList.TINDERBOX_590).isNotEmpty();
    }
    @Override
    public boolean execute() {
        if (!PlayerHelper.withinArea(FiremakingData.firemakingStartArea) && Inventory.stream().id(FiremakingData.logs).count() >= 27) {
            walkToFMSpot();
        }
        if (PlayerHelper.withinArea(FiremakingData.doFiremakingArea)) {
            lightLogs();
        }
        return false;
    }

    private void walkToFMSpot() {
            mMain.state = "Go to lane " + fmSpot;
            Movement.moveTo(FiremakingData.moveToFiremakingSpot());
            Condition.wait( () -> !Players.local().inMotion(), 900, 40);
    }
    private void lightLogs() {
        if (Game.tab(Game.Tab.INVENTORY)) {
                mMain.state = "Lighting.. " + "L:" + fmSpot;
                if (Inventory.stream().id(FiremakingData.logs).first().interact("Use")) {
                    if (Inventory.stream().id(ItemList.TINDERBOX_590).first().interact("Use")) {
                        Condition.wait( () -> (Conditions.expGained(Skill.Firemaking) || !PlayerHelper.withinArea(FiremakingData.doFiremakingArea)), 500, 50);
                    }
                }
                if (Inventory.stream().id(FiremakingData.logs).isEmpty()) {
                    mMain.state = "Switch lane";
                    fmSpot += 1;
                }
                if (fmSpot == 3) {
                    fmSpot = 1;
                }
        }
    }
}