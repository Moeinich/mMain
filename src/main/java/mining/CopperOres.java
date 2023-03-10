package mining;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.World;

import helpers.PlayerHelper;
import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;
import thieving.ThievingData;

public class CopperOres extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_MINING) <= 14;
    }

    @Override
    public boolean execute() {
        if (!MiningData.miningCopperLocation.equals(Players.local().tile())) {
            mMain.state = "Go to copper area";
            if (MiningData.movementMining().distanceTo(Players.local()) < 3) {
                Movement.step(MiningData.miningCopperLocation);
            } else if (MiningData.movementMining().distanceTo(Players.local()) >= 4) {
                PlayerHelper.walkToTile(MiningData.movementMining());
            }
        }

        if (MiningData.miningCopperLocation.equals(Players.local().tile())) {
            if (Players.stream().filter(player -> MiningData.miningCopperArea.contains(player.tile()) && !player.equals(Players.local())).isEmpty()) {
                int[] p2p = SkillData.p2p;
                int randomWorld = p2p[Random.nextInt(0, p2p.length - 1)];
                World world = new World(randomWorld, randomWorld, 1, World.Type.MEMBERS, World.Server.RUNE_SCAPE, World.Specialty.NONE);
                world.hop();
            }
            if (Players.local().animation() == -1 && Players.stream().within(MiningData.miningCopperArea).count() == 1) {
                mMain.state = "Mining...";
                GameObject copperOre = PlayerHelper.nearestGameObject(1,11161,11360);
                if (copperOre.interact("Mine", "Rocks")) {
                    Condition.wait(() -> Objects.stream().at(copperOre.tile()).id(11161,11360).isEmpty(), 150, 50);
                }
            }
        }
        return false;
    }
}
