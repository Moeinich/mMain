package fishing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Camera;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import helpers.PlayerHelper;
import helpers.Task;
import script.mMain;

public class AlKharidFishing extends Task {
    @Override
    public boolean activate() {
        return  Skills.realLevel(Constants.SKILLS_FISHING) <= 19 && Inventory.stream().name("Small fishing net").isNotEmpty();
    }
    @Override
    public boolean execute() {
        if (!PlayerHelper.withinArea(FishingData.AlKharidFishingSpot1) && !PlayerHelper.withinArea(FishingData.AlKharidFishingSpot2)) {
            mMain.state = "Walk to fishing area";
            PlayerHelper.walkToTile(FishingData.movementFishing());
        }

        if (PlayerHelper.withinArea(FishingData.AlKharidFishingSpot1) || PlayerHelper.withinArea(FishingData.AlKharidFishingSpot2)) {
            mMain.state = "Do fishing";
            Npc AlKharidFishingSpot = PlayerHelper.nearestNpc("Fishing spot");
            if (AlKharidFishingSpot.inViewport() && Players.local().animation() == -1) {
                Camera.turnTo(AlKharidFishingSpot);
                if (AlKharidFishingSpot.interact("Small Net", "Fishing Spot")) {
                    Condition.wait(() -> Players.local().animation() == -1, 2500, 50);
                }
            }
        }
        return false;
    }
}