package MeleeCombat;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Combat;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Varpbits;
import org.powbot.api.rt4.Widget;
import org.powbot.api.rt4.Widgets;
import org.powbot.api.rt4.World;

import java.util.concurrent.ThreadLocalRandom;

import Helpers.Task;
import Helpers.PlayerHelper;
import Helpers.SkillData;
import script.mMain;

public class Crabs extends Task {
    int autoRetaliateWidget = 201;
    int autoRetaliateComponent = 1;
    int autoRetaliateVarp = 172;
    long lastCombatTime = System.currentTimeMillis();
    int resetTime = Random.nextInt(4000, 10000);

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_STRENGTH) >= 30 && Skills.realLevel(Constants.SKILLS_ATTACK) >= 30 && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 30;
    }

    @Override
    public boolean execute() {
        if (!Combat.autoRetaliate()) {
            mMain.state = "Toggle auto retaliate";
            System.out.println("Toggle auto retaliate");
            Combat.autoRetaliate();
        }
        if (Players.stream().filter(player -> player.tile().equals(MeleeData.crabLocation) && !player.equals(Players.local())).isNotEmpty()) {
            mMain.state = "Worldhopping";
            Movement.moveTo(MeleeData.crabWorldhop);
            Condition.sleep(Random.nextInt(10000, 12000));
            int[] p2p = SkillData.p2p;
            int randomWorld = p2p[Random.nextInt(0, p2p.length - 1)];
            World world = new World(randomWorld, randomWorld, 1, World.Type.MEMBERS, World.Server.RUNE_SCAPE, World.Specialty.NONE);
            world.hop();
        } else if (PlayerHelper.atTile(MeleeData.crabLocation)) {
            if (Npcs.stream().interactingWithMe().isNotEmpty()) {
                lastCombatTime = System.currentTimeMillis();
            } else if (System.currentTimeMillis() - lastCombatTime > resetTime) {
                mMain.state = "Reset";
                System.out.println("Resetting crabs");
                resetCrabs();
                resetTime = Random.nextInt(4000, 10000);
                System.out.println("resetTime has been randomized to: " + resetTime + "ms");
            } else {
                Npc crab = PlayerHelper.nearestCombatNpc(MeleeData.crabArea, "Sand crab");
                if (crab.inViewport()) {
                    mMain.state = "Do an attack";
                    if (crab.interact("Attack")) {
                        mMain.state = "Waiting for idle";
                        System.out.println("Attacked crab");
                        Condition.wait( () -> crab.healthPercent() == 0, 500, 50);
                    }
                }
            }
        } else {
            mMain.state = "Walk to crabs";
            System.out.println("We are not at crab tile, walking to crabs!");
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
        Movement.moveTo(MeleeData.crabResetLocation);
        resetTime = ThreadLocalRandom.current().nextInt(4000, 10000);
    }
    public void walkToCrabs() {
        mMain.state = "Walk to crabs";
        int distance = (int) MeleeData.crabLocation.tile().distanceTo(Players.local());
        if (distance >= 1 && distance <= 5) {
            System.out.println("We are still not on tile, step to tile");
            Movement.step(MeleeData.crabLocation);
            Condition.wait(() -> PlayerHelper.atTile(MeleeData.crabLocation), 600, 10);
        } else {
            System.out.println("Move to crab location tile");
            Movement.moveTo(MeleeData.crabLocation);
        }
        Condition.wait(() -> PlayerHelper.atTile(MeleeData.crabLocation), 600, 10);
    }
}
