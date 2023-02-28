package woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Chat;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import helpers.PlayerHelper;
import helpers.Task;
import script.mMain;

public class TreeOak extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 15 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 30;
    }
    @Override
    public boolean execute() {
        if (!PlayerHelper.withinArea(WoodcuttingData.oakTreeLocation)) {
            mMain.state = "Go to Oak trees";
            PlayerHelper.walkToTile(WoodcuttingData.movementWoodcutting());
        }
        if (PlayerHelper.withinArea(WoodcuttingData.oakTreeLocation) && Players.local().animation() == -1) {
            GameObject treeOak = PlayerHelper.nearestGameObject(WoodcuttingData.oakTreeLocation, "Oak");
            mMain.state = "Cutting Oaks";
            if (treeOak.interact("Chop down", "Oak")) {
                System.out.println("Clicked oak");
                Condition.wait(() -> Objects.stream().at(treeOak.tile()).id(WoodcuttingData.oakTreeID).isEmpty() || Chat.canContinue() || Inventory.isFull(), 500, 50);
                System.out.println("Oak gone, leveled up or inventory full");
            }
        }
        return false;
    }
}
