package Helpers;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Varpbits;
import org.powbot.api.rt4.walking.model.Skill;

import Firemaking.DoFiremaking;
import script.mMain;

public class SkillData {
    //Skill finished
    public static Boolean miningDone = false;
    public static Boolean fishingDone = false;
    public static Boolean meleeCombatDone = false;
    public static Boolean woodcuttingDone = false;
    public static Boolean cookingDone = false;
    public static Boolean firemakingDone = false;
    public static Boolean smithingDone = false;
    public static Boolean thievingDone = false;
    public static Boolean craftingDone = false;
    public static Boolean fletchingDone = false;
    public static Boolean agilityDone = false;
    public static Boolean herbloreDone = false;
    public static Boolean rangeCombatDone = false;
    public static Boolean magicCombatDone = false;

    public static boolean allSkillsDone() {
        return miningDone
                && fishingDone
                && woodcuttingDone
                && cookingDone
                && firemakingDone
                && smithingDone
                && thievingDone
                && craftingDone
                && fletchingDone
                && agilityDone
                && herbloreDone
                && meleeCombatDone
                && rangeCombatDone
                && magicCombatDone;
    }
    public static void setSkillDone() {
        if (mMain.runningSkill.equals("Mining")) {
            miningDone = true;
        }
        if (mMain.runningSkill.equals("Fishing")) {
            fishingDone = true;
        }
        if (mMain.runningSkill.equals("Woodcutting")) {
            woodcuttingDone = true;
        }
        if (mMain.runningSkill.equals("Cooking")) {
            cookingDone = true;
        }
        if (mMain.runningSkill.equals("Firemaking")) {
            firemakingDone = true;
        }
        if (mMain.runningSkill.equals("Smithing")) {
            smithingDone = true;
        }
        if (mMain.runningSkill.equals("Thieving")) {
            thievingDone = true;
        }
        if (mMain.runningSkill.equals("Crafting")) {
            craftingDone = true;
        }
        if (mMain.runningSkill.equals("Fletching")) {
            fletchingDone = true;
        }
        if (mMain.runningSkill.equals("Agility")) {
            agilityDone = true;
        }
        if (mMain.runningSkill.equals("Herblore")) {
            herbloreDone = true;
        }
        if (mMain.runningSkill.equals("Melee")) {
            meleeCombatDone = true;
        }
        if (mMain.runningSkill.equals("Ranged")) {
            rangeCombatDone = true;
        }
        if (mMain.runningSkill.equals("Magic")) {
            rangeCombatDone = true;
        }
    }

    public enum KOUREND_FAVOR {
        ARCEUUS(4896),
        HOSIDIUS(4895),
        LOVAKENGJ(4898),
        PISCARILIUS(4899),
        SHAYZIEN(4894);
        final int var;
        KOUREND_FAVOR(int var){
            this.var = var;
        }
        public int getValue(){
            return (int) (Varpbits.value(var) * .1);
        }
        public static boolean checkedFavor = false;
        public static int hosidiusFavorValue = 0;
    }
    public static final int[] p2p = {477, 478, 480, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497};

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
    public static Area edgevilleBank = new Area(new Tile(3091, 3499, 0), new Tile(3098, 3488, 0));

    //Firemaking
    public static final Tile firemakingLane1 = new Tile(3032,3360);
    public static final Tile firemakingLane2 = new Tile(3032,3361);

    public static int[] logs = {ItemList.LOGS_1511, ItemList.OAK_LOGS_1521, ItemList.WILLOW_LOGS_1519};

    public static final Area firemakingStartArea = new Area (
            new Tile(3032, 3360, 0), new Tile(3032, 3361, 0)
    );
    public static final Area doFiremakingArea = new Area (
            new Tile(3033, 3362, 0), new Tile(3004, 3359, 0)
    );
    public static Tile moveToFiremakingSpot(){
        if (DoFiremaking.fmSpot == 1) {
            return firemakingLane1;
        }
        if (DoFiremaking.fmSpot == 2) {
            return firemakingLane2;
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

    //Thieving
    public static final Area thievingMenArea = new Area (
            new Tile(3225, 3209, 0), new Tile(3212, 3228, 0)
    );
    public static final Area fruitStallArea = new Area (
            new Tile(1796, 3607, 0), new Tile(1796, 3608, 0)
    );
    public static final Area teaStallArea = new Area (
            new Tile(3272, 3408, 0), new Tile(3267, 3415, 0)
    );
    public static Tile teaStallTile = new Tile(3269,3412);
    public static Tile fruitStallTile = new Tile(1796, 3607);
    public static Tile movementThieving(){
        if (Skills.realLevel(Constants.SKILLS_THIEVING) <= 4) {
            return thievingMenArea.getRandomTile();
        }
        if (Skills.realLevel(Constants.SKILLS_THIEVING)  >= 5 && Skills.realLevel(Constants.SKILLS_THIEVING) <= 24) {
            return teaStallTile;
        }
        if (Skills.realLevel(Constants.SKILLS_THIEVING) >= 25) {
            return fruitStallTile;
        }
        return null;
    }

    //Range
    public static final Area CowSafeSpotArea = new Area(
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
