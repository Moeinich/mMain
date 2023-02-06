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

public class treeOak extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 15 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 30;
    }
    @Override
    public void execute() {
        if (!SkillData.oakTreeLocation.equals(Players.local().tile())) {
            mMain.State = "Go to Oak trees";
            Movement.builder(SkillData.movementWoodcutting()).setRunMin(45).setRunMax(75).move();
        }
        if (SkillData.oakTreeLocation.contains(Players.local()) && Players.local().animation() == -1) {
            GameObject treeOak = Objects.stream().within(6).id(SkillData.oakTreeID).nearest().first();
            mMain.State = "Cutting Oaks";
            treeOak.interact("Chop down", "Tree");
            Condition.wait(() -> Objects.stream().at(treeOak.tile()).id(SkillData.oakTreeID).isEmpty(), 500, 50);
        }
    }
}