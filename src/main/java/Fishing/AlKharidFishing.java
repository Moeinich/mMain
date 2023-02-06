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

public class AlKharidFishing extends Task {
    @Override
    public boolean activate() {
        return  Skills.realLevel(Constants.SKILLS_FISHING) <= 19;
    }
    @Override
    public void execute() {
        if (!SkillData.AlKharidFishingSpot1.contains(Players.local()) || !SkillData.AlKharidFishingSpot2.contains(Players.local())) {
            Movement.builder(SkillData.movementFishing()).setRunMin(45).setRunMax(75).move();
        }

        mMain.State = "Do fishing";
        if (SkillData.AlKharidFishingSpot1.contains(Players.local()) || SkillData.AlKharidFishingSpot2.contains(Players.local())) {
            Npc AlKharidFishingSpot = Npcs.stream().name("Fishing spot").nearest().first();

            if (AlKharidFishingSpot.inViewport() && Players.local().animation() == -1) {
                AlKharidFishingSpot.interact("Small Net", "Fishing Spot");
                Condition.wait(() -> Npcs.stream().at(AlKharidFishingSpot.tile()).isEmpty(), 150, 50);
            }
        }
    }
}