package fishing;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.walking.model.Skill;

public class FishingData {
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
        if (Skill.Fishing.realLevel() >= 20) {
            return BarbarianVillageFishingArea.getRandomTile();
        }
        return AlKharidFishingSpot1.getRandomTile();
    }
}
