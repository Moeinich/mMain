package Quests.NaturalHistory;

import org.powbot.api.Tile;

public class NaturalHistoryConstants {
    public static final Tile TILE_ORLANDO_SMITH = new Tile(1757, 4956, 0);
    public static final String NAME_ORLANDO_SMITH = "Orlando Smith";
    public static final String[] CONVERSATION_ORLANDO_SMITH = {"Sure thing."};
    public static final String[] CONVERSATION_FINISH_ORLANDO_SMITH = {"Sure thing."};

    public static final String NAME_PLAQUE = "Plaque";
    public static final String ACTION_PLAQUE = "Study";

    public static final int WIDGET_ANSWER = 533;
    public static final int COMPONENT_QUESTION = 28;
    public static final int COMPONENT_CLOSE = 32;

    public static final int LIZARD_SHIFT_COUNT = 8;
    public static final Tile TILE_LIZARD = new Tile(1743, 4977, 0);
    public static final Question[] ANSWER_LIZARD = {
            new Question("How does a lizard regulate body heat?", "Sunlight"),
            new Question("Who discovered how to kill lizards?", "The Slayer Masters"),
            new Question("How many eyes does a lizard have?", "Three"),
            new Question("What order do lizards belong to?", "Squamata"),
            new Question("What happens when a lizard becomes cold?", "It becomes sleepy"),
            new Question("Lizard skin is made of the same substance as?", "Hair")
    };

    public static final int TORTOISE_SHIFT_COUNT = 18;
    public static final Tile TILE_TORTOISE = new Tile(1753, 4977, 0);
    public static final Question[] ANSWER_TORTOISE = {
            new Question("What is the name of the oldest tortoise ever recorded?", "Mibbiwocket"),
            new Question("What is a tortoise's favourite food?", "Vegetables"),
            new Question("Name the explorer who discovered the world's oldest tortoise.", "Admiral Bake"),
            new Question("How does the tortoise protect itself?", "Hard shell"),
            new Question("If a tortoise had twenty rings on its shell, how old would it be?", "Twenty years"),
            new Question("Which race breeds tortoises for battle?", "Gnomes")
    };

    public static final int DRAGON_SHIFT_COUNT = 2;
    public static final Tile TILE_DRAGON = new Tile(1768, 4977, 0);
    public final Question[] ANSWER_DRAGON = {
            new Question("What is considered a delicacy by dragons?", "Runite"),
            new Question("What is the best defence against a dragon's attack?", "Anti dragon-breath shield"),
            new Question("How long do dragons live?", "Unknown"),
            new Question("Which of these is not a type of dragon?", "Elemental"),
            new Question("What is the favoured territory of a dragon?", "Old battle sites"),
            new Question("Approximately how many feet tall do dragons stand?", "Twelve")
    };

    public static final int WYVERN_SHIFT_COUNT = 20;
    public static final Tile TILE_WYVERN = new Tile(1778, 4977);
    public static final Question[] ANSWER_WYVERN = {
            new Question("How did the wyverns die out?", "Climate change"),
            new Question("How many legs does a wyvern have?", "Two"),
            new Question("Where have wyvern bones been found?", "Asgarnia"),
            new Question("Which genus does the wyvern theoretically belong to?", "Reptiles"),
            new Question("What are the wyverns' closest relations?", "Dragons"),
            new Question("What is the ambient temperature of wyvern bones?", "Below room temperature")
    };

    public static final int SNAKE_SHIFT_COUNT = 12;
    public static final Tile TILE_SNAKE = new Tile(1783, 4962, 0);
    public static final Question[] ANSWER_SNAKE = {
            new Question("What is snake venom adapted from?", "Stomach acid"),
            new Question("Aside from their noses, what do snakes use to smell?", "Tongue"),
            new Question("If a snake sticks its tongue out at you, what is it doing?", "Seeing how you smell"),
            new Question("If some snakes use venom to kill their prey, what do other snakes use?", "Constriction"),
            new Question("Lizards and snakes belong to the same order - what is it?", "Squamata"),
            new Question("Which habitat do snakes prefer?", "Anywhere")
    };

    public static final int SEA_SLUGS_SHIFT_COUNT = 22;
    public static final Tile TILE_SEA_SLUGS = new Tile(1781, 4959, 0);
    public static final Question[] ANSWER_SEA_SLUGS = {
            new Question("We assume that sea slugs have a stinging organ on their soft skin - what is it called?", "Nematocysts"),
            new Question("Why has the museum never examined a live sea slug?", "The researchers keep vanishing"),
            new Question("What do we think the sea slug feeds upon?", "Seaweed"),
            new Question("What are the two fangs presumed to be used for?", "Defense or display"),
            new Question("Off of which coastline would you find sea slugs?", "Ardougne"),
            new Question("In what way are sea slugs similar to nudibranchs?", "They are both gastropods")
    };

