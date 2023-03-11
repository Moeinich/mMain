package helpers;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Varpbits;
import org.powbot.api.rt4.Widgets;

import java.util.HashMap;
import java.util.Map;

import script.mMain;

public class SkillData {
    //Skill finished
    public static final Map<String, Boolean> skillsMap = new HashMap<>();
    static {
        skillsMap.put("mining", false);
        skillsMap.put("fishing", false);
        skillsMap.put("woodcutting", false);
        skillsMap.put("cooking", false);
        skillsMap.put("firemaking", false);
        skillsMap.put("smithing", false);
        skillsMap.put("thieving", false);
        skillsMap.put("crafting", false);
        skillsMap.put("fletching", false);
        skillsMap.put("agility", false);
        skillsMap.put("herblore", false);
        skillsMap.put("hunter", false);
        skillsMap.put("melee", false);
        skillsMap.put("ranged", false);
        skillsMap.put("runecrafting", false);
        skillsMap.put("magic", false);
    }

    public static void setSkillDone() {
        String skill = mMain.runningSkill.toLowerCase();
        if (skillsMap.containsKey(skill)) {
            skillsMap.put(skill, true);
        }
    }
    public static boolean allSkillsDone() {
        for (Boolean value : skillsMap.values()) {
            if (!value) {
                return false;
            }
        }
        return true;
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
    public static final int[] p2p = {544, 543, 535, 534, 531, 525, 524, 523, 522, 521, 520, 519, 518, 517, 516, 515, 514, 513, 512, 511, 510, 509, 508, 507, 506, 505, 496, 495, 494, 493, 492, 491, 490, 489, 488, 487, 486, 485, 484, 482, 481, 480, 477, 478, 497};
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
    public static Area edgevilleBank = new Area(new Tile(3091, 3488, 0), new Tile(3094, 3493, 0));
}
