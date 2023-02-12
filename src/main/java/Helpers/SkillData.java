package Helpers;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

import Firemaking.DoFiremaking;
import script.mMain;

public class SkillData {
    //Skill finished
    public static Boolean MiningDone = false;
    public static Boolean FishingDone = false;
    public static Boolean MeleeCombatDone = false;
    public static Boolean WoodcuttingDone = false;
    public static Boolean CookingDone = false;
    public static Boolean FiremakingDone = false;
    public static Boolean SmithingDone = false;
    public static Boolean ThievingDone = false;
    public static Boolean CraftingDone = false;
    public static Boolean FletchingDone = false;
    public static Boolean AgilityDone = false;
    public static Boolean HerbloreDone = false;
    public static Boolean RangeCombatDone = false;

    public static boolean AllSkillsDone() {
        return MiningDone
                && FishingDone
                && WoodcuttingDone
                && CookingDone
                && FiremakingDone
                && SmithingDone
                && ThievingDone
                && CraftingDone
                && FletchingDone
                && AgilityDone
                && HerbloreDone
                && MeleeCombatDone
                && RangeCombatDone;
    }
    public static void SetSkillDone() {
        if (mMain.RunningSkill.equals("Mining")) {
            MiningDone = true;
        }
        if (mMain.RunningSkill.equals("Fishing")) {
            FishingDone = true;
        }
        if (mMain.RunningSkill.equals("Woodcutting")) {
            WoodcuttingDone = true;
        }
        if (mMain.RunningSkill.equals("Cooking")) {
            CookingDone = true;
        }
        if (mMain.RunningSkill.equals("Firemaking")) {
            FiremakingDone = true;
        }
        if (mMain.RunningSkill.equals("Smithing")) {
            SmithingDone = true;
        }
        if (mMain.RunningSkill.equals("Thieving")) {
            ThievingDone = true;
        }
        if (mMain.RunningSkill.equals("Crafting")) {
            CraftingDone = true;
        }
        if (mMain.RunningSkill.equals("Fletching")) {
            FletchingDone = true;
        }
        if (mMain.RunningSkill.equals("Agility")) {
            AgilityDone = true;
        }
        if (mMain.RunningSkill.equals("Herblore")) {
            HerbloreDone = true;
        }
        if (mMain.RunningSkill.equals("Melee combat")) {
            MeleeCombatDone = true;
        }
        if (mMain.RunningSkill.equals("Ranged")) {
            RangeCombatDone = true;
        }
    }

    public static final int[] p2p = {302, 303, 304, 305, 306, 307, 309, 310, 311, 312, 313, 314, 315, 317, 318, 319, 320, 321, 322, 323};

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
        if (Skill.Mining.realLevel() < 11) {
            return ItemList.BRONZE_PICKAXE_1265;
        }
        if (Skill.Mining.realLevel() >= 11 && Skill.Mining.realLevel() < 21) {
            return ItemList.BLACK_PICKAXE_12297;
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
        else return ItemList.BRONZE_PICKAXE_1265;
    }

    //Combat
    public static final Area Seagull_area = new Area(
            new Tile(3151, 2841), new Tile(3157,2850)
    );

    //Fishing
    public static final Area AlKharidFishingSpot1 = new Area(
            new Tile(3262, 3154, 0), new Tile(3274, 3143, 0)
    );
    public static final Area AlKharidFishingSpot2 = new Area(
            new Tile(3273, 3142, 0), new Tile(3282, 3135, 0)
    );
    public static final Area BarbarianVillageFishingArea = new Area(
            new Tile(3101, 3422), new Tile(3109,3436)
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
            new Tile(3074,3274), new Tile(3086, 3263)
    );
    public static final Area oakTreeLocation = new Area (
            new Tile(3099,3245), new Tile(3104, 3240)
    );
    public static final Area willowTreeLocation = new Area (
            new Tile(3056,3255), new Tile(3064, 3249)
    );
    public static final Area teakArea = new Area (
            new Tile(2185,2987), new Tile(2185, 2991)
    );
    public static final Tile teakLocation = new Tile (2185,2989);

