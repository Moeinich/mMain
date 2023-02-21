package Thieving;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;

public class ThievingData {
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
}
