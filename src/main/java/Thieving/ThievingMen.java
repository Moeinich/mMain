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

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class ThievingMen extends Task {
    boolean shouldRandomize = true;
    int triggerCount = 15;
    Item CoinPouch = Inventory.stream().name("Coin pouch").first();
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_THIEVING) <= 4 || (Skills.realLevel(Constants.SKILLS_THIEVING) >= 5 && Inventory.stream().name("Coin pouch").count() >= 1);
    }

    @Override
    public void execute() {
        if (!Game.tab(Game.Tab.INVENTORY)) {
            Condition.wait(() -> Game.tab(Game.Tab.INVENTORY), 250, 10);
        }
        if (CoinPouch.stackSize() >= triggerCount) {
            ShouldOpenPouches();
        }
        //Open pouches if we're done thieving in lumbridge
        if (Skills.realLevel(Constants.SKILLS_THIEVING) >= 5 && Inventory.stream().name("Coin pouch").count() >= 1) {
            mMain.State = "Done with men, empty pouches";
            CoinPouch.interact("Open-all");
            Condition.wait( () -> Inventory.stream().name("Coin pouch").isEmpty(), 200, 50);
        }
        ShouldThieve();
    }
    private void ShouldOpenPouches() {
        if (CoinPouch.stackSize() >= triggerCount) {
            mMain.State = "Opening pouches";
            if (CoinPouch.interact("Open-all", "Coin pouch") && !Players.local().inMotion()) {
                Condition.wait( () -> Inventory.stream().name("Coin pouch").isEmpty(), 200,50);
            }
            if (shouldRandomize) {
                triggerCount = Random.nextInt(4, 20);
                shouldRandomize = false;
            }
        }
        else {
            shouldRandomize = true;
        }
    }
    private void ShouldThieve() {
        if (!SkillData.thievingMenArea.contains(Players.local())) {
            mMain.State = "Going to lumbridge";
            Movement.builder(SkillData.movementThieving()).setRunMin(45).setRunMax(75).move();
        }

        Npc Man = Npcs.stream().reachable().within(SkillData.thievingMenArea).name("Man").nearest().first();
        if (Man.inViewport() && Players.local().animation() == -1) {
            mMain.State = "Thieving men";
            if (Man.interact("Pickpocket", "Man")) {
                Condition.wait( () -> !Players.local().inMotion(), 200, 50);
            }
        }
    }
}