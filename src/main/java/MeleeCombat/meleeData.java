package MeleeCombat;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Combat;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;

import Helpers.ItemList;

public class meleeData {
    public static int[] lowMelee = {ItemList.IRON_PLATEBODY_1115,
            ItemList.IRON_PLATELEGS_1067,
            ItemList.IRON_BOOTS_4121,
            ItemList.IRON_KITESHIELD_1191,
            ItemList.IRON_FULL_HELM_1153,
            ItemList.IRON_SCIMITAR_1323,
            ItemList.AMULET_OF_GLORY_1704,
            ItemList.COMBAT_BRACELET_11126,
            ItemList.RED_CAPE_1007};
    public static int[] medAddyScim = {ItemList.ADAMANT_PLATEBODY_1123,
            ItemList.ADAMANT_PLATELEGS_1073,
            ItemList.ADAMANT_BOOTS_4129,
            ItemList.ADAMANT_KITESHIELD_1199,
            ItemList.ADAMANT_FULL_HELM_1161,
            ItemList.ADAMANT_SCIMITAR_1331,
            ItemList.AMULET_OF_GLORY_1704,
            ItemList.COMBAT_BRACELET_11126,
            ItemList.RED_CAPE_1007};
    public static int[] medRuneScim = {ItemList.ADAMANT_PLATEBODY_1123,
            ItemList.ADAMANT_PLATELEGS_1073,
            ItemList.ADAMANT_BOOTS_4129,
            ItemList.ADAMANT_KITESHIELD_1199,
            ItemList.ADAMANT_FULL_HELM_1161,
            ItemList.RUNE_SCIMITAR_1333,
            ItemList.AMULET_OF_GLORY_1704,
            ItemList.COMBAT_BRACELET_11126,
            ItemList.RED_CAPE_1007};
    public static int[] highMelee = {ItemList.ADAMANT_PLATEBODY_1123,
            ItemList.ADAMANT_PLATELEGS_1073,
            ItemList.ADAMANT_BOOTS_4129,
            ItemList.ADAMANT_KITESHIELD_1199,
            ItemList.ADAMANT_FULL_HELM_1161,
            ItemList.GRANITE_HAMMER_21742,
            ItemList.AMULET_OF_GLORY_1704,
            ItemList.COMBAT_BRACELET_11126,
            ItemList.RED_CAPE_1007};

    public static int[] meleeEquipment() {
        if (Skills.realLevel(Constants.SKILLS_ATTACK) <= 29 && Skills.realLevel(Constants.SKILLS_DEFENSE) <= 29 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 29) {
            return lowMelee;
        }
        if (Skills.realLevel(Constants.SKILLS_ATTACK) >= 30 && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 30 && Skills.realLevel(Constants.SKILLS_STRENGTH) >= 30
        && Skills.realLevel(Constants.SKILLS_ATTACK) <= 39 && Skills.realLevel(Constants.SKILLS_DEFENSE) <= 49 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 39) {
            return medAddyScim;
        }
        if (Skills.realLevel(Constants.SKILLS_ATTACK) >= 40 && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 40 && Skills.realLevel(Constants.SKILLS_STRENGTH) >= 40
                && Skills.realLevel(Constants.SKILLS_ATTACK) <= 49 && Skills.realLevel(Constants.SKILLS_DEFENSE) <= 49 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 49) {
            return medRuneScim;
        }
        if (Skills.realLevel(Constants.SKILLS_ATTACK) >= 50 && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 30 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 50) {
            return highMelee;
        }
        return lowMelee;
    }

    public static final Area goblinArea = new Area(new Tile(3152, 3294, 0), new Tile(3134, 3310, 0));