    public static Tile movementWoodcutting(){
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 14) {
            return normalTreeLocation.getRandomTile();
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 15 && (Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 30)) {
            return oakTreeLocation.getRandomTile();
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 30 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 35) {
            return willowTreeLocation.getRandomTile();
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 35) {
            return teakLocation;
        }
        return null;
    }
    public static int withdrawAxe() {
        if (Bank.stream().id(ItemList.STEEL_AXE_1353, ItemList.MITHRIL_AXE_1355, ItemList.ADAMANT_AXE_1357, ItemList.RUNE_AXE_1359).isEmpty()) {
            return ItemList.BRONZE_AXE_1351;
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 41) {
            return ItemList.RUNE_AXE_1359;
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 31 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 40) {
            return ItemList.ADAMANT_AXE_1357;
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 21 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 30) {
            return ItemList.MITHRIL_AXE_1355;
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 6 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 20) {
            return ItemList.STEEL_AXE_1353;
        }
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 6 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 5) {
            return ItemList.BRONZE_AXE_1351;
        }
        return 0;
    }
    public static int[] wcAxes = {ItemList.BRONZE_AXE_1351, ItemList.STEEL_AXE_1353, ItemList.MITHRIL_AXE_1355, ItemList.ADAMANT_AXE_1357, ItemList.RUNE_AXE_1359};


    //Cooking
    public static final Area cookingAreaEdgeville = new Area (
            new Tile(3098, 3484, 0), new Tile(3076, 3506, 0)
    );
    public static final Area StoveAreaEdgeville = new Area (
            new Tile(3077,3496), new Tile(3081,3491)
    );

    //Firemaking
    public static final Tile firemakingLane1 = new Tile(3032,3360);
    public static final Tile firemakingLane2 = new Tile(3032,3361);
    public static final Tile firemakingLane3 = new Tile(3032,3362);

    public static int[] logs = {ItemList.LOGS_1511, ItemList.OAK_LOGS_1521, ItemList.WILLOW_LOGS_1519};

    public static final Area firemakingStartArea = new Area (
            new Tile(3032, 3360, 0), new Tile(3032, 3362, 0)
    );
    public static final Area doFiremakingArea = new Area (
            new Tile(3032, 3362, 0), new Tile(3005, 3360, 0)
    );
    public static Tile moveToFiremakingSpot(){
        if (DoFiremaking.fmSpot == 1) {
            return firemakingLane1;
        }
        if (DoFiremaking.fmSpot == 2) {
            return firemakingLane2;
        }
        if (DoFiremaking.fmSpot == 3) {
            return firemakingLane3;
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
        if (Skills.realLevel(Constants.SKILLS_FIREMAKING) >= 30) {
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
            new Tile(3225, 3209, 0), new Tile(3212, 3228, 0)
    );
    public static final Area fruitStallArea = new Area (
            new Tile(1794, 3609, 0), new Tile(1802, 3605, 0)
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

    //Agility
    //Gnome
    public static final Area GnomeStartArea = new Area (
            new Tile(2474, 3438, 0), new Tile(2477, 3437, 0)
    );
    public static final Area GnomeObstacleLowerArea = new Area (
            new Tile(2468, 3441, 0), new Tile(2491, 3413, 0)
    );
    public static final Area GnomeObstacleAreaMidFloor = new Area (
            new Tile(2469, 3425, 1), new Tile(2477, 3421, 1)
    );
    public static final Area GnomeObstacleAreaTopFloor = new Area (
            new Tile(2470, 3423, 2), new Tile(2490, 3416, 2)
    );
    public static final Area GnomeObstacle1Area = new Area (
            new Tile(2472, 3436, 0), new Tile(2488, 3440, 0)
    );
    public static final Area GnomeObstacle2Area = new Area (
            new Tile(2471, 3429, 0), new Tile(2477, 3427, 0)
    );
    public static final Area GnomeObstacle3Area = new Area (
            new Tile(2476, 3424, 1), new Tile(2471, 3422, 1)
    );
    public static final Area GnomeObstacle4Area = new Area (
            new Tile(2472, 3420, 2), new Tile(2477, 3419, 2)
    );
    public static final Area GnomeObstacle5Area = new Area (
            new Tile(2483, 3420, 2), new Tile(2488, 3418, 2)
    );
    public static final Area GnomeObstacle6Area = new Area (
            new Tile(2489, 3417, 0), new Tile(2481, 3425, 0)
    );
    public static final Area GnomeObstacle7Area = new Area (
            new Tile(2489, 3427, 0), new Tile(2482, 3431, 0)
    );

    //Draynor
    public static final Area DraynorFloorArea = new Area (
            new Tile(3081, 3268, 0),
            new Tile(3079, 3262, 0),
            new Tile(3079, 3254, 0),
            new Tile(3079, 3248, 0),
            new Tile(3085, 3246, 0),
            new Tile(3088, 3246, 0),
            new Tile(3088, 3240, 0),
            new Tile(3096, 3240, 0),
            new Tile(3098, 3240, 0),
            new Tile(3098, 3247, 0),
            new Tile(3103, 3247, 0),
            new Tile(3106, 3249, 0),
            new Tile(3109, 3257, 0),
            new Tile(3111, 3265, 0),
            new Tile(3107, 3270, 0),
            new Tile(3107, 3278, 0),
            new Tile(3108, 3285, 0),
            new Tile(3094, 3286, 0),
            new Tile(3082, 3280, 0)
    );
    public static final Area DraynorTopArea = new Area (
            new Tile(3084, 3283, 3), new Tile(3105, 3253, 3)
    );
    public static final Area DraynorCrateArea = new Area (
            new Tile(3101, 3262, 1), new Tile(3103, 3260, 1)
    );
    public static final Area DraynorStart = new Area (
            new Tile(3106, 3275, 0), new Tile(3103, 3281, 0)
    );
    public static final Area DraynorObstacle2 = new Area (
            new Tile(3102, 3277, 3), new Tile(3097, 3281, 3)
    );
    public static final Area DraynorObstacle3 = new Area (
            new Tile(3089, 3276, 3),
            new Tile(3091, 3276, 3),
            new Tile(3090, 3273, 3),
            new Tile(3089, 3273, 3),
            new Tile(3088, 3274, 3),
            new Tile(3088, 3276, 3)
    );
    public static final Area DraynorObstacle4 = new Area (
            new Tile(3089, 3268, 3), new Tile(3094, 3265, 3)
    );
    public static final Area DraynorObstacle5 = new Area (
            new Tile(3088, 3257, 3), new Tile(3088, 3261, 3)
    );
    public static final Area DraynorObstacle6 = new Area (
            new Tile(3087, 3255, 3), new Tile(3094, 3255, 3)
    );
    public static final Area DraynorObstacle7 = new Area (
            new Tile(3096, 3261, 3), new Tile(3102, 3256, 3)
    );
    //Varrock
    //Varrock
    public static final Area VarrockFloorArea = new Area (
            new Tile(3183, 3431, 0), new Tile(3244, 3391, 0)
    );

    public static final Area VarrockFirstArea = new Area (
            new Tile(3183, 3420, 1), new Tile(3204, 3406, 1)
    );

    public static final Area VarrockMidArea = new Area (
            new Tile(3132, 3459, 2), new Tile(3331, 3326, 2)
    );

    public static final Area VarrockTopArea = new Area (
            new Tile(3105, 3466, 3), new Tile(3338, 3274, 3)
    );

    public static final Area VarrockGhostArea = new Area (
            new Tile(3113, 3411, 1), new Tile(3222, 3314, 1)
    );

    public static final Area VarrockFailArea = new Area (
            new Tile(3183, 3431, 0), new Tile(3244, 3391, 0)
    );
    public static final Area VarrockStart = new Area (
            new Tile(3221, 3411, 0),
            new Tile(3224, 3412, 0),
            new Tile(3224, 3416, 0),
            new Tile(3221, 3416, 0)
    );

    public static final Area VarrockObstacle2 = new Area (
            new Tile(3214, 3420, 3), new Tile(3220, 3409, 3)
    );
    public static final Area VarrockObstacle3 = new Area (
            new Tile(3201, 3420, 3), new Tile(3208, 3412, 3)
    );
    public static final Area VarrockObstacle4 = new Area (
            new Tile(3197, 3416, 1), new Tile(3194, 3416, 1)
    );
    public static final Area VarrockObstacle5 = new Area (
            new Tile(3192, 3406, 3), new Tile(3198, 3402, 3)
    );
    public static final Area VarrockObstacle6 = new Area (
            new Tile(3190, 3382, 3),
            new Tile(3180, 3382, 3),
            new Tile(3180, 3399, 3),
            new Tile(3201, 3399, 3),
            new Tile(3201, 3404, 3),
            new Tile(3209, 3404, 3),
            new Tile(3209, 3395, 3),
            new Tile(3203, 3394, 3)
    );

    public static final Area VarrockObstacle6MoveTo = new Area (
            new Tile(3206, 3403, 3), new Tile(3208, 3395, 3)
    );
    public static final Area VarrockObstacle7 = new Area (
            new Tile(3218, 3405, 3), new Tile(3232, 3392, 3)
    );

    public static final Area VarrockObstacle7MoveTo = new Area (
            new Tile(3229, 3402, 3), new Tile(3232, 3400, 3)
    );

    public static final Area VarrockObstacle8 = new Area (
            new Tile(3236, 3403, 3), new Tile(3240, 3408, 3)
    );

    public static final Area VarrockObstacle9 = new Area (
            new Tile(3236, 3410, 3), new Tile(3240, 3415, 3)
    );

    //Canifis
    public static final Area CanifisFloorArea = new Area (
            new Tile(3469, 3511, 0), new Tile(3520, 3464, 0)
    );

    public static final Area CanifisFirstArea = new Area (
            new Tile(3464, 3513, 1), new Tile(3529, 3457, 1)
    );

    public static final Area CanifisMidArea = new Area (
            new Tile(3464, 3513, 2), new Tile(3529, 3457, 2)
    );

    public static final Area CanifisTopArea = new Area (
            new Tile(3464, 3513, 3), new Tile(3529, 3457, 3)
    );

    public static final Area CanifisObstacle2MoveTo = new Area (
            new Tile(3504, 3497, 2), new Tile(3507, 3496, 2)
    );
    public static final Area CanifisObstacle7MoveTo = new Area (
            new Tile(3499, 3477, 3), new Tile(3504, 3474, 3)
    );

    public static final Area CanifisObstacle1Bug = new Area (
            new Tile(3505, 3489, 2), new Tile(3505, 3489, 2)
    );

    public static final Area CanifisStart = new Area (
            new Tile(3504, 3488, 0), new Tile(3507, 3486, 0)
    );

    public static final Area CanifisObstacle1 = new Area (
            new Tile(3502, 3490, 0), new Tile(3512, 3484, 0)
    );

    public static final Area CanifisObstacle2 = new Area (
            new Tile(3502, 3499, 2), new Tile(3512, 3490, 2)
    );

    public static final Area CanifisObstacle3 = new Area (
            new Tile(3495, 3508, 2), new Tile(3505, 3502, 2)
    );
    public static final Area CanifisObstacle4 = new Area (
            new Tile(3493, 3506, 2), new Tile(3484, 3497, 2)
    );
    public static final Area CanifisObstacle5 = new Area (
            new Tile(3479, 3499, 3), new Tile(3473, 3491, 3)
    );
    public static final Area CanifisObstacle6 = new Area (
            new Tile(3476, 3488, 2), new Tile(3485, 3480, 2)
    );

    public static final Area CanifisObstacle7 = new Area (
            new Tile(3486, 3480, 3), new Tile(3505, 3467, 3)
    );

    public static final Area CanifisObstacle8 = new Area (
            new Tile(3507, 3484, 2), new Tile(3516, 3473, 2)
    );

    public static Tile movementAgility(){
        if (Skills.realLevel(Constants.SKILLS_AGILITY) <= 9) {
            return GnomeStartArea.getRandomTile();
        }
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 10 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 29) {
            return DraynorStart.getRandomTile();
        }
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 30 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 39) {
            return VarrockStart.getRandomTile();
        }
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 40 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 80) {
            return CanifisStart.getRandomTile();
        }
        return null;
    }

    //Range
    public static final Area RangeSafespot = new Area(
                    new Tile(2927, 3292, 0),
                    new Tile(2922, 3292, 0),
                    new Tile(2921, 3293, 0),
                    new Tile(2920, 3293, 0),
                    new Tile(2915, 3293, 0),
                    new Tile(2915, 3295, 0),
                    new Tile(2921, 3295, 0),
                    new Tile(2927, 3294, 0)
    );
    public static final Area CowArea = new Area(
            new Tile(2926, 3288, 0), new Tile(2914, 3292, 0)
    );
}
