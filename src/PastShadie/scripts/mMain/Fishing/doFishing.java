package src.PastShadie.scripts.mMain.Fishing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

public class doFishing extends Task {
    @Override
    public boolean activate() {
        return  !Inventory.isFull()
                && skillData.BarbarianVillageFishingArea.contains(Players.local())
                || skillData.AlKharidFishingSpot1.contains(Players.local())
                || skillData.AlKharidFishingSpot2.contains(Players.local());
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Do fishing";
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