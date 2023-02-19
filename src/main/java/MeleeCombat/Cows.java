package MeleeCombat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import java.util.concurrent.Callable;

import Helpers.Task;
import Helpers.playerHelper;
import script.mMain;

public class Cows extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_STRENGTH) >= 15 && Skills.realLevel(Constants.SKILLS_ATTACK) >= 15 && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 15;
    }

    @Override
    public boolean execute() {
        if (!meleeData.cowArea.contains(Players.local())) {
            mMain.state = "Go to cows";
            System.out.println("Going to cows");
            Movement.moveTo(meleeData.cowArea.getRandomTile());
        }
        if (meleeData.cowArea.contains(Players.local())) {
            System.out.println("Streaming for a cow or cow calf");
            Npc cow = playerHelper.nearestCombatNpc(meleeData.cowArea, "Cow", "Cow calf");
            mMain.state = "Attack";
            if (cow.healthPercent() == 100 && cow.inViewport() && cow.interact("Attack")) {
                mMain.state = "Waiting for kill";
                Condition.wait(() -> cow.healthPercent() == 0,1500,20);
            }
        }
        return false;
    }
}
