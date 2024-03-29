package agility;

import static agility.AgilityData.AgilityAreas.CANIFIS_START;
import static agility.AgilityData.AgilityAreas.DRAYNOR_START;
import static agility.AgilityData.AgilityAreas.GNOME_START;
import static agility.AgilityData.AgilityAreas.VARROCK_START;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;

public class AgilityData {
    enum obstacleInfo implements AgilityHelper.IObstacleInfo {
        //Gnome
        gnome1(23145,"Walk-across","Log balance", "Handle: gnome1"),
        gnome2(23134,"Climb-over","Obstacle net", "Handle: gnome2"),
        gnome3(23559,"Climb","Tree branch", "Handle: gnome3"),
        gnome4(23557,"Walk-on","Balancing rope", "Handle: gnome4"),
        gnome5(23560,"Climb-down","Tree branch", "Handle: gnome5"),
        gnome6(23135,"Climb-over","Obstacle net", "Handle: gnome6"),
        gnome7(23138,"Squeeze-through","Obstacle pipe", "Handle: gnome7"),

        //Draynor
        draynor1(11404,"Climb","Rough wall", "Handle: Draynor1"),
        draynor2(11405,"Cross","Tightrope", "Handle: Draynor2"),
        draynor3(11406,"Cross","Tightrope", "Handle: Draynor3"),
        draynor4(11430,"Balance","Narrow wall", "Handle: Draynor4"),
        draynor5(11630,"Jump-up","Wall", "Handle: Draynor5"),
        draynor6(11631,"Jump","Gap", "Handle: Draynor6"),
        draynor7(11632,"Climb-down","Crate", "Handle: Draynor7"),

        //Varrock
        varrock1(14412,"Climb","Rough wall", "Handle: Varrock1"),
        varrock2(14413,"Cross","Clothes line", "Handle: Varrock2"),
        varrock3(14414,"Leap","Gap", "Handle: Varrock3"),
        varrock4(14832,"Balance","Wall", "Handle: Varrock4"),
        varrock5(14833,"Leap","Gap", "Handle: Varrock5"),
        varrock6(14834,"Leap","Gap", "Handle: Varrock6"),
        varrock7(14835,"Leap","Gap", "Handle: Varrock7"),
        varrock8(14836,"Hurdle","Ledge", "Handle: Varrock8"),
        varrock9(14841,"Jump-off","Edge", "Handle: Varrock9"),

        //Canifis
        canifis1(14843, "Climb", "Tall tree", "Handle: OBS1"),
        canifis2(14844, "Jump", "Gap", "Handle: OBS2"),
        canifis3(14845, "Jump", "Gap", "Handle: OBS3"),
        canifis4(14848, "Jump", "Gap", "Handle: OBS4"),
        canifis5(14846, "Jump", "Gap", "Handle: OBS5"),
        canifis6(14894, "Vault", "Pole-vault", "OHandle: BS6"),
        canifis7(14847, "Jump", "Gap", "Handle: OBS7"),
        canifis8(14897, "Jump", "Gap", "Handle: OBS8"),

        //Seers
        seers1(14927, "Climb-up", "Wall", "Handle: OBS1"),
        seers2(14928, "Jump", "Gap", "Handle: OBS2"),
        seers3(14932, "Cross", "Tightrope", "Handle: OBS3"),
        seers4(14929, "Jump", "Gap", "Handle: OBS4"),
        seers5(14930, "Jump", "Gap", "Handle: OBS5"),
        seers6(14931, "Jump", "Edge", "OHandle: BS6");

        private final int id;
        private final String action;
        private final String name;
        private final String state;

        obstacleInfo(int id, String action, String name, String state) {
            this.id = id;
            this.action = action;
            this.name = name;
            this.state = state;
        }

        public int getId() {
            return id;
        }

        public String getAction() {
            return action;
        }
        public String getState() {
            return state;
        }

        public String getName() {
            return name;
        }

        public String getDescription()  {
            return state;
        }
    }

