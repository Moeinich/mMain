package MeleeCombat;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.World;
import org.powbot.api.rt4.Worlds;

import Helpers.PlayerHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class Crabs extends Task {
    long lastCombatTime = System.currentTimeMillis();
    int resetTime = Random.nextInt(4000, 10000);
    boolean shouldHopWorld = false;

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_STRENGTH) >= 30 && Skills.realLevel(Constants.SKILLS_ATTACK) >= 30 && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 30;
    }

    @Override
    public boolean execute() {


        if (Players.stream().filter(player -> player.tile().equals(MeleeData.crabLocation) && !player.equals(Players.local()))
                .isNotEmpty()) {
            mMain.state = "Worldhopping";
            if (!PlayerHelper.atTile(MeleeData.crabWorldhop)) {
                System.out.println("Move to world hop area");
                Movement.moveTo(MeleeData.crabWorldhop);
                Condition.sleep(Random.nextInt(10000, 12000));
            } else {
                System.out.println("World hop");
                int randomWorld = SkillData.p2p[Random.nextInt(0, SkillData.p2p.length - 1)];
                System.out.println(randomWorld);
                World world = new World(randomWorld, randomWorld, 1, World.Type.MEMBERS, World.Server.RUNE_SCAPE, World.Specialty.NONE);
                world.hop();
            }
        } else if (PlayerHelper.atTile(MeleeData.crabLocation)) {
            if (Npcs.stream().interactingWithMe().isNotEmpty()) {
                lastCombatTime = System.currentTimeMillis();
                mMain.state = "Sleeping..";
                Condition.sleep(Random.nextInt(1000, 3500));
            } else if (System.currentTimeMillis() - lastCombatTime > resetTime) {
                Npc crab = PlayerHelper.nearestCombatNpc(MeleeData.crabArea, "Sand crab");
                if (crab.inViewport()) {
                    mMain.state = "Do an attack";
                    if (crab.interact("Attack")) {
                        mMain.state = "Waiting for idle";
                        System.out.println("Attacked crab");
                        lastCombatTime = System.currentTimeMillis();
                    }
                } else {
                    mMain.state = "Reset";
                    System.out.println("Resetting crabs");
                    if(resetCrabs()) {
                        resetTime = Random.nextInt(4000, 10000);
                        System.out.println("resetTime has been randomized to: " + resetTime + "ms");
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
    public boolean resetCrabs() {
        mMain.state = "Walk to reset spot";
        System.out.println("Resetting crabs!");
        Movement.moveTo(MeleeData.crabResetArea.getRandomTile());
        return PlayerHelper.withinArea(MeleeData.crabResetArea);
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
