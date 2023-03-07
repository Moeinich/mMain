package helpers;

import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.World;
import org.powbot.dax.api.DaxWalker;

import java.util.Arrays;

import helpers.extentions.ItemList;
import script.mMain;

public class CombatHelper {
    public static boolean hasEquipped(int id) {
        return Equipment.stream().id(id).isNotEmpty();
    }
    public static boolean needEquipment(int[] items) {
        return missingEquipment(items).length != 0;
    }
    private static final int DISTANCE_TO_BANK_THRESHOLD = 5;
    private static final int WAIT_TIMEOUT = 250;
    private static final int WAIT_RETRIES = 10;
    public static long lastInteractionTime;
    public static long interactionTimeRandomizer;
    public static final Tile crabLocation = new Tile(1773,3461,0);
    public static final Area crabResetArea = new Area(new Tile(1759, 3504, 0), new Tile(1768, 3498, 0));
    public static final Tile crabWorldhop = new Tile(1775, 3475, 0);
    public static final Area crabArea = new Area(new Tile(1772, 3460, 0), new Tile(1774, 3462, 0));
    static boolean shouldReset = false;

    public static int[] missingEquipment(int[] items) {
        return Arrays.stream(items).filter(x -> !hasEquipped(x)).toArray();
    }
    public static boolean gotItems(int... id) {
        return Inventory.stream().id(id).isNotEmpty();
    }

    public static void gearUp(int[] equipment) {
        if (Bank.nearest().tile().distanceTo(Players.local()) > DISTANCE_TO_BANK_THRESHOLD) {
            System.out.println("We are missing gear, walking to bank.");
            DaxWalker.walkToBank();
        }

        if (!CombatHelper.gotItems(missingEquipment(equipment))) {
            System.out.println("Start withdrawing equipment");
            openBankAndWithdrawItems(equipment);
        }

        if (CombatHelper.gotItems(missingEquipment(equipment))) {
            System.out.println("Start equipping equipment");
            equipMissingItems(equipment);
        }
    }

    private static void openBankAndWithdrawItems(int[] equipment) {
        if (Bank.nearest().tile().distanceTo(Players.local()) <= DISTANCE_TO_BANK_THRESHOLD && Bank.inViewport()) {
            mMain.state = "Open bank";
            if (!Bank.opened()) {
                System.out.println("Open bank to get equipment out");
                Bank.open();
                Condition.wait(Bank::opened, WAIT_TIMEOUT, WAIT_RETRIES);
            }
            if (Bank.open()) {
                mMain.state = "Withdraw equipment";
                Bank.depositEquipment();
                Bank.depositInventory();
                for (var itemId : missingEquipment(equipment)) {
                    if (itemId == ItemList.IRON_ARROW_884 || itemId == ItemList.MITHRIL_DART_809) {
                        System.out.println("Withdraw ammo");
                        InteractionsHelper.withdrawItem(itemId, -1);
                        Condition.wait(() -> Inventory.stream().id(itemId).isNotEmpty(), WAIT_TIMEOUT, WAIT_RETRIES);
                    } else {
                        System.out.println("Withdrawing item " + itemId);
                        InteractionsHelper.withdrawItem(itemId, 1);
                        Condition.wait(() -> Inventory.stream().id(itemId).isNotEmpty(), WAIT_TIMEOUT, WAIT_RETRIES);
                    }
                }
            }
        }
    }
    private static void equipMissingItems(int[] equipment) {
        if (Bank.opened()) {
            Bank.close();
            Condition.wait( () -> !Bank.opened(), WAIT_TIMEOUT, WAIT_RETRIES);
        }
        Item[] missingItems = Inventory.stream()
                .id(equipment)
                .filter(item -> !CombatHelper.hasEquipped(item.id()))
                .list()
                .toArray(new Item[0]);
        if (missingItems.length == 0) {
            System.out.println("We have all items equipped");
            return;
        }
        mMain.state = "Equip items";
        for (Item item : missingItems) {
            var itemToEquip = Inventory.stream().id(item.getId()).findFirst().orElse(null);
            if (itemToEquip == null) {
                System.out.println("Item already equipped");
                continue;
            }
            String interactionType = null;
            if (itemToEquip.actions().contains("Wear")) {
                interactionType = "Wear";
            } else if (itemToEquip.actions().contains("Wield")) {
                interactionType = "Wield";
            }
            if (interactionType != null && itemToEquip.interact(interactionType, itemToEquip.name())) {
                System.out.println("Equipped missing item");
                Condition.wait(() -> CombatHelper.hasEquipped(item.getId()), 250, 10);
            }
        }
    }
    public static boolean resetCrabs() {
        System.out.println("Walking to reset spot!");
        Movement.moveTo(CombatHelper.crabResetArea.getRandomTile());
        return PlayerHelper.withinArea(CombatHelper.crabResetArea);
    }
    public static void walkToCrabs() {
        int distance = (int) CombatHelper.crabLocation.tile().distanceTo(Players.local());
        if (distance >= 1 && distance <= 5) {
            System.out.println("We are still not on tile, step to tile");
            Movement.step(CombatHelper.crabLocation);
            Condition.wait(() -> PlayerHelper.atTile(CombatHelper.crabLocation), 150, 10);
        } else {
            System.out.println("Move to crab location tile");
            Movement.moveTo(CombatHelper.crabLocation);
        }
        Condition.wait(() -> PlayerHelper.atTile(CombatHelper.crabLocation), 150, 10);
    }

