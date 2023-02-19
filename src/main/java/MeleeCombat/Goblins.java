package MeleeCombat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import java.util.concurrent.Callable;

import Helpers.combatHelper;
import Helpers.playerHelper;
import Helpers.Task;
import script.mMain;

public class Goblins extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_STRENGTH) <= 14 || Skills.realLevel(Constants.SKILLS_ATTACK) <= 14 || Skills.realLevel(Constants.SKILLS_DEFENSE) <= 14;
    }

    @Override
    public boolean execute() {
        if (!meleeData.goblinArea.contains(Players.local())) {
            mMain.state = "Go to goblins";
            System.out.println("Going to goblins");
            Movement.moveTo(meleeData.goblinArea.getRandomTile());
        }
        if (meleeData.goblinArea.contains(Players.local())) {
            System.out.println("Streaming for a goblin npc");
            Npc goblin = playerHelper.nearestCombatNpc(meleeData.goblinArea, "Goblin");
            mMain.state = "Attack";
            if (goblin.healthPercent() == 100 && goblin.inViewport() && goblin.interact("Attack")) {
                mMain.state = "Waiting for kill";
                Condition.wait(() -> goblin.healthPercent() == 0,1500,20);
            }
        }
        return false;
    }
}