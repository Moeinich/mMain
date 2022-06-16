package src.PastShadie.scripts.mMain.Assets;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;

import java.util.ArrayList;

public class skillData {

    //Mining stuff
    public static final Tile miningCopperLocation = new Tile(3287,3366);
    public static final Tile miningIronLocation = new Tile(3286,3368);

    //Combat stuff
    public static final Area Seagull_area = new Area(
            new Tile(3151, 2841),
            new Tile(3157,2850)
    );
}
