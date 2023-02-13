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

public class treeOak extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 15 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 30;
    }
    @Override
    public void execute() {
        if (!PlayerHelper.WithinArea(SkillData.oakTreeLocation)) {
            mMain.State = "Go to Oak trees";
            PlayerHelper.WalkToTile(SkillData.movementWoodcutting());
        }
        if (PlayerHelper.WithinArea(SkillData.oakTreeLocation) && Players.local().animation() == -1) {
            GameObject treeOak = Objects.stream().within(6).id(SkillData.oakTreeID).nearest().first();
            mMain.State = "Cutting Oaks";
            if (treeOak.interact("Chop down", "Oak")) {
                Condition.wait(() -> Objects.stream().at(treeOak.tile()).id(SkillData.oakTreeID).isEmpty(), 500, 50);
            }
        }
    }
}
