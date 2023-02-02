package Helpers;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

import Firemaking.GoFiremaking;

public class SkillData {

    //Mining
    public static Tile miningCopperLocation = new Tile(3287,3366);
    public static Tile miningIronLocation = new Tile(3286,3368);

    public static int[] pickaxes = {ItemList.BRONZE_PICKAXE_1265, ItemList.STEEL_PICKAXE_1269, ItemList.MITHRIL_PICKAXE_1273, ItemList.ADAMANT_PICKAXE_1271, ItemList.RUNE_PICKAXE_1275};

    public static Tile movementMining(){
        if (Skill.Mining.realLevel() <= 25) {
            return miningCopperLocation;
        }
        if (Skill.Mining.realLevel() >= 26) {
            return miningIronLocation;
        }
        return null;
    }
    public static int withdrawPickaxe() {
        if (Skill.Mining.realLevel() < 6) {
            return ItemList.BRONZE_PICKAXE_1265;
        }
        if (Skill.Mining.realLevel() >= 6 && Skill.Mining.realLevel() < 21) {
            return ItemList.STEEL_PICKAXE_1269;
        }
        if (Skill.Mining.realLevel() >= 21 && Skill.Mining.realLevel() < 31) {
            return ItemList.MITHRIL_PICKAXE_1273;
        }
        if (Skill.Mining.realLevel() >= 31 && Skill.Mining.realLevel() < 41) {
            return ItemList.ADAMANT_PICKAXE_1271;
        }
        if (Skill.Mining.realLevel() > 41) {
            return ItemList.RUNE_PICKAXE_1275;
        }
        if (Bank.stream().id(ItemList.STEEL_PICKAXE_1269, ItemList.MITHRIL_PICKAXE_1273, ItemList.ADAMANT_PICKAXE_1271, ItemList.RUNE_PICKAXE_1275).count() == 0) {
            return ItemList.BRONZE_PICKAXE_1265;
        }
        return 0;
    }

    //Combat
    public static final Area Seagull_area = new Area(
            new Tile(3151, 2841),
            new Tile(3157,2850)
    );

    //Fishing
    public static final Area AlKharidFishingSpot1 = new Area(
            new Tile(3264, 3150),
            new Tile(3270,3145)
    );
    public static final Area AlKharidFishingSpot2 = new Area(
            new Tile(3273, 3142),
            new Tile(3280,3137)
    );
    public static final Area BarbarianVillageFishingArea = new Area(
            new Tile(3101, 3422),
            new Tile(3109,3436)
    );

    public static Tile movementFishing(){
        if (Skill.Fishing.realLevel() <= 19) {
            return AlKharidFishingSpot1.getRandomTile();
        }
        if (Skill.Fishing.realLevel() >= 20) {
            return BarbarianVillageFishingArea.getRandomTile();
        }
        return null;
    }

    //Woodcutting
    public static int[] normalTreeID = {1276, 1278};
    public static int[] oakTreeID = {10820};
    public static int[] willowTreeID = {10819};
    public static int[] teakTreeID = {40758};

    public static final Area normalTreeLocation = new Area (
            new Tile(3074,3274),
            new Tile(3086, 3263)
    );
    public static final Area oakTreeLocation = new Area (
            new Tile(3099,3245),
            new Tile(3104, 3240)
    );
    public static final Area willowTreeLocation = new Area (
            new Tile(3056,3255),
            new Tile(3064, 3249)
    );
    public static final Area teakLocation = new Area (
            new Tile(2185,2987),
            new Tile(2185, 2991)
    );

