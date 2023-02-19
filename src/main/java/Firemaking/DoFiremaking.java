package Firemaking;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

import Helpers.ItemList;
import Helpers.playerHelper;
import Helpers.Task;
import script.mMain;

public class DoFiremaking extends Task {
    public static int fmSpot = 1;
    int CurrentXP = Skills.experience(Skill.Firemaking);
    @Override
    public boolean activate() {
        return Inventory.stream().id(firemakingData.logs).isNotEmpty() && Inventory.stream().id(ItemList.TINDERBOX_590).isNotEmpty();
    }
    @Override
    public boolean execute() {
        if (!playerHelper.withinArea(firemakingData.firemakingStartArea) && Inventory.stream().id(firemakingData.logs).count() >= 27) {
            walkToFMSpot();
        }
        if (playerHelper.withinArea(firemakingData.doFiremakingArea)) {
            lightLogs();
        }
        return false;
    }

    private void walkToFMSpot() {
            mMain.state = "Go to lane " + fmSpot;
            Movement.moveTo(firemakingData.moveToFiremakingSpot());
            Condition.wait( () -> !Players.local().inMotion(), 900, 100);
    }
    private void lightLogs() {
        if (Game.tab(Game.Tab.INVENTORY)) {
                mMain.state = "Lighting.. " + "L:" + fmSpot;
                if (Inventory.stream().id(firemakingData.logs).first().interact("Use")) {
                    if (Inventory.stream().id(ItemList.TINDERBOX_590).first().interact("Use")) {
                        CurrentXP = Skills.experience(Skill.Firemaking);
                        Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Firemaking) || !playerHelper.withinArea(firemakingData.doFiremakingArea)), 500, 50);
                    }
                }
                if (Inventory.stream().id(firemakingData.logs).isEmpty()) {
                    mMain.state = "Switch lane";
                    fmSpot += 1;
                }
                if (fmSpot == 3) {
                    fmSpot = 1;
                }
        }
    }
}