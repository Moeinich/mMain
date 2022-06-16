package src.PastShadie.scripts.mMain.Woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class doWoodcutting extends Task {
    @Override
    public boolean activate() {
        return !Inventory.isFull() && (
                skillData.treeNormal.inViewport() || skillData.treeOak.inViewport() || skillData.treeWillow.inViewport()
        );
    }
    @Override
    public void execute() {
        //cut normal logs
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 14 && Players.local().animation() == -1) {
                    skillData.treeNormal.interact("Chop down", "Tree");
                    Condition.wait(() -> Objects.stream().at(skillData.treeNormal.tile()).id(skillData.normalTreeID).isEmpty(), 150, 50);
        }

        //Cut oak logs
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 14 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 30 && Players.local().animation() == -1) {
            skillData.treeOak.interact("Chop down", "Tree");
            Condition.wait(() -> Objects.stream().at(skillData.treeOak.tile()).id(skillData.oakTreeID).isEmpty(), 150, 50);
        }

        //Cut willow logs
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 30 && Players.local().animation() == -1) {
            skillData.treeWillow.interact("Chop down", "Tree");
            Condition.wait(() -> Objects.stream().at(skillData.treeWillow.tile()).id(skillData.willowTreeID).isEmpty(), 150, 50);
        }
    }
}