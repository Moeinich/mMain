package Fletching;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class GoFletchingArea extends Task {
    @Override
    public boolean activate() {
        return !SkillData.fletchingArea.contains(Players.local().tile());
    }

    @Override
    public void execute() {
        if (!Players.local().tile().equals(SkillData.movementFletching())) {
            mMain.State = "Walking to Fletching spot";
            Movement.builder(SkillData.movementFletching()).setRunMin(45).setRunMax(75).move();
        }
    }
}
