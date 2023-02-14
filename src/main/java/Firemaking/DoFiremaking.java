package Firemaking;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

import Helpers.ItemList;
import Helpers.PlayerHelper;
import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class DoFiremaking extends Task {
    public static int fmSpot = 1;
    int CurrentXP = Skills.experience(Skill.Firemaking);
    @Override
    public boolean activate() {
        return Inventory.stream().id(SkillData.logs).isNotEmpty() && Inventory.stream().id(ItemList.TINDERBOX_590).isNotEmpty();
    }
    @Override
    public boolean execute() {
        if (!PlayerHelper.withinArea(SkillData.firemakingStartArea) && Inventory.stream().id(SkillData.logs).count() >= 27) {
            walkToFMSpot();
        }
        if (PlayerHelper.withinArea(SkillData.doFiremakingArea)) {
            lightLogs();
        }
        return false;
    }

    private void walkToFMSpot() {
            mMain.State = "Go to lane " + fmSpot;
            PlayerHelper.walkToTile(SkillData.moveToFiremakingSpot());
    }
    private void lightLogs() {
        if (Game.tab(Game.Tab.INVENTORY)) {
                mMain.State = "Lighting.. " + "L:" + fmSpot;
                if (Inventory.stream().id(SkillData.logs).first().interact("Use")) {
                    if (Inventory.stream().id(ItemList.TINDERBOX_590).first().interact("Use")) {
                        CurrentXP = Skills.experience(Skill.Firemaking);
                        Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Firemaking) || !PlayerHelper.withinArea(SkillData.doFiremakingArea)), 500, 50);
                    }
                }
                if (Inventory.stream().id(SkillData.logs).isEmpty()) {
                    mMain.State = "Switch lane";
                    fmSpot += 1;
                }
                if (fmSpot == 4) {
                    fmSpot = 1;
                }
        }
    }
}