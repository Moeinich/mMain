package MagicCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Magic;
import org.powbot.api.rt4.Skills;

import Helpers.ItemList;

public class MagicData {

    //Mage setups!
    public static int[] LowMage = {ItemList.ZAMORAK_ROBE_1035, ItemList.ZAMORAK_ROBE_1033, ItemList.BLUE_WIZARD_HAT_579, ItemList.STAFF_OF_AIR_1381, ItemList.AMULET_OF_GLORY_1704, ItemList.COMBAT_BRACELET_11126, ItemList.RED_CAPE_1007};
    public static int[] MedMage = {ItemList.ZAMORAK_ROBE_1035, ItemList.ZAMORAK_ROBE_1033, ItemList.BLUE_WIZARD_HAT_579, ItemList.STAFF_OF_FIRE_1387, ItemList.AMULET_OF_GLORY_1704, ItemList.COMBAT_BRACELET_11126, ItemList.RED_CAPE_1007};

    //Runes!
    public static int[] Runes = {ItemList.AIR_RUNE_556, ItemList.MIND_RUNE_558};

    public static int[] MagicEquipment() {
        if (Skills.realLevel(Constants.SKILLS_MAGIC) <= 12) {
            return LowMage;
        }
        if (Skills.realLevel(Constants.SKILLS_MAGIC) >= 13) {
            return MedMage;
        }
        return LowMage;
    }
    public static Magic.Spell MagicSpell() {
        if (Skills.realLevel(Constants.SKILLS_MAGIC) <= 12) {
            return Magic.Spell.WIND_STRIKE;
        }
        if (Skills.realLevel(Constants.SKILLS_MAGIC) >= 13) {
            return Magic.Spell.FIRE_STRIKE;
        }
        return Magic.Spell.WIND_STRIKE;
    }

}