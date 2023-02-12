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
    public void execute() {
        if (Game.tab(Game.Tab.INVENTORY)) {
            if (!PlayerHelper.WithinArea(SkillData.firemakingStartArea) && Inventory.stream().id(SkillData.logs).count() >= 27) {
                mMain.State = "Go to lane " + fmSpot;
                PlayerHelper.WalkToTile(SkillData.moveToFiremakingSpot());
            }

            if (PlayerHelper.WithinArea(SkillData.doFiremakingArea)) {
                mMain.State = "Lighting.. " + "L:" + fmSpot;
                if (Inventory.stream().id(SkillData.logs).first().interact("Use")) {
                    if (Inventory.stream().id(ItemList.TINDERBOX_590).first().interact("Use")) {
                        CurrentXP = Skills.experience(Skill.Firemaking);
                        Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Firemaking) || !PlayerHelper.WithinArea(SkillData.doFiremakingArea)), 500, 50);
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
}