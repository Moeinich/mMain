package helpers.extentions;

import org.powbot.api.rt4.Chat;
import org.powbot.api.rt4.Combat;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Widgets;
import org.powbot.api.rt4.walking.model.Skill;

import java.util.concurrent.Callable;

public class Conditions {
    public static Callable<Boolean> waitUntilItemEntersInventory(String itemName, int itemCount) {
        return () -> {
            int newCount = (int) Inventory.stream().name(itemName).count();
            return newCount > itemCount;
        };
    }
    public static Callable<Boolean> waitUntilItemEntersInventory(int itemId, int itemCount) {
        return () -> {
            int newCount = (int) Inventory.stream().id(itemId).count();
            return newCount > itemCount;
        };
    }

    public static Callable<Boolean> waitUntilItemLeavesInventory(String itemName, int itemCount) {
        return () -> {
            int newCount = (int) Inventory.stream().name(itemName).count();
            return newCount < itemCount;
        };
    }

    public static Callable<Boolean> waitUntilAnyItemEntersInventory() {
        int originalCount = Inventory.emptySlotCount();
        return () -> originalCount > Inventory.emptySlotCount();
    }

    public static Callable<Boolean> waitUntilNotMoving() {
        return () -> !Players.local().inMotion();
    }

    public static Callable<Boolean> waitUntilChatting() {
        return Chat::chatting;
    }

    public static Callable<Boolean> waitUntilNotChatting() {
        return () -> !Chat.chatting();
    }

    public static Callable<Boolean> waitUntilNonIdleAnimation() {
        return () -> Players.local().animation() != -1;
    }

    public static Callable<Boolean> waitUntilIdleAnimation() {
        return () -> Players.local().animation() == -1;
    }

    public static Boolean expGained(Skill skill) {
        int startExp = Skills.experience(skill);
        return Skills.experience(skill) > startExp;
    }

    public static Callable<Boolean> waitUntilComponentAppears(int widget, int component) {
        return () -> Widgets.component(widget, component).visible();
    }

    public static Callable<Boolean> waitUntilComponentAppears(int widget, int component, int subComponent) {
        return () -> Widgets.component(widget, component, subComponent).visible();
    }

    public static Callable<Boolean> waitUntilNpcAppears(int id) {
        return () -> Npcs.stream().id(id).findFirst().orElse(null) != null;
    }

    public static Callable<Boolean> waitUntilNpcAppears(String name) {
        return () -> Npcs.stream().name(name).findFirst().orElse(null) != null;
    }

    public static Callable<Boolean> healthDecreases() {
        int currentHp = Combat.health();
        return () -> Combat.health() < currentHp;
    }
}
