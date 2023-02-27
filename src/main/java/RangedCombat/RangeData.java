package RangedCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;


import Helpers.ItemList;

public class RangeData {
    //For crabs:
    public static long lastInteractionTime;
    public static long interactionTimeRandomizer;

    //Range setups!
    public static int[] LowRangedSetup = {ItemList.IRON_ARROW_884, ItemList.SHORTBOW_841, ItemList.AMULET_OF_GLORY_1704, ItemList.COMBAT_BRACELET_11126, ItemList.RED_CAPE_1007};
    public static int[] LowPlusRangedSetup = {ItemList.MITHRIL_DART_809, ItemList.AMULET_OF_GLORY_1704, ItemList.COMBAT_BRACELET_11126, ItemList.RED_CAPE_1007};
    public static int[] MedRangedSetup = {ItemList.SNAKESKIN_BANDANA_6326,ItemList.SNAKESKIN_BODY_6322,ItemList.SNAKESKIN_SHIELD_22272,ItemList.SNAKESKIN_BOOTS_6328,ItemList.SNAKESKIN_CHAPS_6324,ItemList.MITHRIL_DART_809, ItemList.AMULET_OF_GLORY_1704, ItemList.COMBAT_BRACELET_11126, ItemList.RED_CAPE_1007};

    public static int[] rangeEquipment() {
        if (Skills.realLevel(Constants.SKILLS_RANGE) >= 30 && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 30) {
            return MedRangedSetup;
        }
        if (Skills.realLevel(Constants.SKILLS_RANGE) <= 29) {
            return LowPlusRangedSetup;
        }
        else return LowRangedSetup;
    }
}