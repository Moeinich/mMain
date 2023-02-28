package firemaking;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;

import helpers.ItemList;

public class FiremakingData {
    //Firemaking
    public static final Tile firemakingLane1 = new Tile(3032,3360);
    public static final Tile firemakingLane2 = new Tile(3032,3361);
    public static final Area firemakingStartArea = new Area (
            new Tile(3032, 3360, 0), new Tile(3032, 3361, 0)
    );
    public static final Area doFiremakingArea = new Area (
            new Tile(3033, 3362, 0), new Tile(3004, 3359, 0)
    );
    public static int[] logs = {ItemList.LOGS_1511, ItemList.OAK_LOGS_1521, ItemList.WILLOW_LOGS_1519};

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
}
