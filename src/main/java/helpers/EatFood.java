package helpers;

import org.powbot.api.rt4.*;

public class EatFood extends Task {
    @Override
    public boolean activate() {
        return Players.local().healthPercent() < 50;
    }
    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY)) {
            PlayerHelper.shouldEat();
        }
        return false;
    }
}