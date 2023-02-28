package helpers.tasks;

import org.powbot.api.rt4.*;

import helpers.PlayerHelper;
import helpers.extentions.Task;

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