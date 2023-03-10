package helpers;

import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Combat;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.GroundItem;
import org.powbot.api.rt4.GroundItems;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Widgets;
import org.powbot.dax.api.DaxWalker;
import org.powbot.dax.teleports.Teleport;

import java.util.Arrays;

import script.mMain;

public class PlayerHelper {

    public static void shouldEat() {
        mMain.state = "Eating food";
        Item food = Inventory.stream().action("Eat").first();
        System.out.println("Eating food");
        food.interact("Eat");
        Condition.wait(() -> Players.local().animation() == -1, 250, 50);
    }
    public static void bankForFood(int foodName, int amount) {
        mMain.state = "Bank for food";
        if (Bank.nearest().tile().distanceTo(Players.local()) >= 6) {
            System.out.println("Moving to bank to grab food");
            Movement.moveToBank();
        }
        if (Bank.nearest().tile().distanceTo(Players.local()) <= 5) {
            System.out.println("Withdrawing food " + foodName + " amount:" + amount);
            InteractionsHelper.depositAndWithdraw(foodName, amount);
            Bank.close();
            Condition.wait( () -> !Bank.opened(), 150, 50);
        }
    }

    public static void lootItems(String Action, String ItemName) {
        GroundItem groundItem = GroundItems.stream().within(7).name(ItemName).nearest().first();
        if (groundItem.inViewport()){
            System.out.println("Looting grounditem " + ItemName);
            groundItem.interact(Action, ItemName);
            Condition.wait(() -> GroundItems.stream().id(groundItem.id()).at(groundItem.tile()).isEmpty(), 300, 50);
        }
    }
    public static void walkToTile(Tile place, Teleport... teleportBlacklist) {
        if (place.tile().distanceTo(Players.local()) <= 2) {
            System.out.println("We are still too far away from tile, stepping");
            Movement.step(place);
            Condition.wait( () -> !Players.local().inMotion(), 900, 100);
        } else if (place.tile().distanceTo(Players.local()) > 3){
            System.out.println("Walking to tile");
            DaxWalker.blacklistTeleports(teleportBlacklist);
            DaxWalker.walkTo(place);
        }
        DaxWalker.clearTeleportBlacklist();
    }

    public static boolean withinArea(Area area) {
        return area.contains(Players.local());
    }
    public static boolean atTile(Tile tile) {
        return Players.local().tile().equals(tile);
    }
    public static void enableRun() {
        mMain.state = "Enable run..";
        Widgets.widget(160).component(29).click();
        Condition.wait(Movement::running, 150, 50);
    }
    public static void setAttackMode(Combat.Style style) {
        System.out.print("Setting combat mode to " + style);
        Combat.style(style);
    }
    public static GameObject nearestGameObject(String... name) {
        return Objects.stream().name(name).nearest().first();
    }
    public static GameObject nearestGameObject(Area withinArea, String... name) {
        return Objects.stream().within(withinArea).name(name).nearest().first();
    }

    public static GameObject nearestGameObject(Area withinArea, int... ids) {
        return Objects.stream().within(withinArea).id(ids).nearest().first();
    }
    public static GameObject nearestGameObject(int radius, int... ids) {
        return Objects.stream().within(radius).id(ids).nearest().first();
    }
    public static Npc nearestNpc(String... npcName) {
        return Npcs.stream().name(npcName).nearest().first();
    }
    public static Npc nearestNpc(Area withinArea, String... npcName) {
        return Npcs.stream().within(withinArea).name(npcName).nearest().first();
    }
    public static Npc nearestCombatNpc(Area withinArea, String... npcName) {
        return Npcs.stream().within(withinArea).name(npcName).filter(n -> n.healthPercent() == 100).nearest().first();
    }
    public static boolean hasItem(int... itemID) {
        for (int id : itemID) {
            if (Inventory.stream().id(id).isEmpty()) {
                System.out.println("We dont have: " + Arrays.toString(itemID));
                return false;
            }
        }
        System.out.println("We do have: " + Arrays.toString(itemID));
        return true;
    }

    public static boolean hasItem(String... itemName) {
        for (String id : itemName) {
            if (Inventory.stream().name(id).isEmpty()) {
                System.out.println("We dont have: " + Arrays.toString(itemName));
                return false;
            }
        }
        System.out.println("We do have: " + Arrays.toString(itemName));
        return true;
    }
}
