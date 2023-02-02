package Fishing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class DoFishing extends Task {
    @Override
    public boolean activate() {
        return  !Inventory.isFull()
                && SkillData.BarbarianVillageFishingArea.contains(Players.local())
                || SkillData.AlKharidFishingSpot1.contains(Players.local())
                || SkillData.AlKharidFishingSpot2.contains(Players.local());
    }
    @Override
    public void execute() {
        mMain.State = "Do fishing";
        if (Skills.realLevel(Constants.SKILLS_FISHING) <= 19) {
            Npc AlKharidFishingSpot = Npcs.stream().name("Fishing spot").nearest().first();

            if (AlKharidFishingSpot.inViewport() && Players.local().animation() == -1) {
                AlKharidFishingSpot.interact("Small Net", "Fishing Spot");
                Condition.wait(() -> Npcs.stream().at(AlKharidFishingSpot.tile()).isEmpty(), 150, 50);
            }
        }
        if (Skills.realLevel(Constants.SKILLS_FISHING) >= 20) {
            Npc BarbarianVillageFishingSpot = Npcs.stream().name("Rod Fishing spot").nearest().first();

            if (BarbarianVillageFishingSpot.inViewport() && Players.local().animation() == -1) {
                BarbarianVillageFishingSpot.interact("Lure", "Rod Fishing spot");
                Condition.wait(() -> Npcs.stream().at(BarbarianVillageFishingSpot.tile()).isEmpty(), 150, 50);
            }
        }
    }
}