    public static final Area cowArea = new Area(
            new Tile(3184, 3314, 0),
            new Tile(3179, 3314, 0),
            new Tile(3178, 3316, 0),
            new Tile(3175, 3316, 0),
            new Tile(3170, 3316, 0),
            new Tile(3168, 3319, 0),
            new Tile(3164, 3319, 0),
            new Tile(3160, 3315, 0),
            new Tile(3155, 3315, 0),
            new Tile(3154, 3317, 0),
            new Tile(3153, 3329, 0),
            new Tile(3154, 3331, 0),
            new Tile(3154, 3334, 0),
            new Tile(3153, 3335, 0),
            new Tile(3153, 3338, 0),
            new Tile(3152, 3339, 0),
            new Tile(3152, 3342, 0),
            new Tile(3153, 3344, 0),
            new Tile(3153, 3346, 0),
            new Tile(3160, 3345, 0),
            new Tile(3166, 3342, 0),
            new Tile(3174, 3342, 0),
            new Tile(3180, 3341, 0),
            new Tile(3185, 3339, 0),
            new Tile(3190, 3335, 0),
            new Tile(3196, 3332, 0),
            new Tile(3201, 3328, 0),
            new Tile(3202, 3321, 0),
            new Tile(3205, 3315, 0),
            new Tile(3207, 3310, 0),
            new Tile(3200, 3308, 0),
            new Tile(3196, 3308, 0),
            new Tile(3192, 3309, 0),
            new Tile(3190, 3310, 0),
            new Tile(3185, 3314, 0),
            new Tile(3184, 3314, 0)
    );
    public static Combat.Style AttackStyle() {
        //Train to 10
        if (Skills.realLevel(Constants.SKILLS_STRENGTH) <= 9) {
            return Combat.Style.AGGRESSIVE;
        }
        if (Skills.realLevel(Constants.SKILLS_ATTACK) <= 9) {
            return Combat.Style.ACCURATE;
        }
        if (Skills.realLevel(Constants.SKILLS_DEFENSE) <= 9) {
            return Combat.Style.DEFENSIVE;
        }

        //Train from 10-15
        if (Skills.realLevel(Constants.SKILLS_STRENGTH) >= 10 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 14) {
            return Combat.Style.AGGRESSIVE;
        }
        if (Skills.realLevel(Constants.SKILLS_ATTACK) >= 10 && Skills.realLevel(Constants.SKILLS_ATTACK) <= 14) {
            return Combat.Style.ACCURATE;
        }
        if (Skills.realLevel(Constants.SKILLS_DEFENSE) >= 10 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 14) {
            return Combat.Style.DEFENSIVE;
        }

        //Train from 15-30
        if (Skills.realLevel(Constants.SKILLS_STRENGTH) >= 15 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 29) {
            return Combat.Style.AGGRESSIVE;
        }
        if (Skills.realLevel(Constants.SKILLS_ATTACK) >= 15 && Skills.realLevel(Constants.SKILLS_ATTACK) <= 29) {
            return Combat.Style.ACCURATE;
        }
        if (Skills.realLevel(Constants.SKILLS_DEFENSE) >= 15 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 29) {
            return Combat.Style.DEFENSIVE;
        }

        //Train from 30-40
        if (Skills.realLevel(Constants.SKILLS_STRENGTH) >= 30 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 39) {
            return Combat.Style.AGGRESSIVE;
        }
        if (Skills.realLevel(Constants.SKILLS_ATTACK) >= 30 && Skills.realLevel(Constants.SKILLS_ATTACK) <= 39) {
            return Combat.Style.ACCURATE;
        }
        if (Skills.realLevel(Constants.SKILLS_DEFENSE) >= 30 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 39) {
            return Combat.Style.DEFENSIVE;
        }

        //Train from 40-50
        if (Skills.realLevel(Constants.SKILLS_STRENGTH) >= 40 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 49) {
            return Combat.Style.AGGRESSIVE;
        }
        if (Skills.realLevel(Constants.SKILLS_ATTACK) >= 40 && Skills.realLevel(Constants.SKILLS_ATTACK) <= 49) {
            return Combat.Style.ACCURATE;
        }
        if (Skills.realLevel(Constants.SKILLS_DEFENSE) >= 40 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 49) {
            return Combat.Style.DEFENSIVE;
        }

        //Train from 50-60
        if (Skills.realLevel(Constants.SKILLS_STRENGTH) >= 50 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 59) {
            return Combat.Style.AGGRESSIVE;
        }
        if (Skills.realLevel(Constants.SKILLS_ATTACK) >= 50 && Skills.realLevel(Constants.SKILLS_ATTACK) <= 59) {
            return Combat.Style.ACCURATE;
        }
        if (Skills.realLevel(Constants.SKILLS_DEFENSE) >= 50 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 59) {
            return Combat.Style.DEFENSIVE;
        }

        //Train from 60-70
        if (Skills.realLevel(Constants.SKILLS_STRENGTH) >= 60 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 69) {
            return Combat.Style.AGGRESSIVE;
        }
        if (Skills.realLevel(Constants.SKILLS_ATTACK) >= 60 && Skills.realLevel(Constants.SKILLS_ATTACK) <= 69) {
            return Combat.Style.ACCURATE;
        }
        if (Skills.realLevel(Constants.SKILLS_DEFENSE) >= 60 && Skills.realLevel(Constants.SKILLS_STRENGTH) <= 69) {
            return Combat.Style.DEFENSIVE;
        }
        return Combat.Style.AGGRESSIVE;
    }
}
