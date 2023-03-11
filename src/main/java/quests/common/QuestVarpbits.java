package quests.common;

public class QuestVarpbits {
    public static class Quest {
        private final String questName;
        private int questVarbit;
        private int finishedValue;
        private boolean hasCombat;

        public Quest(String questName, int questVarbit, int finishedValue) {
            this(questName, questVarbit, finishedValue, false);
        }

        public Quest(String questName, int questVarbit, int finishedValue, boolean hasCombat) {
            this.questName = questName;
            this.questVarbit = questVarbit;
            this.finishedValue = finishedValue;
            this.hasCombat = hasCombat;
        }

        // Getter methods for private fields
        public String getQuestName() {
            return questName;
        }

        public int getQuestVarbit() {
            return questVarbit;
        }

        public int getFinishedValue() {
            return finishedValue;
        }

        public boolean getHasCombat() {
            return hasCombat;
        }
    }
    public static final Quest QUEST_ANIMAL_MAGNETISM = new Quest("Animal Magnetism", 3185, 240);
    public static final Quest BIOHAZARD = new Quest("Biohazard", 68, 16);
    public static final Quest BONE_VOYAGE = new Quest("Bone Voyage", 5795, 50);
    public static final Quest CLIENT_OF_KOUREND = new Quest("Client of Kourend", 5619, 9);
    public static final Quest COOKS_ASSISTANT = new Quest("Cook's Assistant", 29, 2);
    public static final Quest DADDYS_HOME = new Quest("Daddy's Home", 10570, 14);
    public static final Quest QUEST_DARKNESS_OF_HALLOWVALE = new Quest("Darkness of Hallowvale", 2573, 320);
    public static final Quest DEMON_SLAYER = new Quest("Demon Slayer", 2561, 3);
    public static final Quest ENTER_THE_ABYSS = new Quest("Enter the Abyss", 492, 4);
    public static final Quest THE_DIG_SITE = new Quest("The Digsite", 131, 9);
    public static final Quest DORICS_QUEST = new Quest("Dorics Quest", 31, 100);
    public static final Quest DRUIDIC_RITUAL = new Quest("Druidic Ritual", 80, 4);
    public static final Quest ERNEST_THE_CHICKEN = new Quest("Ernest the Chicken", 32, 3);
    public static final Quest FIGHT_ARENA = new Quest("Fight Arena", 17, 15, true);
    public static final Quest GERTRUDES_CAT = new Quest("Gertrudes Cat", 180, 6);
    public static final Quest FISHING_CONTEST = new Quest("Fishing Contest", 11, 5);
    public static final Quest GOBLIN_DIPLOMACY = new Quest("Goblin Diplomacy", 2378, 6);
    public static final Quest HAND_IN_THE_SAND = new Quest("Hand in the Sand", 635, -1);
    public static final Quest HAZEEL_CULT = new Quest("Hazeel Cult", 223, 9);
    public static final Quest IMP_CATCHER = new Quest("Imp Catcher", 160, 2);
    public static final Quest LOST_CITY = new Quest("Lost City", 147, 6);
    public static final Quest NATURAL_HISTORY = new Quest("Natural History", 1014, -1);
    public static final Quest OBSERVATORY_QUEST = new Quest("Observatory Quest", 112, 7);
    public static final Quest NATURE_SPIRIT = new Quest("Nature Spirit", 307, 10);
    public static final Quest IN_AID_OF_THE_MYREQUE = new Quest("In Aid of the Myreque", 1990, 430);
    public static final Quest IN_SEARCH_OF_THE_MYREQUE = new Quest("In search of the Myreque", 387, 110);
    public static final Quest JUNGLE_POTION = new Quest("Jungle Potion", 175, 12);
    public static final Quest KINGS_RANSOM = new Quest("King's Ransom", 3888, 90);
    public static final Quest LEGENDS_QUEST = new Quest("Legends' Quest", 139, 75);
    public static final Quest MONKEY_MADNESS_I = new Quest("Monkey Madness I", 365, 10);
    public static final Quest MONKEY_MADNESS_II = new Quest("Monkey Madness II", 5027, 200);
    public static final Quest MOUNTAIN_DAUGHTER = new Quest("Mountain Daughter", 260, 70);
    public static final Quest MOURNINGS_END_PART_I = new Quest("Mourning's End Part I", 517, 9);
    public static final Quest MOURNINGS_END_PART_II = new Quest("Mourning's End Part II", 1103, 60);
    public static final Quest MURDER_MYSTERY = new Quest("Murder Mystery", 192, 2);
    public static final Quest MY_ARMS_BIG_ADVENTURE = new Quest("My Arm's Big Adventure", 2790, 320);
    public static final Quest OLAFS_QUEST = new Quest("Olaf's Quest", 3534, 80);
    public static final Quest ONE_SMALL_FAVOUR = new Quest("One Small Favour", 416, 285);
    public static final Quest PIRATES_TREASURE = new Quest("Pirate's Treasure", 71, 4);
    public static final Quest PLAGUE_CITY = new Quest("Plague City", 165, 30);
    public static final Quest PRIEST_IN_PERIL = new Quest("Priest in Peril", 302, 61, true);
    public static final Quest PRINCE_ALI_RESCUE = new Quest("Prince Ali Rescue", 273, 110);
    public static final Quest REGICIDE = new Quest("Regicide", 328, 15);
    public static final Quest ROMEO_AND_JULIET = new Quest("Romeo & Juliet", 144, 100);
    public static final Quest ROVING_ELVES = new Quest("Roving Elves", 402, 6);
    public static final Quest ROYAL_TROUBLE = new Quest("Royal Trouble", 2140, 30);
    public static final Quest RUNE_MYSTERIES = new Quest("Rune Mysteries", 63, 6);
    public static final Quest SCORPION_CATCHER = new Quest("Scorpion Catcher", 76, 6);
    public static final Quest SEA_SLUG = new Quest("Sea Slug", 159, 12);
    public static final Quest SHADES_OF_MORTTON = new Quest("Shades of Mort'ton", 339, 85);
    public static final Quest SHADOW_OF_THE_STORM = new Quest("Shadow of the Storm", 1372, 125);
    public static final Quest SHEEP_HERDER = new Quest("Sheep Herder", 60, 3);
    public static final Quest SHIELD_OF_ARRAV = new Quest("Shield of Arrav", 145, 3);
    public static final Quest TEMPLE_OF_IKOV = new Quest("Temple of Ikov", 26, 80);
    public static final Quest THE_GRAND_TREE = new Quest("The Grand Tree", 150, 160);
    public static final Quest THE_TOURIST_TRAP = new Quest("The Tourist Trap", 197, 30);
    public static final Quest TOWER_OF_LIFE = new Quest("Tower of Life", 3337, 18);
    public static final Quest TREE_GNOME_STRONGHOLD = new Quest("Tree Gnome Stronghold", 150, -1);
    public static final Quest TRIBAL_TOTEM = new Quest("Tribal Totem", 200, 5);
    public static final Quest TROLL_ROMANCE = new Quest("Troll Romance", 385, 45);
    public static final Quest TROLL_STRONGHOLD = new Quest("Troll Stronghold", 317, 50);
    public static final Quest UNDERGROUND_PASS = new Quest("Underground Pass", 161, 11);
    public static final Quest VAMPIRE_SLAYER = new Quest("Vampire Slayer", 178, 3);
    public static final Quest WANTED = new Quest("Wanted!", 1051, 11);
    public static final Quest WATCHTOWER = new Quest("Watchtower", 212, 14);
    public static final Quest WATERFALL_QUEST = new Quest("Waterfall Quest", 65, 10);
    public static final Quest WHAT_LIES_BELOW = new Quest("What Lies Below", 3523, 150);
    public static final Quest WITCHS_HOUSE = new Quest("Witch's House", 226, 7, true);
    public static final Quest WITCHS_POTION = new Quest("Witch's Potion", 67, 3, true);
    public static final Quest X_MARKS_THE_SPOT = new Quest("X Marks the Spot", 2111, 49807368);
    public static final Quest TEMPLE_OF_THE_EYE = new Quest("Temple of the Eye", 3405, 130);
    public static final Quest BLACK_KNIGHTS_FORTRESS = new Quest("Black Knights' Fortress", 130, 4);
    public static final Quest DRAGON_SLAYER_I = new Quest("Dragon Slayer I", 176, 10);
    public static final Quest THE_KNIGHTS_SWORD = new Quest("The Knight's Sword", 122, 7);
    public static final Quest THE_RESTLESS_GHOST = new Quest("The Restless Ghost", 107, 5);
    public static final Quest BIG_CHOMPY_BIRD_HUNTING = new Quest("Big Chompy Bird Hunting", 293, 65);
    public static final Quest CABIN_FEVER = new Quest("Cabin Fever", 655, 140);
    public static final Quest CLOCK_TOWER = new Quest("Clock Tower", 10, 8);
    public static final Quest CREATURE_OF_FENKENSTRAIN = new Quest("Creature of Fenkenstrain", 399, 9);
    public static final Quest DEATH_PLATEAU = new Quest("Death Plateau", 314, 80);
    public static final Quest DWARF_CANNON = new Quest("Dwarf Cannon", 0, 11);
    public static final Quest EADGARS_RUSE = new Quest("Eadgar's Ruse", 335, 110);
    public static final Quest ELEMENTAL_WORKSHOP_I = new Quest("Elemental Workshop I", 299, 1091326);
    public static final Quest FAMILY_CREST = new Quest("Family Crest", 148, 11);
    public static final Quest FREMENNIK_TRIALS = new Quest("The Fremennik Trials", 347, 10);
    public static final Quest HAUNTED_MINE = new Quest("Haunted Mine", 382, 11);
    public static final Quest HEROES_QUEST = new Quest("Heroes' Quest", 188, 15);
    public static final Quest HOLY_GRAIL = new Quest("Holy Grail", 5, 10);
    public static final Quest MERLINS_CRYSTAL = new Quest("Merlin's Crystal", 14, 7);
    public static final Quest MONKS_FRIEND = new Quest("Monk's Friend", 30, 80);
    public static final Quest RAG_AND_BONE_MAN_I = new Quest("Rag and Bone Man I", 714, 6);
    public static final Quest RUM_DEAL = new Quest("Rum Deal", 600, 19);
    public static final Quest SHILO_VILLAGE = new Quest("Shilo Village", 116, 15);
    public static final Quest TAI_BWO_WANNAI_TRIO = new Quest("Tai Bwo Wannai Trio", 320, 6);
    public static final Quest THRONE_OF_MISCELLANIA = new Quest("Throne of Miscellania", 359, 100);
    public static final Quest TREE_GNOME_VILLAGE = new Quest("Tree Gnome Village", 111, 9);
    public static final Quest THE_GREAT_BRAIN_ROBBERY = new Quest("The Great Brain Robbery", 980, 130);
    public static final Quest RAG_AND_BONE_MAN_II = new Quest("Rag and Bone Man II", 714, 6);
    public static final Quest ALFRED_GRIMHANDS_BARCRAWL = new Quest("Alfred Grimhands Barcrawl", 77, 0);
    public static final Quest THE_MAGE_ARENA = new Quest("Mage Arena", 267, 8);
    public static final Quest BELOW_ICE_MOUNTAIN = new Quest("Below Ice Mountain", 12063, 120);
    public static final Quest MISTHALIN_MYSTERY = new Quest("Misthalin Mystery", 3468, 135);
    public static final Quest THE_CORSAIR_CURSE = new Quest("The Corsair Curse", 6071, 60);
    public static final Quest BENEATH_CURSED_SANDS = new Quest("Beneath Cursed Sands", 13841, 108);
    public static final Quest BETWEEN_A_ROCK = new Quest("Between a Rock...", 299, 110);
    public static final Quest CONTACT = new Quest("Contact!", 3274, 130);
    public static final Quest ZOGRE_FLESH_EATERS = new Quest("Zogre Flesh Eaters", 487, 14);
    public static final Quest DEATH_TO_THE_DORGESHUUN = new Quest("Death to the Dorgeshuun", 2258, 13);
    public static final Quest DESERT_TREASURE = new Quest("Desert Treasure", 358, 15);
    public static final Quest DEVIOUS_MINDS = new Quest("Devious Minds", 1465, 80);
    public static final Quest EAGLES_PEAK = new Quest("Eagles' Peak", 2780, 40);
    public static final Quest ELEMENTAL_WORKSHOP_II = new Quest("Elemental Workshop II", 2639, 11);
    public static final Quest ENAKHRAS_LAMENT = new Quest("Enakhra's Lament", 1560, 70);
    public static final Quest ENLIGHTENED_JOURNEY = new Quest("Enlightened Journey", 2866, 200);
    public static final Quest THE_EYES_OF_GLOUPHRIE = new Quest("The Eyes of Glouphrie", 2497, 60);
    public static final Quest FAIRYTALE_I_GROWING_PAINS = new Quest("Fairytale I - Growing Pains", 1803, 90);
    public static final Quest FAIRYTALE_II_CURE_A_QUEEN = new Quest("Fairytale II - Cure a Queen", 2326, 100);
    public static final Quest THE_FEUD = new Quest("The Feud", 334, 28);
    public static final Quest FORGETTABLE_TALE = new Quest("Forgettable Tale...", 822, 140);
    public static final Quest GARDEN_OF_TRANQUILLITY = new Quest("Garden of Tranquillity", 961, 60);
    public static final Quest GHOSTS_AHOY = new Quest("Ghosts Ahoy", 217, 8);
    public static final Quest THE_GIANT_DWARF = new Quest("The Giant Dwarf", 571, 50);
    public static final Quest THE_GOLEM = new Quest("The Golem", 346, 10);
    public static final Quest THE_HAND_IN_THE_SAND = new Quest("The Hand in the Sand", 1527, 160);
    public static final Quest HORROR_FROM_THE_DEEP = new Quest("Horror from the Deep", 34, 10);
    public static final Quest ICTHLARINS_LITTLE_HELPER = new Quest("Icthlarin's Little Helper", 418, 26);
    public static final Quest LAND_OF_THE_GOBLINS = new Quest("Land of the Goblins", 13599, 56);
    public static final Quest THE_LOST_TRIBE = new Quest("The Lost Tribe", 532, 12);
    public static final Quest LUNAR_DIPLOMACY = new Quest("Lunar Diplomacy", 2448, 190);
    public static final Quest MAKING_HISTORY = new Quest("Making History", 1383, 4);
    public static final Quest RATCATCHERS = new Quest("Ratcatchers", 1404, 127);
    public static final Quest RECIPE_FOR_DISASTER = new Quest("Recipe for Disaster", 1850, 5);
    public static final Quest RECIPE_FOR_DISASTER_DWARF = new Quest("Recipe for Disaster: Dwarf", 1892, 60);
    public static final Quest RECIPE_FOR_DISASTER_WARTFACE_AND_BENTNOZE = new Quest("Recipe for Disaster: Wartface and Bentnoze", 1867, 40);
    public static final Quest RECIPE_FOR_DISASTER_PIRATE_PETE = new Quest("Recipe for Disaster: Pirate Pete", 1895, 110);
    public static final Quest RECIPE_FOR_DISASTER_LUMBRIDGE_GUIDE = new Quest("Recipe for Disaster: Lumbridge Guide", 1896, 5);
    public static final Quest RECIPE_FOR_DISASTER_EVIL_DAVE = new Quest("Recipe for Disaster: Evil Dave", 1878, 5);
    public static final Quest RECIPE_FOR_DISASTER_SIR_AMIK_VARZE = new Quest("Recipe for Disaster: Sir Amik Varze", 1910, 20);
    public static final Quest RECIPE_FOR_DISASTER_SKRACH_UGLOGWEE = new Quest("Recipe for Disaster: Skrach Uglogwee", 1904, 170);
    public static final Quest RECIPE_FOR_DISASTER_MONKEY_AMBASSADOR = new Quest("Recipe for Disaster: Monkey Ambassador", 1914, 50);
    public static final Quest RECRUITMENT_DRIVE = new Quest("Recruitment Drive", 657, 2);
    public static final Quest THE_SLUG_MENACE = new Quest("The Slug Menace", 2610, 14);
    public static final Quest A_SOULS_BANE = new Quest("A Soul's Bane", 2011, 13);
    public static final Quest SPIRITS_OF_THE_ELID = new Quest("Spirits of the Elid", 1444, 60);
    public static final Quest SWAN_SONG = new Quest("Swan Song", 2098, 210);
    public static final Quest A_TAIL_OF_TWO_CATS = new Quest("A Tail of Two Cats", 1028, 70);
    public static final Quest TEARS_OF_GUTHIX = new Quest("Tears of Guthix", 451, 2);
    public static final Quest COLD_WAR = new Quest("Cold War", 3293, 135);
    public static final Quest THE_FREMENNIK_ISLES = new Quest("The Fremennik Isles", 3311, 340);
    public static final Quest ANOTHER_SLICE_OF_HAM = new Quest("Another Slice of H.A.M.", 3550, 11);
    public static final Quest DREAM_MENTOR = new Quest("Dream Mentor", 3618, 28);
    public static final Quest GRIM_TALES = new Quest("Grim Tales", 2783, 60);
    public static final Quest THE_QUEEN_OF_THIEVES = new Quest("The Queen of Thieves", 6037, 13);
    public static final Quest THE_DEPTHS_OF_DESPAIR = new Quest("The Depths of Despair", 6027, 11);
    public static final Quest DRAGON_SLAYER_II = new Quest("Dragon Slayer II", 6104, 215);
    public static final Quest TALE_OF_THE_RIGHTEOUS = new Quest("Tale of the Righteous", 6358, 17);
    public static final Quest A_TASTE_OF_HOPE = new Quest("A Taste of Hope", 6396, 165);
    public static final Quest MAKING_FRIENDS_WITH_MY_ARM = new Quest("Making Friends with My Arm", 6528, 200);
    public static final Quest THE_ASCENT_OF_ARCEUUS = new Quest("The Ascent of Arceuus", 7856, 14);
    public static final Quest THE_FORSAKEN_TOWER = new Quest("The Forsaken Tower", 7796, 11);
    public static final Quest SONG_OF_THE_ELVES = new Quest("Song of the Elves", 9016, 200);
    public static final Quest THE_FREMENNIK_EXILES = new Quest("The Fremennik Exiles", 9459, 130);
    public static final Quest SINS_OF_THE_FATHER = new Quest("Sins of the Father", 7255, 138);
    public static final Quest A_PORCINE_OF_INTEREST = new Quest("A Porcine of Interest", 10582, 40);
    public static final Quest GETTING_AHEAD = new Quest("Getting Ahead", 693, 34);
    public static final Quest A_KINGDOM_DIVIDED = new Quest("A Kingdom Divided", 12296, 150);
    public static final Quest A_NIGHT_AT_THE_THEATRE = new Quest("A Night at the Theatre", 12276, 86);
    public static final Quest ARCHITECTURAL_ALLIANCE = new Quest("Architectural Alliance", 4982, 5);
    public static final Quest BEAR_YOUR_SOUL = new Quest("Bear Your Soul", 5078, 3);
    public static final Quest CURSE_OF_THE_EMPTY_LORD = new Quest("Curse of the Empty Lord", 821, 1);
    public static final Quest ENCHANTED_KEY = new Quest("Enchanted Key", 1391, 2047);
    public static final Quest THE_GENERALS_SHADOW = new Quest("The General's Shadow", 3330, 30);
    public static final Quest SKIPPY_AND_THE_MOGRES = new Quest("Skippy and the Mogres", 1344, 3);
    public static final Quest LAIR_OF_TARN_RAZORLOR = new Quest("Lair of Tarn Razorlor", 3290, 3);
    public static final Quest FAMILY_PEST = new Quest("Family Pest", 5347, 3);
    public static final Quest THE_MAGE_ARENA_II = new Quest("The Mage Arena II", 6067, 4);
    public static final Quest IN_SEARCH_OF_KNOWLEDGE = new Quest("In Search of Knowledge", 8403, 3);
    public static final Quest HOPESPEARS_WILL = new Quest("Hopespear's Will", 13619, 2);
}
