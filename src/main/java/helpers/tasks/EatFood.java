package helpers.tasks;

import org.powbot.api.Random;
import org.powbot.api.rt4.*;

import helpers.PlayerHelper;
import helpers.extentions.Task;

public class EatFood extends Task {
    int eatFoodPercentage = 50;
    @Override
    public boolean activate() {
        return Players.local().healthPercent() < eatFoodPercentage;
    }
    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY)) {
            PlayerHelper.shouldEat();
            eatFoodPercentage = Random.nextInt(40, 70);
            System.out.println("Randomized health percentage to eat at to: " + eatFoodPercentage + "%");
        }
        return false;
    }
}