    final int MONKEY_SHIFT_COUNT = 10;
    final Tile TILE_MONKEY = new Tile(1774, 4958, 0);
    final Question[] ANSWER_MONKEY = new Question[] {
            new Question("Which type of primates do monkeys belong to?", "Simian"),
            new Question("Which have the lighter colour: Karamjan or Harmless monkeys?", "Harmless"),
            new Question("Monkeys love bananas. What else do they like to eat?", "Bitternuts"),
            new Question("There are two known families of monkeys. One is Karamjan, the other is...?", "Harmless"),
            new Question("What colour mohawk do Karamjan monkeys have?", "Red"),
            new Question("What have Karamjan monkeys taken a deep dislike to?", "Seaweed"),
    };

    final int KALPHITE_QUEEN_SHIFT_COUNT = 26;
    final Tile TILE_KALPHITE_QUEEN = new Tile(1761, 4938, 0);
    final Question[] ANSWER_KALPHITE_QUEEN = new Question[] {
            new Question("Kalphites are ruled by a...?", "Pasha"),
            new Question("What is the lowest caste in kalphite society?", "Worker"),
            new Question("What are the armoured plates on a kalphite called?", "Lamellae"),
            new Question("Are kalphites carnivores, herbivores or omnivores?", "Carnivores"),
            new Question("What are kalphites assumed to have evolved from?", "Scarab beetles"),
            new Question("Name the prominent figure in kalphite mythology?", "Scabaras"),
    };

    final int TERRORBIRD_SHIFT_COUNT = 24;
    final Tile TILE_TERRORBIRD = new Tile(1756, 4940, 0);
    final Question[] ANSWER_TERRORBIRD = new Question[] {
            new Question("What is a terrorbird's preferred food?", "Anything"),
            new Question("Who use terrorbirds as mounts?", "Gnomes"),
            new Question("Where do terrorbirds get most of their water?", "Eating plants"),
            new Question("How many claws do terrorbirds have?", "Four"),
            new Question("What do terrorbirds eat to aid digestion?", "Stones"),
            new Question("How many teeth do terrorbirds have?", "0"),
    };

    final int PENGUIN_SHIFT_COUNT = 4;
    final Tile TILE_PENGUIN = new Tile(1742, 4958, 0);
    final Question[] ANSWER_PENGUIN = new Question[] {
            new Question("Which sense do penguins rely on when hunting?", "Sight"),
            new Question("Which skill seems unusual for the penguins to possess?", "Planning"),
            new Question("How do penguins keep warm?", "A layer of fat"),
            new Question("What is the preferred climate for penguins?", "Cold"),
            new Question("Describe the behaviour of penguins?", "Social"),
            new Question("When do penguins fast?", "During breeding"),
    };

    final int MOLE_SHIFT_COUNT = 14;
    final Tile TILE_MOLE = new Tile(1736,4958,0);
    final Question[] ANSWER_MOLE = new Question[] {
            new Question("What habitat do moles prefer?", "Subterranean"),
            new Question("Why are moles considered to be an agricultural pest?", "They dig holes"),
            new Question("Who discovered giant moles?", "Wyson the Gardener"),
            new Question("What would you call a group of young moles?", "A labour"),
            new Question("What is a mole's favourite food?", "Insects and other invertebrates"),
            new Question("Which family do moles belong to?", "The Talpidae family"),
    };

    final int CAMEL_SHIFT_COUNT = 16;
    final Tile TILE_CAMEL = new Tile(1737,4962,0);
    final Question[] ANSWER_CAMEL = new Question[] {
            new Question("What is produced by feeding chilli to a camel?", "Toxic dung"),
            new Question("If an ugthanki has one, how many does a bactrian have?", "Two"),
            new Question("Camels: herbivore, carnivore or omnivore?", "Omnivore"),
            new Question("What is the usual mood for a camel?", "Annoyed"),
            new Question("Where would you find an ugthanki?", "Al Kharid"),
            new Question("Which camel byproduct is known to be very nutritious?", "Milk"),
    };

    final int LEECH_SHIFT_COUNT = 28;
    final Tile TILE_LEECH = new Tile(1743,4962,0);
    final Question[] ANSWER_LEECH = new Question[] {
            new Question("What is the favoured habitat of leeches?", "Water"),
            new Question("What shape is the inside of a leech's mouth?", "'Y'-shaped"),
            new Question("Which of these is not eaten by leeches?", "Apples"),
            new Question("What contributed to the giant growth of Morytanian leeches?", "Environment"),
            new Question("What is special about Morytanian leeches?", "They attack by jumping"),
            new Question("How does a leech change when it feeds?", "It doubles in size"),
    };
    static class Question {
        public String question;
        public String answer;

        public Question(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }
    }
}
