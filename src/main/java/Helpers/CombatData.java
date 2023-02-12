package Helpers;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Skills;

public class CombatData {
    public static int[] LowRangedSetup = {ItemList.IRON_ARROW_884, ItemList.SHORTBOW_841, ItemList.COIF_1169};
    public static int[] MedRangedSetup = {ItemList.IRON_ARROW_884, ItemList.SHORTBOW_841, ItemList.COIF_1169};


    public static boolean hasEquipped(int id)
    {
        return Equipment.stream().id(id).count() > 0;
    }
    public int[] RangeEquipment() {
        if (Skills.realLevel(Constants.SKILLS_RANGE) >= 20) {
            return LowRangedSetup;
        }
        return new int[0];
    }

}