    public static Tile movementWoodcutting(){
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 14) {
            return normalTreeLocation.getRandomTile();
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 15 && (Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 30)) {
            return oakTreeLocation.getRandomTile();
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 30) {
            return willowTreeLocation.getRandomTile();
        }
        return null;
    }
    public static int withdrawAxe() {
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >41) {
            return ItemList.RUNE_AXE_1359;
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 31) {
            return ItemList.ADAMANT_AXE_1357;
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 21) {
            return ItemList.MITHRIL_AXE_1355;
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 6) {
            return ItemList.STEEL_AXE_1353;
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 6) {
            return ItemList.BRONZE_AXE_1351;
        }
        if (Bank.stream().id(ItemList.STEEL_AXE_1353, ItemList.MITHRIL_AXE_1355, ItemList.ADAMANT_AXE_1357, ItemList.RUNE_AXE_1359).count() == 0) {
            return ItemList.BRONZE_AXE_1351;
        }
        return 0;
    }


    public static final int[] wcAxes = {ItemList.BRONZE_AXE_1351, ItemList.STEEL_AXE_1353, ItemList.MITHRIL_AXE_1355, ItemList.ADAMANT_AXE_1357, ItemList.RUNE_AXE_1359};

    //Cooking
    public static final Area cookingAreaEdgeville = new Area (
            new Tile(3077,3496),
            new Tile(3081,3491)
    );

    public static GameObject cookingStove = Objects.stream().id(12269).first();

    public static final int[] rawFish = {ItemList.RAW_SHRIMPS_317, ItemList.RAW_ANCHOVIES_321, ItemList.RAW_TROUT_335, ItemList.RAW_SALMON_331};
    public static final int[] cookedFish = {ItemList.SHRIMPS_315, ItemList.ANCHOVIES_319, ItemList.TROUT_333, ItemList.SALMON_329};


    //Firemaking
    public static final Tile firemakingGE1 = new Tile(3196,3489);
    public static final Tile firemakingGE2 = new Tile(3196,3490);
    public static final Tile firemakingGE3 = new Tile(3196,3491);

    public static int[] logs = {ItemList.LOGS_1511, ItemList.OAK_LOGS_1521, ItemList.WILLOW_LOGS_1519};

    public static final Area firemakingArea = new Area (
            new Tile(3195, 3491, 0),
            new Tile(3198, 3487, 0)
    );
    public static final Area doFiremakingArea = new Area (
            new Tile(3196, 3488, 0),
            new Tile(3168, 3491, 0)
    );
    public static Tile moveToFiremakingSpot(){
        if (GoFiremaking.fmSpot == 1) {
            return firemakingGE1;
        }
        if (GoFiremaking.fmSpot == 2) {
            return firemakingGE2;
        }
        if (GoFiremaking.fmSpot == 3) {
            return firemakingGE3;
        }
        return null;
    }
    public static int withdrawLogs() {
        if (Skills.realLevel(Constants.SKILLS_FIREMAKING) <= 14) {
            return ItemList.LOGS_1511;
        }
        if (Skills.realLevel(Constants.SKILLS_FIREMAKING) >= 15 && Skills.realLevel(Constants.SKILLS_FIREMAKING) <= 29) {
            return ItemList.OAK_LOGS_1521;
        }
        if (Skills.realLevel(Constants.SKILLS_FIREMAKING) > 30) {
            return ItemList.WILLOW_LOGS_1519;
        }
        return 0;
    }

    //Smithing
    public static int[] copperBarOres = {ItemList.COPPER_ORE_436, ItemList.TIN_ORE_438};
    public static int[] ironBarOres = {ItemList.IRON_ORE_440};
    public static int[] smithingOres = {ItemList.COPPER_ORE_436, ItemList.TIN_ORE_438, ItemList.IRON_ORE_440};
    public static int[] smithingBars = {ItemList.BRONZE_BAR_2349, ItemList.IRON_BAR_2351};
    public static int[] bronzeWarhammer = {ItemList.BRONZE_WARHAMMER_1337};
    public static int copperOre = ItemList.COPPER_ORE_436;
    public static int tinOre = ItemList.TIN_ORE_438;
    public static int ironOre = ItemList.IRON_ORE_440;
    public static int bronzeBar = ItemList.BRONZE_BAR_2349;
    public static Area smithingAreaEdgeville = new Area(new Tile(3109, 3499, 0), new Tile(3108, 3498, 0));
    public static Tile smithingTileVarrockWest = new Tile(3188,3427);
    public static Area edgevilleBank = new Area(new Tile(3091, 3499, 0), new Tile(3098, 3488, 0));
    public static Area varrockWestBank = new Area(new Tile(3180, 3447, 0), new Tile(3190, 3433, 0));

    //Thieving
    public static final Area thievingMenArea = new Area (
            new Tile(3225, 3209, 0),
            new Tile(3212, 3228, 0)
    );
    public static Tile teaStallTile = new Tile(3269,3412);
    public static Tile fruitStallTile = new Tile(1796, 3607);
    public static Tile movementThieving(){
        if (Skills.realLevel(Constants.SKILLS_THIEVING) <= 5) {
            return thievingMenArea.getRandomTile();
        }
        if (Skills.realLevel(Constants.SKILLS_THIEVING)  > 5 && Skills.realLevel(Constants.SKILLS_THIEVING) <= 24) {
            return teaStallTile;
        }
        if (Skills.realLevel(Constants.SKILLS_THIEVING) >= 25) {
            return fruitStallTile;
        }
        return null;
    }

    //Crafting
    public static final Area craftingArea = new Area (
            new Tile(3160, 3493, 0),
            new Tile(3168, 3486, 0)
    );
    //Fletching
    public static final Area fletchingArea = new Area (
            new Tile(3160, 3493, 0),
            new Tile(3168, 3486, 0)
    );
}
