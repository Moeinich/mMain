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

public class treeTeak extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 42;
    }

    @Override
    public void execute() {
        if (!SkillData.teakLocation.equals(Players.local().tile())) {
            mMain.State = "Go to Teak trees";
            Movement.builder(SkillData.movementWoodcutting()).setRunMin(45).setRunMax(75).move();
        }
        if (SkillData.teakLocation.contains(Players.local()) && Players.local().animation() == -1) {
            GameObject treeTeak = Objects.stream().within(6).id(SkillData.teakTreeID).nearest().first();
            mMain.State = "Cutting Willows";
            treeTeak.interact("Chop down", "Teak");
            Condition.wait(() -> Objects.stream().at(treeTeak.tile()).id(SkillData.willowTreeID).isEmpty(), 500, 50);
        }
    }
}
