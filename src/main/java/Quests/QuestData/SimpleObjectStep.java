package Quests.QuestData;

import org.powbot.api.Tile;
import org.powbot.api.rt4.GameObject;

import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;

public class SimpleObjectStep extends WalkToInteractiveStep<GameObject> {
    private final Tile noInteractableTile;
    private final String[] conversation;
    private final Callable<GameObject> interactive;
    private final Function<GameObject, Boolean> interaction;
    private final Function<GameObject, Boolean> interactionWait;
    private final String stepName;
    private final Supplier<Boolean> shouldExecute;
    private final boolean forceWeb;

    public SimpleObjectStep(Tile noInteractableTile, String[] conversation, Callable<GameObject> interactive, Function<GameObject, Boolean> interaction, Function<GameObject, Boolean> interactionWait, String stepName, QuestInformation questInformation, Supplier<Boolean> shouldExecute, boolean forceWeb) {
        super(noInteractableTile, conversation, forceWeb, questInformation);
        this.conversation = conversation != null ? conversation : new String[0];
        this.interactive = interactive;
        this.interaction = interaction;
        this.interactionWait = interactionWait;
        this.stepName = stepName;
        this.shouldExecute = shouldExecute != null ? shouldExecute : () -> true;
        this.noInteractableTile = noInteractableTile != null ? noInteractableTile : new Tile(0, 0);
        this.forceWeb = forceWeb;
    }


    public boolean shouldExecute() {
        return shouldExecute.get();
    }

    public GameObject getInteractive() {
        try {
            return interactive.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean interact(GameObject interactive) {
        return interaction.apply(interactive);
    }

    public Tile getInteractiveTile(GameObject interactive) {
        return interactive.tile();
    }

    public Tile getNoInteractTile() {
        return noInteractableTile;
    }

    public String[] getConversation() {
        return conversation;
    }

    public String stepName() {
        return stepName;
    }

    public boolean isForceWeb() {
        return forceWeb;
    }
}

