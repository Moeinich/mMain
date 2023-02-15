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

public class treeNormal extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 14;
    }
    @Override
    public boolean execute() {
        if (!PlayerHelper.withinArea(SkillData.normalTreeLocation)) {
            mMain.state = "Go to noob trees";
            PlayerHelper.walkToTile(SkillData.movementWoodcutting());
        }
        //cut normal logs
        if (PlayerHelper.withinArea(SkillData.normalTreeLocation) && Players.local().animation() == -1) {
            GameObject treeNormal = Objects.stream().within(6).id(SkillData.normalTreeID).nearest().first();
            mMain.state = "Cutting Trees..";
            if (treeNormal.interact("Chop down", "Tree")) {
                Condition.wait(() -> Objects.stream().at(treeNormal.tile()).id(SkillData.normalTreeID).isEmpty(), 500, 50);
            }
        }
        return false;
    }
}
