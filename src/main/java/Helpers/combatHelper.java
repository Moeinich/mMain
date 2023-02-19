package Helpers;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;
import org.powbot.dax.api.DaxWalker;

import java.util.Arrays;

import script.mMain;

public class combatHelper {
    public static boolean hasEquipped(int id)
    {
        return Equipment.stream().id(id).isNotEmpty();
    }
    public static boolean needEquipment(int[] items)
    {
        return missingEquipment(items).length != 0;
    }

    public static int[] missingEquipment(int[] items) {
        return Arrays.stream(items).filter(x -> !hasEquipped(x)).toArray();
    }
    public static boolean gotItems(int... id) {
        return Inventory.stream().id(id).count() > 0;
    }

    public static void gearUp(int[] equipment) {
        if (Bank.nearest().tile().distanceTo(Players.local()) > 5) {
            mMain.state = "Walking to bank";
            DaxWalker.walkToBank();
        }
        if (!combatHelper.gotItems(missingEquipment(equipment))) {
            if (Bank.nearest().tile().distanceTo(Players.local()) <= 5 && Bank.inViewport()) {
                mMain.state = "Open bank";
                if (!Bank.opened()) {
                    Bank.open();
                    Condition.wait(Bank::opened, 100,10);
                }
                if (Bank.open()) {
                    mMain.state = "Withdraw equipment";
                    Bank.depositEquipment();
                    Bank.depositInventory();
                    for (var itemId : missingEquipment(equipment)) {
                        System.out.println("Withdrawing item " + itemId);
                        interactionHelper.withdrawItem(itemId, 1);
                        Condition.wait(() -> Inventory.stream().id(itemId).isNotEmpty(), 250,10);
                    }
                }
            }
            if (combatHelper.gotItems(missingEquipment(equipment))) {
                Bank.close();
            }
        }

        if (combatHelper.gotItems(missingEquipment(equipment))) {
            mMain.state = "Equip items";
            for (var item : missingEquipment(equipment)) {
                var itemToEquip = Inventory.stream().id(item).first();
                if (itemToEquip != null){
                    if (itemToEquip.interact("Wield", itemToEquip.name())) {
                        Condition.wait(() -> combatHelper.hasEquipped(item), 250, 10);
                    }
                    else {
                        if (itemToEquip.interact("Wear", itemToEquip.name())) {
                            Condition.wait(() -> combatHelper.hasEquipped(item), 250, 10);
                        }
                    }
                }
            }
        }
    }
}
