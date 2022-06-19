package src.PastShadie.scripts.mMain.Assets;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;

public class skillData {

    //Mining
    public static final Tile miningCopperLocation = new Tile(3287,3366);
    public static final Tile miningIronLocation = new Tile(3286,3368);

    public static Tile movementMining(){
        if (Skill.Fishing.realLevel() <= 19) {
            return miningCopperLocation;
        }
        if (Skill.Fishing.realLevel() >= 20) {
            return miningIronLocation;
        }
        return null;
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
    public static final Area normalTreeLocation = new Area (
            new Tile(3032,3273),
            new Tile(3049, 3260)
    );
    public static final Area oakTreeLocation = new Area (
            new Tile(3099,3245),
            new Tile(3104, 3240)
    );
    public static final Area willowTreeLocation = new Area (
            new Tile(3056,3255),
            new Tile(3064, 3249)
    );

    public static Tile movementWoodcutting(){
        if (Skill.Fishing.realLevel() <= 14) {
            return normalTreeLocation.getRandomTile();
        }
        if (Skill.Fishing.realLevel() >= 15 && Skill.Woodcutting.realLevel() < 30) {
            return oakTreeLocation.getRandomTile();
        }
        if (Skill.Fishing.realLevel() >= 30) {
            return willowTreeLocation.getRandomTile();
        }
        return null;
    }

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
            new Tile(3191, 3492, 0),
            new Tile(3199, 3486, 0)
    );
}