    //Seers
    public enum AgilityAreas {
        //Gnome
        GNOME_START(new Area (new Tile(2474, 3438, 0), new Tile(2477, 3437, 0))),
        GNOME_LOWER(new Area (new Tile(2468, 3441, 0), new Tile(2491, 3413, 0))),
        GNOME_MID(new Area (new Tile(2469, 3425, 1), new Tile(2477, 3421, 1))),
        GNOME_TOP(new Area (new Tile(2470, 3423, 2), new Tile(2490, 3416, 2))),
        GNOME_OBSTACLE_2(new Area (new Tile(2471, 3429, 0), new Tile(2477, 3427, 0))),
        GNOME_OBSTACLE_3(new Area (new Tile(2476, 3424, 1), new Tile(2471, 3422, 1))),
        GNOME_OBSTACLE_4(new Area (new Tile(2472, 3420, 2), new Tile(2477, 3419, 2))),
        GNOME_OBSTACLE_5(new Area (new Tile(2483, 3420, 2), new Tile(2488, 3418, 2))),
        GNOME_OBSTACLE_6(new Area (new Tile(2489, 3417, 0), new Tile(2481, 3425, 0))),
        GNOME_OBSTACLE_7(new Area (new Tile(2489, 3427, 0), new Tile(2482, 3431, 0))),

        //Draynor
        DRAYNOR_FLOOR(new Area (new Tile(3081, 3268, 0), new Tile(3079, 3262, 0), new Tile(3079, 3254, 0), new Tile(3079, 3248, 0), new Tile(3085, 3246, 0), new Tile(3088, 3246, 0), new Tile(3088, 3240, 0), new Tile(3096, 3240, 0), new Tile(3098, 3240, 0), new Tile(3098, 3247, 0), new Tile(3103, 3247, 0), new Tile(3106, 3249, 0), new Tile(3109, 3257, 0), new Tile(3111, 3265, 0), new Tile(3107, 3270, 0), new Tile(3107, 3278, 0), new Tile(3108, 3285, 0), new Tile(3094, 3286, 0), new Tile(3082, 3280, 0))),
        DRAYNOR_TOP(new Area (new Tile(3084, 3283, 3), new Tile(3105, 3253, 3))),
        DRAYNOR_CRATE(new Area (new Tile(3101, 3262, 1), new Tile(3103, 3260, 1))),
        DRAYNOR_START(new Area(new Tile(3103, 3281, 0), new Tile(3105, 3277, 0))),
        DRAYNOR_OBSTACLE_2(new Area (new Tile(3102, 3277, 3), new Tile(3097, 3281, 3))),
        DRAYNOR_OBSTACLE_3(new Area (new Tile(3092, 3277, 3),
                new Tile(3090, 3279, 3),
                new Tile(3085, 3274, 3),
                new Tile(3089, 3271, 3),
                new Tile(3094, 3276, 3))),
        DRAYNOR_OBSTACLE_4(new Area (new Tile(3088, 3269, 3), new Tile(3095, 3264, 3))),
        DRAYNOR_OBSTACLE_5(new Area (new Tile(3088, 3257, 3), new Tile(3089, 3262, 3))),
        DRAYNOR_OBSTACLE_6(new Area (new Tile(3087, 3256, 3), new Tile(3094, 3255, 3))),
        DRAYNOR_OBSTACLE_7(new Area (new Tile(3096, 3261, 3), new Tile(3102, 3256, 3))),

        //Varrock
        VARROCK_FLOOR(new Area (new Tile(3183, 3431, 0), new Tile(3244, 3391, 0))),
        VARROCK_FIRST(new Area (new Tile(3183, 3420, 1), new Tile(3204, 3406, 1))),
        VARROCK_MID(new Area (new Tile(3132, 3459, 2), new Tile(3331, 3326, 2))),
        VARROCK_TOP(new Area (new Tile(3105, 3466, 3), new Tile(3338, 3274, 3))),
        VARROCK_GHOST(new Area (new Tile(3113, 3411, 1), new Tile(3222, 3314, 1))),
        VARROCK_START(new Area (new Tile(3221, 3411, 0), new Tile(3224, 3412, 0), new Tile(3224, 3416, 0), new Tile(3221, 3416, 0))),
        VARROCK_OBSTACLE_2(new Area (new Tile(3214, 3420, 3), new Tile(3220, 3409, 3))),
        VARROCK_OBSTACLE_3(new Area (new Tile(3201, 3420, 3), new Tile(3208, 3412, 3))),
        VARROCK_OBSTACLE_4(new Area (new Tile(3197, 3416, 1), new Tile(3194, 3416, 1))),
        VARROCK_OBSTACLE_5(new Area (new Tile(3192, 3406, 3), new Tile(3198, 3402, 3))),
        VARROCK_OBSTACLE_6(new Area (new Tile(3190, 3382, 3), new Tile(3180, 3382, 3), new Tile(3180, 3399, 3), new Tile(3201, 3399, 3), new Tile(3201, 3404, 3), new Tile(3209, 3404, 3), new Tile(3209, 3395, 3), new Tile(3203, 3394, 3))),
        VARROCK_OBSTACLE_6_MOVETO(new Area (new Tile(3206, 3403, 3), new Tile(3208, 3395, 3))),
        VARROCK_OBSTACLE_7(new Area (new Tile(3218, 3405, 3), new Tile(3232, 3392, 3))),
        VARROCK_OBSTACLE_7_MOVETO(new Area (new Tile(3229, 3402, 3), new Tile(3232, 3400, 3))),
        VARROCK_OBSTACLE_8(new Area (new Tile(3236, 3403, 3), new Tile(3240, 3408, 3))),
        VARROCK_OBSTACLE_9(new Area (new Tile(3236, 3410, 3), new Tile(3240, 3415, 3))),

