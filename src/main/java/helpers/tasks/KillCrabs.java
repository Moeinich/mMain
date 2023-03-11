package helpers.tasks;

import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.World;

import helpers.PlayerHelper;
import helpers.SkillData;
import script.mMain;

public class KillCrabs {
    public static final Tile crabLocation = new Tile(1773,3461,0);
    public static final Area crabResetArea = new Area(new Tile(1759, 3504, 0), new Tile(1768, 3498, 0));
    public static final Tile crabWorldhop = new Tile(1775, 3475, 0);
    public static final Area crabArea = new Area(new Tile(1772, 3460, 0), new Tile(1774, 3462, 0));
    public static long lastInteractionTime;
    public static long interactionTimeRandomizer;
    static boolean shouldReset = false;

    public static void doCrabs() {
        if (Players.stream()
                .filter(player -> player.tile().equals(crabLocation) && !player.equals(Players.local()))
                .isNotEmpty()) {
            hopWorld();
        } else if (!shouldReset) {
            if (!PlayerHelper.atTile(crabLocation)) {
                if (PlayerHelper.withinArea(crabArea)) {
                    Movement.step(crabLocation);
                } else {
                    walkToCrabs();
                }
            } else if (lastInteractionTime < interactionTimeRandomizer) {
                sleep();
            } else {
                attack();
            }
        } else {
            resetCrabs();
        }
    }

    private static void hopWorld() {
        mMain.state = "Worldhopping";
        if (!PlayerHelper.atTile(crabWorldhop)) {
            System.out.println("Move to world hop area");
            Movement.moveTo(crabWorldhop);
            Condition.sleep(Random.nextInt(10000, 12000));
        } else {
            System.out.println("World hop");
            int randomWorld = SkillData.p2p[Random.nextInt(0, SkillData.p2p.length - 1)];
            System.out.println(randomWorld);
            World world = new World(randomWorld, randomWorld, 1, World.Type.MEMBERS, World.Server.RUNE_SCAPE, World.Specialty.NONE);
            world.hop();
        }
    }

    private static void attack() {
        Npc crab = PlayerHelper.nearestCombatNpc(crabArea, "Sand crab");
        if (crab.inViewport() && crab.interact("Attack")) {
            System.out.println("Attacked crab");
            lastInteractionTime = System.currentTimeMillis();
            interactionTimeRandomizer = lastInteractionTime + Random.nextInt(90000, 180000);
            Condition.sleep(Random.nextInt(500, 1000));
        }
        Npc sandyRocks = PlayerHelper.nearestCombatNpc(crabArea, "Sandy rocks");
        if (sandyRocks.inViewport() && Npcs.stream().interactingWithMe().isEmpty()) {
            shouldReset = true;
            System.out.println("shouldReset set to true!");
        }
    }

    private static void sleep() {
        mMain.state = "Sleeping..";
        lastInteractionTime = System.currentTimeMillis();
        System.out.println("We are still interacting.. sleeping...");
        System.out.println("next interaction time: " + (interactionTimeRandomizer - lastInteractionTime) + "ms");
        Condition.sleep(Random.nextInt(5000, 15000));
    }

    private static void resetCrabs() {
        mMain.state = "Reset";
        if (!crabResetArea.contains(Players.local())) {
            System.out.println("Moving to crabResetArea");
            Movement.moveTo(crabResetArea.getRandomTile());
        }
        if (crabResetArea.contains(Players.local())) {
            lastInteractionTime = System.currentTimeMillis();
            interactionTimeRandomizer = lastInteractionTime + Random.nextInt(90000, 180000);
            System.out.println("Reset crab successful");
            shouldReset = false;
            System.out.println("shouldReset set to false");
        }
    }

    public static void walkToCrabs() {
        int distance = (int) crabLocation.tile().distanceTo(Players.local());
        if (distance >= 1 && distance <= 5) {
            System.out.println("We are still not on tile, step to tile");
            Movement.step(crabLocation);
            Condition.wait(() -> PlayerHelper.atTile(crabLocation), 150, 10);
        } else {
            mMain.state = "Move to crab location";
            System.out.println("Move to crab location tile");
            Movement.moveTo(crabLocation);
        }
        Condition.wait(() -> PlayerHelper.atTile(crabLocation), 150, 10);
    }
}
