package src.PastShadie.scripts.mMain.Fishing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class doFishing extends Task {
    @Override
    public boolean activate() {
        return !Inventory.isFull() && (skillData.AlKharidFishingSpot.inViewport() || skillData.BarbarianVillageFishingSpot.inViewport());
    }
    @Override
    public void execute() {
        if (Skills.realLevel(Constants.SKILLS_FISHING) <= 19) {
            if (skillData.AlKharidFishingSpot.inViewport() && Players.local().animation() == -1) {
                skillData.AlKharidFishingSpot.interact("Small Net", "Fishing Spot");
                Condition.wait(() -> Npcs.stream().at(skillData.AlKharidFishingSpot.tile()).isEmpty(), 150, 50);
            }
        }
        if (Skills.realLevel(Constants.SKILLS_FISHING) >= 20) {
            if (skillData.BarbarianVillageFishingSpot.inViewport() && Players.local().animation() == -1) {
                skillData.BarbarianVillageFishingSpot.interact("Lure", "Rod Fishing Spot");
                Condition.wait(() -> Npcs.stream().at(skillData.BarbarianVillageFishingSpot.tile()).isEmpty(), 150, 50);
            }
        }
    }
}