package MeleeCombat;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Combat;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Varpbits;
import org.powbot.api.rt4.Widget;
import org.powbot.api.rt4.Widgets;

import java.util.concurrent.ThreadLocalRandom;

import Helpers.Task;
import Helpers.playerHelper;
import script.mMain;

public class Crabs extends Task {
    int autoRetaliateWidget = 201;
    int autoRetaliateComponent = 1;
    int autoRetaliateVarp = 172;
    long lastCombatTime = System.currentTimeMillis();
    int resetTime = Random.nextInt(4000, 10000);

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_STRENGTH) >= 40 && Skills.realLevel(Constants.SKILLS_ATTACK) >= 40 && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 40;
    }

    @Override
    public boolean execute() {
        if (!Combat.autoRetaliate()) {
            EnableAutoRetaliate();
            //Combat.autoRetaliate();
        }

        if (System.currentTimeMillis() - lastCombatTime > resetTime && playerHelper.atTile(meleeData.crabLocation)) {
            resetCrabs();
        }

        if (!playerHelper.atTile(meleeData.crabLocation)) {
            walkToCrabs();
        }
        return false;
    }

    public void EnableAutoRetaliate() {
        if (Game.tab(Game.Tab.ATTACK)) {
            Widget widget = Widgets.widget(autoRetaliateWidget);
            if (widget.valid()) {
                Component autoRetaliateComp = widget.component(autoRetaliateComponent);
                if (autoRetaliateComp.visible()) {
                    autoRetaliateComp.click();
                    Condition.wait(() -> Varpbits.varpbit(autoRetaliateVarp) == 1, 200, 10);
                }
            }
        }
    }
    public void resetCrabs() {
        mMain.state = "Walk to reset spot";
        System.out.println("Resetting crabs!");
        Movement.moveTo(meleeData.crabResetLocation);
        resetTime = ThreadLocalRandom.current().nextInt(4000, 10000);
    }
    public void walkToCrabs() {
        mMain.state = "Walk to crabs";
        int distance = (int) meleeData.crabLocation.tile().distanceTo(Players.local());
        if (distance >= 1 && distance <= 5) {
            System.out.println("We are still not on tile, step to tile");
            Movement.step(meleeData.crabLocation);
            Condition.wait(() -> playerHelper.atTile(meleeData.crabLocation), 400, 10);
        } else {
            System.out.println("Move to crab location tile");
            Movement.moveTo(meleeData.crabLocation);
        }
        Condition.wait(() -> playerHelper.atTile(meleeData.crabLocation), 400, 10);
    }
}
