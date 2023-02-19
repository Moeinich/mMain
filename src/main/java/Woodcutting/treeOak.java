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

public class treeOak extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 15 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 30;
    }
    @Override
    public boolean execute() {
        if (!playerHelper.withinArea(woodcuttingData.oakTreeLocation)) {
            mMain.state = "Go to Oak trees";
            playerHelper.walkToTile(woodcuttingData.movementWoodcutting());
        }
        if (playerHelper.withinArea(woodcuttingData.oakTreeLocation) && Players.local().animation() == -1) {
            GameObject treeOak = playerHelper.nearestGameObject(woodcuttingData.oakTreeLocation, "Oak");
            mMain.state = "Cutting Oaks";
            if (treeOak.interact("Chop down", "Oak")) {
                Condition.wait(() -> Objects.stream().at(treeOak.tile()).id(woodcuttingData.oakTreeID).isEmpty() || Chat.canContinue(), 500, 50);
            }
        }
        return false;
    }
}
