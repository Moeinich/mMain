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

public class treeWillow extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 30 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 35;
    }

    @Override
    public boolean execute() {
        if (!playerHelper.withinArea(woodcuttingData.willowTreeLocation)) {
            mMain.state = "Go to Willow trees";
            playerHelper.walkToTile(woodcuttingData.movementWoodcutting());
        }
        if (playerHelper.withinArea(woodcuttingData.willowTreeLocation) && Players.local().animation() == -1) {
            GameObject treeWillow = playerHelper.nearestGameObject(woodcuttingData.willowTreeLocation, "Willow");
            mMain.state = "Cutting Willows";
            if (treeWillow.interact("Chop down", "Willow")) {
                Condition.wait(() -> Objects.stream().at(treeWillow.tile()).id(woodcuttingData.willowTreeID).isEmpty() || Chat.canContinue(), 500, 50);
            }
        }
        return false;
    }
}
