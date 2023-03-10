package woodcutting;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Skills;

import helpers.extentions.ItemList;

public class WoodcuttingData {
    public static final Area normalTreeLocation = new Area (
            new Tile(3074,3274), new Tile(3086, 3263)
    );
    public static final Area oakTreeLocation = new Area (
            new Tile(3060, 3272, 0), new Tile(3040, 3261, 0)
    );
    public static final Area willowTreeLocation = new Area (
            new Tile(3056,3255), new Tile(3064, 3249)
    );
    public static Area teakArea = new Area (
            new Tile(2185, 2990, 0), new Tile(2185, 2987, 0)
    );
    public static final Tile teakLocation = new Tile (2185,2989);
    //Woodcutting
    public static int[] normalTreeID = {1276, 1278};
    public static int[] oakTreeID = {10820};
    public static int[] willowTreeID = {10819};
    public static int[] teakTreeID = {40758};
    public static int[] wcAxes = {ItemList.BRONZE_AXE_1351, ItemList.STEEL_AXE_1353, ItemList.MITHRIL_AXE_1355, ItemList.ADAMANT_AXE_1357, ItemList.RUNE_AXE_1359};

    public static Tile movementWoodcutting(){
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 15 && (Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 30)) {
            return oakTreeLocation.getRandomTile();
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 30 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 35) {
            return willowTreeLocation.getRandomTile();
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 35) {
            return teakLocation;
        }
        return normalTreeLocation.getRandomTile();
    }

    public static int withdrawAxe() {
        int currentAxeId = Inventory.stream().id(WoodcuttingData.wcAxes).first().getId();
        int newAxeId;
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 41) {
            newAxeId = ItemList.RUNE_AXE_1359;
        } else if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 31) {
            newAxeId = ItemList.ADAMANT_AXE_1357;
        } else if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 21) {
            newAxeId = ItemList.MITHRIL_AXE_1355;
        } else if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 6) {
            newAxeId = ItemList.STEEL_AXE_1353;
        } else {
            newAxeId = ItemList.BRONZE_AXE_1351;
        }

        if (currentAxeId != newAxeId) {
            return newAxeId;
        }
        return currentAxeId;
    }
}
