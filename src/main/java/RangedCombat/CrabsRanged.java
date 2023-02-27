package RangedCombat;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Combat;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.World;

import Helpers.CombatHelper;
import Helpers.PlayerHelper;
import Helpers.SkillData;
import Helpers.Task;
import MeleeCombat.MeleeData;
import script.mMain;

public class CrabsRanged extends Task {
    boolean shouldReset = false;

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_RANGE) >= 30
                && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 30;
    }

    @Override
    public boolean execute() {
        if (!Combat.style(Combat.Style.AGGRESSIVE)) {
            mMain.state = "Setting cb mode";
            PlayerHelper.setAttackMode(Combat.Style.AGGRESSIVE);
        }

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
            if (RangeData.lastInteractionTime < RangeData.interactionTimeRandomizer) {
                System.out.println("Sleeping...");
                System.out.println("next interaction time: " + (RangeData.interactionTimeRandomizer - RangeData.lastInteractionTime) + "ms");
                mMain.state = "Sleeping..";
                RangeData.lastInteractionTime = System.currentTimeMillis();
                Condition.sleep(Random.nextInt(5000, 15000));
            } else {
                Npc crab = PlayerHelper.nearestCombatNpc(MeleeData.crabArea, "Sand crab");
                Npc sandyRocks = PlayerHelper.nearestCombatNpc(MeleeData.crabArea, "Sandy rocks");
                if (crab.inViewport()) {
                    mMain.state = "Do an attack";
                    if (crab.interact("Attack")) {
                        System.out.println("Attacked crab");
                        RangeData.lastInteractionTime = System.currentTimeMillis();
                        RangeData.interactionTimeRandomizer = RangeData.lastInteractionTime + Random.nextInt(90000, 180000);
                        Condition.sleep(Random.nextInt(500, 1000));
                    }
                }
                if (sandyRocks.inViewport() && Npcs.stream().interactingWithMe().isEmpty()) {
                    mMain.state = "Reset";
                    System.out.println("Resetting crabs");
                    shouldReset = true;
                    if (resetCrabs()) {
                        Condition.wait(() -> PlayerHelper.atTile(MeleeData.crabResetArea.getRandomTile()), 500, 30);
                        System.out.println("Reset crab successful");
                        RangeData.lastInteractionTime = System.currentTimeMillis();
                        RangeData.interactionTimeRandomizer = RangeData.lastInteractionTime + Random.nextInt(90000, 180000);
                        shouldReset = false;
                    }
                }
            }
        } else if (!shouldReset) {
            if (PlayerHelper.withinArea(MeleeData.crabArea)) {
                Movement.step(MeleeData.crabLocation);
            } else {
                mMain.state = "Walk to crabs";
                System.out.println("We are not at crab tile, walking to crabs!");
                walkToCrabs();
            }
        }
        return false;
    }
    public boolean resetCrabs() {
        Movement.moveTo(MeleeData.crabResetArea.getRandomTile());
        return PlayerHelper.withinArea(MeleeData.crabResetArea);
    }
    public void walkToCrabs() {
        int distance = (int) MeleeData.crabLocation.tile().distanceTo(Players.local());
        if (distance >= 1 && distance <= 5) {
            System.out.println("We are still not on tile, step to tile");
            Movement.step(MeleeData.crabLocation);
            Condition.wait(() -> PlayerHelper.atTile(MeleeData.crabLocation), 150, 10);
        } else {
            System.out.println("Move to crab location tile");
            Movement.moveTo(MeleeData.crabLocation);
        }
        Condition.wait(() -> PlayerHelper.atTile(MeleeData.crabLocation), 150, 10);
    }
}
