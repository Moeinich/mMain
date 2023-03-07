package helpers;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Players;
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
}
