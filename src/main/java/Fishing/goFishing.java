package Fishing;

import static Helpers.skillData.AlKharidFishingSpot1;
import static Helpers.skillData.AlKharidFishingSpot2;
import static Helpers.skillData.BarbarianVillageFishingArea;

import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

import Helpers.Task;
import Helpers.skillData;
import script.mMain;

public class goFishing extends Task {
    @Override
    public boolean activate() {
        return ( (Skill.Fishing.realLevel() <= 19
                && (!AlKharidFishingSpot1.contains(Players.local()) && !AlKharidFishingSpot2.contains(Players.local())) )
                || (Skill.Fishing.realLevel() >= 20
                && !BarbarianVillageFishingArea.contains(Players.local())) );
    }
    @Override
    public void execute() {
        mMain.State = "Go to fishing area";
        Movement.builder(skillData.movementFishing()).setRunMin(45).setRunMax(75).move();
    }
}