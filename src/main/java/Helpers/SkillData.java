package Helpers;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Varpbits;

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

    //Shared areas
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
    public static Area edgevilleBank = new Area(new Tile(3091, 3499, 0), new Tile(3098, 3488, 0));

}
