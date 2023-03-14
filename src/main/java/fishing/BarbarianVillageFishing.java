package fishing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import helpers.PlayerHelper;
import helpers.extentions.Task;
import script.mMain;

public class BarbarianVillageFishing extends Task {
    @Override
    public boolean activate() {
        return  Skills.realLevel(Constants.SKILLS_FISHING) >= 20 && PlayerHelper.hasItem("Fly fishing rod") && PlayerHelper.hasItem("Feather");
    }
    @Override
    public boolean execute() {
        if (!PlayerHelper.withinArea(FishingData.BarbarianVillageFishingArea)) {
            mMain.state = "Go to fishing area";
            PlayerHelper.walkToTile(FishingData.movementFishing());
        }

        if (PlayerHelper.withinArea(FishingData.BarbarianVillageFishingArea)) {
            mMain.state = "Do fishing";
            Npc BarbarianVillageFishingSpot = PlayerHelper.nearestNpc("Rod Fishing spot");
            if (BarbarianVillageFishingSpot.inViewport() && Players.local().animation() == -1) {
                if (BarbarianVillageFishingSpot.interact("Lure", "Rod Fishing spot")) {
                    Condition.wait(() -> Npcs.stream().at(BarbarianVillageFishingSpot.tile()).isEmpty(), 500, 50);
                }
            }
        }
        return false;
    }
}
