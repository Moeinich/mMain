package Mining;

import org.powbot.api.Condition;
import org.powbot.api.Random;
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

public class IronOres extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_MINING) >= 15;
    }

    @Override
    public boolean execute() {
        if (!miningData.miningIronLocation.equals(Players.local().tile())) {
            mMain.state = "Go to copper area";
            if (miningData.movementMining().distanceTo(Players.local()) < 3) {
                Movement.step(miningData.miningIronLocation);
            } else if (miningData.movementMining().distanceTo(Players.local()) >= 4) {
                playerHelper.walkToTile(miningData.movementMining());
            }
        }
        if (miningData.miningIronLocation.equals(Players.local().tile())) {
            if (Players.stream().within(miningData.miningIronArea).count() != 1) {
                int[] p2p = skillData.p2p;
                int randomWorld = p2p[Random.nextInt(0, p2p.length - 1)];
                World world = new World(randomWorld, randomWorld, 1, World.Type.MEMBERS, World.Server.RUNE_SCAPE, World.Specialty.NONE);
                world.hop();
            }
            if (Players.local().animation() == -1 && Players.stream().within(miningData.miningIronArea).count() == 1) {
                mMain.state = "Mining...";
                GameObject ironOre = playerHelper.nearestGameObject(1,11364,11365);
                if (ironOre.interact("Mine", "Rocks")) {
                    Condition.wait(() -> Objects.stream().at(ironOre.tile()).id(11364, 11365).isEmpty(), 150, 50);
                }
            }
        }
        return false;
    }
}
