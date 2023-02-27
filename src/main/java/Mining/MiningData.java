package Mining;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

import Helpers.ItemList;
import Woodcutting.WoodcuttingData;

public class MiningData {
    //Mining
    public static Tile miningCopperLocation = new Tile(3287,3366);
    public static Area miningCopperArea = new Area(new Tile(3286, 3367, 0), new Tile(3288, 3365, 0));
    public static Tile miningIronLocation = new Tile(1475,3779);
    public static Area miningIronArea = new Area(new Tile(1476, 3777, 0), new Tile(1473, 3781, 0));
    public static int[] pickaxes = {ItemList.BRONZE_PICKAXE_1265, ItemList.BLACK_PICKAXE_12297, ItemList.MITHRIL_PICKAXE_1273, ItemList.ADAMANT_PICKAXE_1271, ItemList.RUNE_PICKAXE_1275};

    public static Tile movementMining(){
        if (Skill.Mining.realLevel() <= 14) {
            return miningCopperLocation;
        }
        if (Skill.Mining.realLevel() >= 15) {
            return miningIronLocation;
        }
        return null;
    }

    public static int withdrawPickaxe() {
        int currentAxeId = Inventory.stream().id(WoodcuttingData.wcAxes).first().getId();
        int newAxeId;
        if (Skills.realLevel(Constants.SKILLS_MINING) >= 41) {
            newAxeId = ItemList.RUNE_PICKAXE_1275;
        } else if (Skills.realLevel(Constants.SKILLS_MINING) >= 31) {
            newAxeId = ItemList.ADAMANT_PICKAXE_1271;
        } else if (Skills.realLevel(Constants.SKILLS_MINING) >= 21) {
            newAxeId = ItemList.MITHRIL_PICKAXE_1273;
        } else if (Skills.realLevel(Constants.SKILLS_MINING) >= 6) {
            newAxeId = ItemList.STEEL_PICKAXE_1269;
        } else {
            newAxeId = ItemList.BRONZE_PICKAXE_1265;
        }

        if (currentAxeId != newAxeId) {
            return newAxeId;
        }
        return currentAxeId;
    }
}
