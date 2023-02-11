package Helpers;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

import Helpers.PlayerHelper;
import Helpers.Task;
import script.mMain;

public class EatFood extends Task {
    @Override
    public boolean activate() {
        return Skills.level(Constants.SKILLS_HITPOINTS) < 5;
    }
    @Override
    public void execute() {
        if (Game.tab(Game.Tab.INVENTORY)) {
            PlayerHelper playerHelper = new PlayerHelper();
            playerHelper.ShouldEat();
        }
    }
}