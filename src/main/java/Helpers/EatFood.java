package Helpers;

import org.powbot.api.rt4.*;

public class EatFood extends Task {
    @Override
    public boolean activate() {
        return Skills.level(Constants.SKILLS_HITPOINTS) < 5;
    }
    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY)) {
            PlayerHelper playerHelper = new PlayerHelper();
            playerHelper.ShouldEat();
        }
        return false;
    }
}