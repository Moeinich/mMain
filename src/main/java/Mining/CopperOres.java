package Mining;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

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
            Movement.builder(SkillData.movementMining()).setRunMin(45).setRunMax(75).move();
        }
        if (SkillData.miningCopperLocation.equals(Players.local().tile())) {
            mMain.State = "Mining...";
            GameObject copperOre = Objects.stream().within(2).id(11161,11360).nearest().first();
            if (copperOre.inViewport() && Players.local().animation() == -1) {
                copperOre.interact("Mine", "Rocks");
                Condition.wait(() -> Objects.stream().at(copperOre.tile()).id(11161,11360).isEmpty(), 150, 50);
            }
        }
    }
}
