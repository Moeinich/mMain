package quests.Common;

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
    public static final Quest ANIMAL_MAGNETISM = new Quest("Animal Magnetism", 939, -1);
    public static final Quest BIOHAZARD = new Quest("Biohazard", 68, -1);
    public static final Quest BONE_VOYAGE = new Quest("Bone Voyage", 1630, -1);
    public static final Quest CLIENT_OF_KOUREND = new Quest("Client of Kourend", 1566, 7);
    public static final Quest COOKS_ASSISTANT = new Quest("Cook's Assistant", 29, 2);
    public static final Quest DADDYS_HOME = new Quest("Daddys Home", 393, -1);
    public static final Quest DARKNESS_OF_HALLOWVALE = new Quest("Darkness of Hawllowvale", 869, -1);
    public static final Quest DEMON_SLAYER = new Quest("Demon Slayer", 222, -1);
    public static final Quest ENTER_THE_ABYSS = new Quest("Enter the Abyss", 492, -1);
    public static final Quest THE_DIG_SITE = new Quest("The Digsite", 131, -1);
    public static final Quest DORICS_QUEST = new Quest("Dorics Quest", 31, 100);
    public static final Quest DRUIDIC_RITUAL = new Quest("Druidic Ritual", 80, 4);
    public static final Quest ERNEST_THE_CHICKEN = new Quest("Ernest the Chicken", 32, -1);
    public static final Quest FIGHT_ARENA = new Quest("Fight Arena", 17, -1, true);
    public static final Quest GERTRUDES_CAT = new Quest("Gertrudes Cat", 180, -1);
    public static final Quest FISHING_CONTEST = new Quest("Fishing Contest", 11, 5);
    public static final Quest GOBLIN_DIPLOMACY = new Quest("Goblin Diplomacy", 62, 454);
    public static final Quest HAND_IN_THE_SAND = new Quest("Hand in the Sand", 635, -1);
    public static final Quest HAZEEL_CULT = new Quest("Hazeel Cult", 223, -1);
    public static final Quest IMP_CATCHER = new Quest("Imp Catcher", 160, 2);
    public static final Quest LOST_CITY = new Quest("Lost City", 147, -1);
    public static final Quest NATURAL_HISTORY = new Quest("Natural History", 1014, -1);
    public static final Quest OBSERVATORY_QUEST = new Quest("Observatory Quest", 112, -1);
    public static final Quest NATURE_SPIRIT = new Quest("Nature Spirit", 307, -1);
    public static final Quest IN_AID_OF_THE_MYREQUE = new Quest("In aid of the Myreque", 705, -1);
    public static final Quest IN_SEARCH_OF_THE_MYREQUE = new Quest("In search of the Myreque", 387, -1);
    public static final Quest JUNGLE_POTION = new Quest("Jungle Potion", 59, 2);
    public static final Quest KINGS_RANSOM = new Quest("King's Ransom", 646, -1);
    public static final Quest LEGENDS_QUEST = new Quest("Legends' Quest", 1074, -1);
    public static final Quest MONKEY_MADNESS_I = new Quest("Monkey Madness I", 141, -1);
    public static final Quest MONKEY_MADNESS_II = new Quest("Monkey Madness II", 464, -1);
    public static final Quest MOUNTAIN_DAUGHTER = new Quest("Mountain Daughter", 154, -1);
    public static final Quest MOURNINGS_END_PART_I = new Quest("Mourning's End Part I", 319, -1);
    public static final Quest MOURNINGS_END_PART_II = new Quest("Mourning's End Part II", 320, -1);
    public static final Quest MURDER_MYSTERY = new Quest("Murder Mystery", 110, 10);
    public static final Quest MY_ARMS_BIG_ADVENTURE = new Quest("My Arm's Big Adventure", 618, -1);
    public static final Quest OLAFS_QUEST = new Quest("Olaf's Quest", 153, -1);
    public static final Quest ONE_SMALL_FAVOUR = new Quest("One Small Favour", 487, -1);
    public static final Quest PIRATES_TREASURE = new Quest("Pirate's Treasure", 42, 4);
    public static final Quest PLAGUE_CITY = new Quest("Plague City", 165, 29);
    public static final Quest PRIEST_IN_PERIL = new Quest("Priest in Peril", 302, 0, true);
    public static final Quest PRINCE_ALI_RESCUE = new Quest("Prince Ali Rescue", 273, -1);
    public static final Quest REGICIDE = new Quest("Regicide", 328, -1);
    public static final Quest ROMEO_AND_JULIET = new Quest("Romeo & Juliet", 144, 100);
    public static final Quest ROVING_ELVES = new Quest("Roving Elves", 364, -1);
    public static final Quest ROYAL_TROUBLE = new Quest("Royal Trouble", 665, -1);
    public static final Quest RUNE_MYSTERIES = new Quest("Rune Mysteries", 63, 6);
    public static final Quest SCORPION_CATCHER = new Quest("Scorpion Catcher", 156, -1);
    public static final Quest SEA_SLUG = new Quest("Sea Slug", 159, -1);
    public static final Quest SHADES_OF_MORTTON = new Quest("Shades of Mort'ton", 145, -1);
    public static final Quest SHADOW_OF_THE_STORM = new Quest("Shadow of the Storm", 304, -1);
    public static final Quest SHEEP_SHEARER = new Quest("Sheep Shearer", 179, 21);
    public static final Quest SHIELD_OF_ARRAV = new Quest("Shield of Arrav", 70, -1);
    public static final Quest TEMPLE_OF_IKOV = new Quest("Temple of Ikov", 97, -1);
    public static final Quest THE_GRAND_TREE = new Quest("The Grand Tree", 230, -1);
    public static final Quest THE_TOURIST_TRAP = new Quest("The Tourist Trap", 239, -1);
    public static final Quest TOWER_OF_LIFE = new Quest("Tower of Life", 787, -1);
    public static final Quest TREE_GNOME_STRONGHOLD = new Quest("Tree Gnome Stronghold", 150, -1);
    public static final Quest TRIBAL_TOTEM = new Quest("Tribal Totem", 62, 42);
    public static final Quest TROLL_ROMANCE = new Quest("Troll Romance", 378, -1);
    public static final Quest TROLL_STRONGHOLD = new Quest("Troll Stronghold", 31, -1);
    public static final Quest UNDERGROUND_PASS = new Quest("Underground Pass", 161, -1);
    public static final Quest VAMPIRE_SLAYER = new Quest("Vampire Slayer", 178, -1);
    public static final Quest WANTED = new Quest("Wanted!", 153, -1);
    public static final Quest WATCHTOWER = new Quest("Watchtower", 147, 107);
    public static final Quest WATERFALL_QUEST = new Quest("Waterfall Quest", 65, -1);
    public static final Quest WHAT_LIES_BELOW = new Quest("What Lies Below", 774, -1);
    public static final Quest WITCHS_HOUSE = new Quest("Witch's House", 226, -1, true);
    public static final Quest WITCHS_POTION = new Quest("Witch's Potion", 67, 3, true);
    public static final Quest X_MARKS_THE_SPOT = new Quest("X Marks The Spot", 2111, 49807368);
}
