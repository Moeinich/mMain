package meleeCombat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import helpers.extentions.Task;
import helpers.PlayerHelper;
import script.mMain;

public class Cows extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_STRENGTH) <= 29
                || Skills.realLevel(Constants.SKILLS_ATTACK) <= 29
                || Skills.realLevel(Constants.SKILLS_DEFENSE) <= 29;

    }

    @Override
    public boolean execute() {
        if (!Movement.running() && Movement.energyLevel() > 30) {
            PlayerHelper.enableRun();
        }

        if (!MeleeData.cowArea.contains(Players.local())) {
            mMain.state = "Go to cows";
            System.out.println("Going to cows");
            Movement.moveTo(MeleeData.cowArea.getRandomTile());
        }
        if (MeleeData.cowArea.contains(Players.local())) {
            System.out.println("Streaming for a cow or cow calf");
            Npc cow = PlayerHelper.nearestCombatNpc(MeleeData.cowArea, "Cow", "Cow calf");
            mMain.state = "Attack";
            if (cow.healthPercent() == 100 && cow.inViewport() && cow.interact("Attack")) {
                mMain.state = "Waiting for kill";
                Condition.wait(() -> cow.healthPercent() == 0 || Players.local().healthPercent() < 50,1500,20);
            }
        }
        return false;
    }
}
