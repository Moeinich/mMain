package Fishing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class BarbarianVillageFishing extends Task {
    @Override
    public boolean activate() {
        return  Skills.realLevel(Constants.SKILLS_FISHING) >= 20;
    }
    @Override
    public void execute() {
        if (!SkillData.BarbarianVillageFishingArea.contains(Players.local())) {
            mMain.State = "Go to fishing area";
            Movement.builder(SkillData.movementFishing()).setRunMin(45).setRunMax(75).move();
        }

        if (SkillData.BarbarianVillageFishingArea.contains(Players.local())) {
            mMain.State = "Do fishing";
            Npc BarbarianVillageFishingSpot = Npcs.stream().name("Rod Fishing spot").nearest().first();
            if (BarbarianVillageFishingSpot.inViewport() && Players.local().animation() == -1) {
                BarbarianVillageFishingSpot.interact("Lure", "Rod Fishing spot");
                Condition.wait(() -> Npcs.stream().at(BarbarianVillageFishingSpot.tile()).isEmpty(), 150, 50);
            }
        }
    }
}
