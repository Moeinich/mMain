package Firemaking;

import org.powbot.api.Condition;
import org.powbot.api.event.SkillLevelUpEvent;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

import Helpers.ItemList;
import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class DoFiremaking extends Task {
    int CurrentXP = Skills.experience(Skill.Firemaking);
    @Override
    public boolean activate() {
        return Inventory.stream().id(SkillData.logs).isNotEmpty()
                && Inventory.stream().id(ItemList.TINDERBOX_590).isNotEmpty()
                && SkillData.doFiremakingArea.contains(Players.local());
    }
    @Override
    public void execute() {

        mMain.State = "Do firemaking";
        if (Game.tab(Game.Tab.INVENTORY)) {
            if (Inventory.stream().id(SkillData.logs).first().interact("Use")) {
                if (Inventory.stream().id(ItemList.TINDERBOX_590).first().interact("Use")) {
                    CurrentXP = Skills.experience(Skill.Firemaking);
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Firemaking)), 500, 50);
                }
            }
            if (Inventory.stream().id(SkillData.logs).isEmpty()) {
                GoFiremaking.fmSpot += 1;
            }
            if (GoFiremaking.fmSpot == 4) {
                GoFiremaking.fmSpot = 1;
            }
        }
    }
}