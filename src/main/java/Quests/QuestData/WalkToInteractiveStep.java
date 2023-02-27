package Quests.QuestData;

import org.powbot.api.Condition;
import org.powbot.api.Point;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Chat;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Interactive;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Store;
import org.powbot.api.rt4.Widget;
import org.powbot.api.rt4.Widgets;

import java.util.Arrays;

import Helpers.PlayerHelper;

abstract class WalkToInteractiveStep<T extends Interactive> extends BaseQuestStep {

    protected Tile noInteractableTile;
    protected String[] conversation;
    protected boolean forceWeb;
    protected QuestInformation questInformation;
    protected Point pointVariance = new Point(0, 0);

    public WalkToInteractiveStep(Tile noInteractableTile, String[] conversation, boolean forceWeb, QuestInformation questInformation) {
        this.noInteractableTile = noInteractableTile;
        this.conversation = conversation;
        this.forceWeb = forceWeb;
        this.questInformation = questInformation;
    }

    protected void setPointVariance(int x, int y) {
        pointVariance = new Point(x, y);
    }

    public abstract boolean shouldExecute();

    @Override
    public void run() {
        if (Bank.opened()) {
            System.out.println("Closing bank since its open");
            Bank.close();
        } else if (Store.opened()) {
            System.out.println("Closing store since its open");
            Store.close();
        }

        if ((conversation != null && Chat.chatting() && !Chat.pendingInput()) || CommonMethods.isInCutscene()) {
            System.out.println("Completing chat");
            completeChat();
        } else {
            T interactive = getInteractive();
            if (interactive.valid() && interactive.inViewport(true)) {
                Tile interactiveTile = getInteractiveTile(interactive);
                if (interactive.inViewport() && interactiveTile.reachable()) {
                    interact(interactive);
                } else {
                    System.out.println("Walking to interactive tile " + interactiveTile);
                    walkToTile(interactiveTile);
                }
            } else {
                System.out.println("No interactive detected.");
                walkToTile(noInteractableTile);
            }
        }
    }

    protected abstract T getInteractive();
    protected abstract Tile getInteractiveTile(T interactive);

    protected Boolean interact(T interactive) {
        if (interactive.click()) {
            Condition.wait(interactive::inViewport, 100, 10);
        }
        return null;
    }

    protected void walkToTile(Tile tile) {
        PlayerHelper.walkToTile(tile);
        Condition.wait(() -> !Players.local().inMotion() || Players.local().tile().distanceTo(tile) < 5, 100, 10);
    }

    private void completeChat() {
        Widget optionBar = Widgets.widget(162);
        if (optionBar.valid()) {
            Component[] options = optionBar.components();
            if (Arrays.stream(options)
                    .noneMatch(widget -> {
                        System.out.println("Text " + widget.text());
                        return conversation != null && Arrays.asList(conversation).contains(widget.text());
                    })) {
                if (Players.local().tile().matrix().interact("Walk here")) {
                    Condition.wait(() -> !Chat.chatting(), 150, 20);
                }
            } else {
                System.out.println("Completing conversation/cutscene, options " + Arrays.toString(conversation));
                Chat.completeChat(conversation);
                if (CommonMethods.isInCutscene()) {
                    Condition.wait(() -> Chat.chatting() || !CommonMethods.isInCutscene(), 150, 25);
                }
            }
        }
    }
}
