package Mining;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class IronOres extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_MINING) >= 15;
    }

    @Override
    public void execute() {
        if (!SkillData.miningIronLocation.equals(Players.local().tile())) {
            mMain.State = "Go to iron area";
            if (SkillData.movementMining().tile().distanceTo(Players.local()) < 3) {
                Movement.step(SkillData.movementMining());
            }

            Movement.builder(SkillData.movementMining()).setRunMin(45).setRunMax(75).move();
        }
        if (SkillData.miningIronLocation.equals(Players.local().tile())) {
            mMain.State = "Mining...";
            GameObject ironOre = Objects.stream().within(1).id(11364, 11365).nearest().first();
            if (ironOre.inViewport() && Players.local().animation() == -1) {
                ironOre.interact("Mine", "Rocks");
                Condition.wait(() -> Objects.stream().at(ironOre.tile()).id(11364, 11365).isEmpty(), 150, 50);
            }
        }
    }
}