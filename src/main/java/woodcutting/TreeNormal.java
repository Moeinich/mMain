package woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Chat;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import helpers.PlayerHelper;
import helpers.extentions.Task;
import script.mMain;

public class TreeNormal extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 14;
    }
    @Override
    public boolean execute() {
        if (!PlayerHelper.withinArea(WoodcuttingData.normalTreeLocation)) {
            mMain.state = "Go to trees";
            PlayerHelper.walkToTile(WoodcuttingData.movementWoodcutting());
        }
        //cut normal logs
        if (PlayerHelper.withinArea(WoodcuttingData.normalTreeLocation) && Players.local().animation() == -1) {
            GameObject treeNormal = PlayerHelper.nearestGameObject(WoodcuttingData.normalTreeLocation,"Tree");
            mMain.state = "Cutting Trees..";
            if (treeNormal.interact("Chop down", "Tree")) {
                System.out.println("Clicked tree");
                Condition.wait(() -> Objects.stream().at(treeNormal.tile()).id(WoodcuttingData.normalTreeID).isEmpty() || Chat.canContinue(), 500, 50);
                System.out.println("Tree gone, leveled up or inventory full");
            }
        }
        return false;
    }
}
