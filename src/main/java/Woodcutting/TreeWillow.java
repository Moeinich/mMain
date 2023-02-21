package Woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Chat;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.PlayerHelper;
import Helpers.Task;
import script.mMain;

public class TreeWillow extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 30 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 35;
    }

    @Override
    public boolean execute() {
        if (!PlayerHelper.withinArea(WoodcuttingData.willowTreeLocation)) {
            mMain.state = "Go to Willow trees";
            PlayerHelper.walkToTile(WoodcuttingData.movementWoodcutting());
        }
        if (PlayerHelper.withinArea(WoodcuttingData.willowTreeLocation) && Players.local().animation() == -1) {
            GameObject treeWillow = PlayerHelper.nearestGameObject(WoodcuttingData.willowTreeLocation, "Willow");
            mMain.state = "Cutting Willows";
            if (treeWillow.interact("Chop down", "Willow")) {
                Condition.wait(() -> Objects.stream().at(treeWillow.tile()).id(WoodcuttingData.willowTreeID).isEmpty() || Chat.canContinue(), 500, 50);
            }
        }
        return false;
    }
}
