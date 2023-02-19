package Fishing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.playerHelper;
import Helpers.Task;
import script.mMain;

public class BarbarianVillageFishing extends Task {
    @Override
    public boolean activate() {
        return  Skills.realLevel(Constants.SKILLS_FISHING) >= 20 && Inventory.stream().name("Fly fishing rod").isNotEmpty() && Inventory.stream().name("Feather").isNotEmpty();
    }
    @Override
    public boolean execute() {
        if (!playerHelper.withinArea(fishingData.BarbarianVillageFishingArea)) {
            mMain.state = "Go to fishing area";
            playerHelper.walkToTile(fishingData.movementFishing());
        }

        if (playerHelper.withinArea(fishingData.BarbarianVillageFishingArea)) {
            mMain.state = "Do fishing";
            Npc BarbarianVillageFishingSpot = playerHelper.nearestNpc("Rod Fishing spot");
            if (BarbarianVillageFishingSpot.inViewport() && Players.local().animation() == -1) {
                BarbarianVillageFishingSpot.interact("Lure", "Rod Fishing spot");
                Condition.wait(() -> Npcs.stream().at(BarbarianVillageFishingSpot.tile()).isEmpty(), 150, 50);
            }
        }
        return false;
    }
}
