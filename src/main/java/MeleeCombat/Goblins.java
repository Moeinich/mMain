package MeleeCombat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.PlayerHelper;
import Helpers.Task;
import script.mMain;

public class Goblins extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_STRENGTH) <= 14 || Skills.realLevel(Constants.SKILLS_ATTACK) <= 14 || Skills.realLevel(Constants.SKILLS_DEFENSE) <= 14;
    }

    @Override
    public boolean execute() {
        if (!Movement.running() && Movement.energyLevel() > 30) {
            PlayerHelper.enableRun();
        }

        if (!MeleeData.goblinArea.contains(Players.local())) {
            mMain.state = "Go to goblins";
            System.out.println("Going to goblins");
            Movement.moveTo(MeleeData.goblinArea.getRandomTile());
        }
        if (MeleeData.goblinArea.contains(Players.local())) {
            System.out.println("Streaming for a goblin npc");
            Npc goblin = PlayerHelper.nearestCombatNpc(MeleeData.goblinArea, "Goblin");
            mMain.state = "Attack";
            if (goblin.healthPercent() == 100 && goblin.inViewport() && goblin.interact("Attack")) {
                mMain.state = "Waiting for kill";
                Condition.wait(() -> goblin.healthPercent() == 0 || Players.local().healthPercent() < 50,1500,20);
            }
        }
        return false;
    }
}