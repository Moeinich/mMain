package Helpers;

import org.powbot.api.rt4.*;

public class eatFood extends Task {
    @Override
    public boolean activate() {
        return Players.local().healthPercent() < 50;
    }
    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY)) {
            playerHelper.shouldEat();
        }
        return false;
    }
}