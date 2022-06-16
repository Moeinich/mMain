package src.PastShadie.scripts.mMain.Assets;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
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

    //Fishing stuff
    public static Npc AlKharidFishingSpot = Npcs.stream().name("Fishing spot").nearest().first();
    public static Npc BarbarianVillageFishingSpot = Npcs.stream().name("Rod Fishing spot").nearest().first();


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
}
