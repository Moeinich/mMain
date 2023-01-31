package Thieving;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import java.util.concurrent.Callable;

import Assets.Task;
import Assets.skillData;
import script.mMain;

public class thievingMen extends Task {
    boolean shouldRandomize = true;
    int triggerCount = 15;
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_THIEVING) <= 10;
    }

    @Override
    public void execute() {
        if (Game.tab(Game.Tab.INVENTORY)) {
            Item CoinPouch = Inventory.stream().name("Coin pouch").first();
            if (CoinPouch.stackSize() >= triggerCount) {
                CoinPouch.interact("Open-all");
                Condition.wait((Callable<Boolean>) () -> Inventory.stream().name("Coin pouch").count() == 0);
                if (shouldRandomize) {
                    triggerCount = Random.nextInt(4, 20);
                    shouldRandomize = false;
                }
            }
            else {
                shouldRandomize = true;
            }
        }

        if (!skillData.thievingMenArea.contains(Players.local())) {
            mMain.scriptStatus = "Going to lumbridge";
            Movement.builder(skillData.movementThieving()).setRunMin(45).setRunMax(75).move();
        }

        Npc Man = Npcs.stream().name("Man").nearest().first();
        if (Man.inViewport() && Players.local().animation() == -1) {
            Man.interact("Pickpocket", "Man");
        }
    }
}