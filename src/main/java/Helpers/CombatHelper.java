package Helpers;

import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Inventory;

import java.util.Arrays;

public class CombatHelper {
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
}
