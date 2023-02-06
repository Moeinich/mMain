package Woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class treeNormal extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 14;
    }
    @Override
    public void execute() {
        if (!SkillData.normalTreeLocation.equals(Players.local().tile())) {
            mMain.State = "Go to noob trees";
            Movement.builder(SkillData.movementWoodcutting()).setRunMin(45).setRunMax(75).move();
        }
        //cut normal logs
        if (SkillData.normalTreeLocation.contains(Players.local()) && Players.local().animation() == -1) {
            GameObject treeNormal = Objects.stream().within(6).id(SkillData.normalTreeID).nearest().first();
            mMain.State = "Cutting Trees..";
            treeNormal.interact("Chop down", "Tree");
            Condition.wait(() -> Objects.stream().at(treeNormal.tile()).id(SkillData.normalTreeID).isEmpty(), 500, 50);
        }
    }
}