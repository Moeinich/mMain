package Woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Chat;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.playerHelper;
import Helpers.Task;
import script.mMain;

public class treeNormal extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 14;
    }
    @Override
    public boolean execute() {
        if (!playerHelper.withinArea(woodcuttingData.normalTreeLocation)) {
            mMain.state = "Go to noob trees";
            playerHelper.walkToTile(woodcuttingData.movementWoodcutting());
        }
        //cut normal logs
        if (playerHelper.withinArea(woodcuttingData.normalTreeLocation) && Players.local().animation() == -1) {
            GameObject treeNormal = playerHelper.nearestGameObject(woodcuttingData.normalTreeLocation,"Tree");
            mMain.state = "Cutting Trees..";
            if (treeNormal.interact("Chop down", "Tree")) {
                Condition.wait(() -> Objects.stream().at(treeNormal.tile()).id(woodcuttingData.normalTreeID).isEmpty() || Chat.canContinue(), 500, 50);
            }
        }
        return false;
    }
}
