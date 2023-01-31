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
    Item CoinPouch = Inventory.stream().name("Coin pouch").first();
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_THIEVING) <= 5 || (Skills.realLevel(Constants.SKILLS_THIEVING) > 5 && Inventory.stream().name("Coin pouch").count() >= 1);
    }

    @Override
    public void execute() {
        if (!Game.tab(Game.Tab.INVENTORY)) {
            Condition.wait(() -> Game.tab(Game.Tab.INVENTORY), 250, 10);
        }
        if (CoinPouch.stackSize() >= triggerCount) {
            mMain.scriptStatus = "Opening pouches";
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

        if (!skillData.thievingMenArea.contains(Players.local())) {
            mMain.scriptStatus = "Going to lumbridge";
            Movement.builder(skillData.movementThieving()).setRunMin(45).setRunMax(75).move();
        }

        Npc Man = Npcs.stream().name("Man").nearest().first();
        if (Man.inViewport() && Players.local().animation() == -1) {
            mMain.scriptStatus = "Thieving men";
            Man.interact("Pickpocket", "Man");
        }

        //Open pouches if we're done thieving in lumbridge
        if (Skills.realLevel(Constants.SKILLS_THIEVING) > 5 && Inventory.stream().name("Coin pouch").count() >= 1) {
            mMain.scriptStatus = "Done with men, empty pouches";
            CoinPouch.interact("Open-all");
            Condition.wait((Callable<Boolean>) () -> Inventory.stream().name("Coin pouch").count() == 0);
        }
    }
}