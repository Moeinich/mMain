package src.PastShadie.scripts.mMain.Mining;

import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class doMining extends Task {
    @Override
    public boolean activate() {
        return !Inventory.stream().name("pickaxe").isEmpty() && !Inventory.isFull();
    }

    @Override
    public void execute() {
        //Mine copper ores till 20 mining
        if (Skills.realLevel(Constants.SKILLS_MINING) <= 19) {
            GameObject copperOre = Objects.stream().within(6).id(11161,11360).nearest().first();
            if (copperOre.inViewport() && Players.local().animation() == -1) {
                copperOre.interact("Mine", "Rocks");
                Condition.wait(() -> Objects.stream().at(copperOre.tile()).id(11161,11360).isEmpty(), 150, 50);
            }
        }

        if (Skills.realLevel(Constants.SKILLS_MINING) >= 20) {
            GameObject ironOre = Objects.stream().within(6).id(11365).nearest().first();
            if (ironOre.inViewport() && Players.local().animation() == -1) {
                ironOre.interact("Mine", "Rocks");
                Condition.wait(() -> Objects.stream().at(ironOre.tile()).id(11365).isEmpty(), 150, 50);
            }
        }
}
}