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
import org.powbot.dax.api.DaxWalker;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class CopperOres extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_MINING) <= 14;
    }

    @Override
    public void execute() {
        if (!SkillData.miningCopperLocation.equals(Players.local().tile())) {
            mMain.State = "Go to copper area";
            if (SkillData.movementMining().distanceTo(Players.local()) < 3) {
                Movement.step(SkillData.movementMining());
            }
            DaxWalker.walkTo(SkillData.movementMining());
        }
        if (SkillData.miningCopperLocation.equals(Players.local().tile())) {
            if (Players.stream().within(SkillData.miningCopperArea).count() != 1) {
                int[] p2p = SkillData.p2p;
                int randomWorld = p2p[Random.nextInt(0, p2p.length - 1)];
                World world = new World(2, randomWorld, 1, World.Type.MEMBERS, World.Server.RUNE_SCAPE, World.Specialty.NONE);
                world.hop();
            }
            if (Players.local().animation() == -1 && Players.stream().within(SkillData.miningCopperArea).count() == 1) {
                mMain.State = "Mining...";
                GameObject copperOre = Objects.stream().within(1).id(11161,11360).nearest().first();
                if (copperOre.interact("Mine", "Rocks")) {
                    Condition.wait(() -> Objects.stream().at(copperOre.tile()).id(11161,11360).isEmpty(), 150, 50);
                }
            }
        }
    }
}
