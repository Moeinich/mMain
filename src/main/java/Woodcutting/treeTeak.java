package Woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Chat;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.World;

import Helpers.playerHelper;
import Helpers.skillData;
import Helpers.Task;
import script.mMain;

public class treeTeak extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 35;
    }

    @Override
    public boolean execute() {
        if (!playerHelper.withinArea(woodcuttingData.teakArea)) {
            mMain.state = "Go to Teak trees";
            if (woodcuttingData.teakLocation.distanceTo(Players.local()) < 10) {
                Movement.step(woodcuttingData.teakLocation);
            } else playerHelper.walkToTile(woodcuttingData.movementWoodcutting());
        }

        if (playerHelper.withinArea(woodcuttingData.teakArea) && Players.local().animation() == -1) {
            if (Players.stream().within(woodcuttingData.teakArea).count() != 1) {
                int[] p2p = skillData.p2p;
                int randomWorld = p2p[Random.nextInt(0, p2p.length - 1)];
                World world = new World(randomWorld, randomWorld, 1, World.Type.MEMBERS, World.Server.RUNE_SCAPE, World.Specialty.NONE);
                world.hop();
            }

            GameObject treeTeak = playerHelper.nearestGameObject(woodcuttingData.teakArea, "Teak");
            mMain.state = "Cutting teaks";
            if (treeTeak.interact("Chop down", "Teak")) {
                Condition.wait(() -> Objects.stream().at(treeTeak.tile()).id(woodcuttingData.teakTreeID).isEmpty() || Chat.canContinue(), 500, 50);
            }
        }
        return false;
    }
}