        //Canifis
        CANIFIS_FLOOR(new Area(new Tile(3469, 3511, 0), new Tile(3520, 3464, 0))),
        CANIFIS_FIRST(new Area(new Tile(3464, 3513, 1), new Tile(3529, 3457, 1))),
        CANIFIS_MID(new Area(new Tile(3464, 3513, 2), new Tile(3529, 3457, 2))),
        CANIFIS_TOP(new Area(new Tile(3464, 3513, 3), new Tile(3529, 3457, 3))),
        CANIFIS_OBSTACLE_2_MOVE_TO(new Area(new Tile(3504, 3497, 2), new Tile(3507, 3496, 2))),
        CANIFIS_OBSTACLE_7_MOVE_TO(new Area(new Tile(3499, 3477, 3), new Tile(3504, 3474, 3))),
        CANIFIS_OBSTACLE_1_BUG(new Area(new Tile(3505, 3489, 2), new Tile(3505, 3489, 2))),
        CANIFIS_START(new Area(new Tile(3504, 3488, 0), new Tile(3507, 3486, 0))),
        CANIFIS_OBSTACLE_2(new Area(new Tile(3502, 3499, 2), new Tile(3512, 3490, 2))),
        CANIFIS_OBSTACLE_3(new Area(new Tile(3495, 3508, 2), new Tile(3505, 3502, 2))),
        CANIFIS_OBSTACLE_4(new Area(new Tile(3493, 3506, 2), new Tile(3484, 3497, 2))),
        CANIFIS_OBSTACLE_5(new Area(new Tile(3479, 3499, 3), new Tile(3473, 3491, 3))),
        CANIFIS_OBSTACLE_6(new Area(new Tile(3476, 3488, 2), new Tile(3485, 3480, 2))),
        CANIFIS_OBSTACLE_7(new Area(new Tile(3486, 3480, 3), new Tile(3505, 3467, 3))),
        CANIFIS_OBSTACLE_8(new Area(new Tile(3507, 3484, 2), new Tile(3516, 3473, 2))),

        //Seers
        SEERS_FLOOR(new Area(new Tile(2684, 3501, 0), new Tile(2733, 3452, 0))),
        SEERS_FIRST(new Area(new Tile(2684, 3501, 1), new Tile(2733, 3452, 1))),
        SEERS_MID(new Area(new Tile(2684, 3501, 2), new Tile(2733, 3452, 2))),
        SEERS_TOP(new Area(new Tile(2684, 3501, 3), new Tile(2733, 3452, 3))),
        SEERS_START(new Area(new Tile(2727, 3486, 0), new Tile(2729, 3485, 0))),
        SEERS_OBSTACLE_2(new Area(new Tile(2720, 3498, 3), new Tile(2730, 3489, 3))),
        SEERS_OBSTACLE_3(new Area(new Tile(2702, 3499, 2), new Tile(2715, 3486, 2))),
        SEERS_OBSTACLE_4(new Area(new Tile(2709, 3480, 2), new Tile(2716, 3476, 2))),
        SEERS_OBSTACLE_5(new Area(new Tile(2698, 3478, 3), new Tile(2717, 3468, 3))),
        SEERS_OBSTACLE_6(new Area(new Tile(2689, 3467, 2), new Tile(2703, 3458, 2)));

        private final Area area;

        AgilityAreas(Area area) {
            this.area = area;
        }

        public Area getArea() {
            return area;
        }
    }



    public static Tile movementAgility(){
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 10 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 29) {
            return DRAYNOR_START.getArea().getRandomTile();
        }
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 30 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 39) {
            return VARROCK_START.getArea().getRandomTile();
        }
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 40 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 59) {
            return CANIFIS_START.getArea().getRandomTile();
        }
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 60 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 80) {
            return AgilityAreas.SEERS_START.getArea().getRandomTile();
        }
        return GNOME_START.getArea().getRandomTile();
    }
}
