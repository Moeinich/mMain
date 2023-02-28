package woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Chat;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.World;

import helpers.PlayerHelper;
import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

public class TreeTeak extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 35;
    }

    @Override
    public boolean execute() {
        if (!PlayerHelper.withinArea(WoodcuttingData.teakArea)) {
            mMain.state = "Go to Teak trees";
            if (WoodcuttingData.teakLocation.distanceTo(Players.local()) < 10) {
                Movement.step(WoodcuttingData.teakLocation);
            } else PlayerHelper.walkToTile(WoodcuttingData.movementWoodcutting());
        }

        if (PlayerHelper.withinArea(WoodcuttingData.teakArea) && Players.local().animation() == -1) {
            if (Players.stream().within(WoodcuttingData.teakArea).count() != 1) {
                int[] p2p = SkillData.p2p;
                int randomWorld = p2p[Random.nextInt(0, p2p.length - 1)];
                World world = new World(randomWorld, randomWorld, 1, World.Type.MEMBERS, World.Server.RUNE_SCAPE, World.Specialty.NONE);
                world.hop();
            }

            GameObject treeTeak = PlayerHelper.nearestGameObject(WoodcuttingData.teakArea, "Teak");
            mMain.state = "Cutting teaks";
            if (treeTeak.interact("Chop down", "Teak")) {
                System.out.println("Clicked teak");
                Condition.wait(() -> Objects.stream().at(treeTeak.tile()).id(WoodcuttingData.teakTreeID).isEmpty() || Chat.canContinue() || Inventory.isFull(), 500, 50);
                System.out.println("Teak gone, leveled up or inventory full");
            }
        }
        return false;
    }
}
