package Mining;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

import Assets.Task;
import Assets.skillData;
import script.mMain;


public class doMining extends Task {
    @Override
    public boolean activate() {
        return !Inventory.isFull() && (skillData.miningCopperLocation.equals(Players.local().tile()) || skillData.miningIronLocation.equals(Players.local().tile()));
    }

    @Override
    public void execute() {
        mMain.scriptStatus = "Do mining";
        //Mine copper ores till 20 mining
        if (Skills.realLevel(Constants.SKILLS_MINING) <= 25) {
            GameObject copperOre = Objects.stream().within(2).id(11161,11360).nearest().first();
            if (copperOre.inViewport() && Players.local().animation() == -1) {
                copperOre.interact("Mine", "Rocks");
                Condition.wait(() -> Objects.stream().at(copperOre.tile()).id(11161,11360).isEmpty(), 150, 50);
            }
        }

        if (Skills.realLevel(Constants.SKILLS_MINING) >= 26) {
            GameObject ironOre = Objects.stream().within(2).id(11365).nearest().first();
            if (ironOre.inViewport() && Players.local().animation() == -1) {
                ironOre.interact("Mine", "Rocks");
                Condition.wait(() -> Objects.stream().at(ironOre.tile()).id(11365).isEmpty(), 150, 50);
            }
        }
    }
}