    public static void doCrabs() {
        if (Players.stream().filter(player -> player.tile().equals(CombatHelper.crabLocation) && !player.equals(Players.local()))
                .isNotEmpty()) {
            mMain.state = "Worldhopping";
            if (!PlayerHelper.atTile(CombatHelper.crabWorldhop)) {
                System.out.println("Move to world hop area");
                Movement.moveTo(CombatHelper.crabWorldhop);
                Condition.sleep(Random.nextInt(10000, 12000));
            } else {
                System.out.println("World hop");
                int randomWorld = SkillData.p2p[Random.nextInt(0, SkillData.p2p.length - 1)];
                System.out.println(randomWorld);
                World world = new World(randomWorld, randomWorld, 1, World.Type.MEMBERS, World.Server.RUNE_SCAPE, World.Specialty.NONE);
                world.hop();
            }
        } else if (PlayerHelper.atTile(CombatHelper.crabLocation)) {
            if (CombatHelper.lastInteractionTime < CombatHelper.interactionTimeRandomizer) {
                System.out.println("Sleeping...");
                System.out.println("next interaction time: " + (CombatHelper.interactionTimeRandomizer - CombatHelper.lastInteractionTime) + "ms");
                mMain.state = "Sleeping..";
                CombatHelper.lastInteractionTime = System.currentTimeMillis();
                Condition.sleep(Random.nextInt(5000, 15000));
            } else {
                Npc crab = PlayerHelper.nearestCombatNpc(CombatHelper.crabArea, "Sand crab");
                Npc sandyRocks = PlayerHelper.nearestCombatNpc(CombatHelper.crabArea, "Sandy rocks");
                if (crab.inViewport()) {
                    mMain.state = "Do an attack";
                    if (crab.interact("Attack")) {
                        System.out.println("Attacked crab");
                        CombatHelper.lastInteractionTime = System.currentTimeMillis();
                        CombatHelper.interactionTimeRandomizer = CombatHelper.lastInteractionTime + Random.nextInt(90000, 180000);
                        Condition.sleep(Random.nextInt(500, 1000));
                    }
                }
                if (sandyRocks.inViewport() && Npcs.stream().interactingWithMe().isEmpty()) {
                    mMain.state = "Reset";
                    System.out.println("Resetting crabs");
                    shouldReset = true;
                    if (CombatHelper.resetCrabs()) {
                        Condition.wait(() -> PlayerHelper.atTile(CombatHelper.crabResetArea.getRandomTile()), 500, 30);
                        System.out.println("Reset crab successful");
                        CombatHelper.lastInteractionTime = System.currentTimeMillis();
                        CombatHelper.interactionTimeRandomizer = CombatHelper.lastInteractionTime + Random.nextInt(90000, 180000);
                        shouldReset = false;
                        System.out.println("shouldReset set to false");
                    }
                }
            }
        }  else if (!shouldReset) {
            if (PlayerHelper.withinArea(CombatHelper.crabArea)) {
                System.out.println("Step to crab location tile");
                Movement.step(CombatHelper.crabLocation);
            } else {
                mMain.state = "Walk to crabs";
                CombatHelper.walkToCrabs();
            }
        }
    }
}
