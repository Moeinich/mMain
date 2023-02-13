package Fishing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.PlayerHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class BarbarianVillageFishing extends Task {
    @Override
    public boolean activate() {
        return  Skills.realLevel(Constants.SKILLS_FISHING) >= 20 && Inventory.stream().name("Fly fishing rod").isNotEmpty() && Inventory.stream().name("Feather").isNotEmpty();
    }
    @Override
    public void execute() {
        if (Skills.realLevel(Constants.SKILLS_FISHING) >= 70) {
            mMain.State = "Fishing done!";
            SkillData.SetSkillDone();
            mMain.taskRunning.set(false);
        }

        if (!PlayerHelper.WithinArea(SkillData.BarbarianVillageFishingArea)) {
            mMain.State = "Go to fishing area";
            PlayerHelper.WalkToTile(SkillData.movementFishing());
        }

        if (PlayerHelper.WithinArea(SkillData.BarbarianVillageFishingArea)) {
            mMain.State = "Do fishing";
            Npc BarbarianVillageFishingSpot = Npcs.stream().name("Rod Fishing spot").nearest().first();
            if (BarbarianVillageFishingSpot.inViewport() && Players.local().animation() == -1) {
                BarbarianVillageFishingSpot.interact("Lure", "Rod Fishing spot");
                Condition.wait(() -> Npcs.stream().at(BarbarianVillageFishingSpot.tile()).isEmpty(), 150, 50);
            }
        }
    }
}
