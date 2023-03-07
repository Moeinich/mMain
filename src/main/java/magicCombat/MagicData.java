package magicCombat;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Magic;
import org.powbot.api.rt4.Skills;

import helpers.extentions.ItemList;

public class MagicData {
    public static final Area HobgoblinSafeSpotArea = new Area(
            new Tile(2914, 3292, 0),
            new Tile(2912, 3297, 0),
            new Tile(2914, 3297, 0),
            new Tile(2915, 3293, 0)
    );

    public static final Area HobgoblinArea = new Area(new Tile(2901, 3300, 0), new Tile(2911, 3287, 0));

    //Mage setups!
    public static int[] LowMage = {ItemList.ZAMORAK_ROBE_1035, ItemList.ZAMORAK_ROBE_1033, ItemList.BLUE_WIZARD_HAT_579, ItemList.STAFF_OF_AIR_1381, ItemList.AMULET_OF_GLORY_1704, ItemList.COMBAT_BRACELET_11126, ItemList.RED_CAPE_1007};
    public static int[] MedMage = {ItemList.ZAMORAK_ROBE_1035, ItemList.ZAMORAK_ROBE_1033, ItemList.BLUE_WIZARD_HAT_579, ItemList.STAFF_OF_FIRE_1387, ItemList.AMULET_OF_GLORY_1704, ItemList.COMBAT_BRACELET_11126, ItemList.RED_CAPE_1007};

    //Runes!
    public static int[] Runes = {ItemList.AIR_RUNE_556, ItemList.MIND_RUNE_558};

    public static int[] magicEquipment() {
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