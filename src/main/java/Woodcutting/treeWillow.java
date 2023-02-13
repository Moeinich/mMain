package Woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.PlayerHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class treeWillow extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 30 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 35;
    }

    @Override
    public boolean execute() {
        if (!PlayerHelper.WithinArea(SkillData.willowTreeLocation)) {
            mMain.State = "Go to Willow trees";
            PlayerHelper.WalkToTile(SkillData.movementWoodcutting());
        }
        if (PlayerHelper.WithinArea(SkillData.willowTreeLocation) && Players.local().animation() == -1) {
            GameObject treeWillow = Objects.stream().within(6).id(SkillData.willowTreeID).nearest().first();
            mMain.State = "Cutting Willows";
            if (treeWillow.interact("Chop down", "Willow")) {
                Condition.wait(() -> Objects.stream().at(treeWillow.tile()).id(SkillData.willowTreeID).isEmpty(), 500, 50);
            }
        }
        return false;
    }
}
