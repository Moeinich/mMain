package Thieving;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.PlayerHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class ThievingMen extends Task {
    boolean shouldRandomize = true;
    int triggerCount = 15;
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_THIEVING) <= 4 || (Inventory.stream().name("Coin pouch").isNotEmpty() && Skills.realLevel(Constants.SKILLS_THIEVING) >= 5);
    }

    @Override
    public boolean execute() {
        if (!Game.tab(Game.Tab.INVENTORY)) {
            Condition.wait(() -> Game.tab(Game.Tab.INVENTORY), 250, 10);
        }
        Item CoinPouch = Inventory.stream().name("Coin pouch").first();
        if (CoinPouch.stackSize() >= triggerCount || (Skills.realLevel(Constants.SKILLS_THIEVING) >= 5 && Inventory.stream().name("Coin pouch").isNotEmpty())) {
            ShouldOpenPouches();
        }
        if (Skills.realLevel(Constants.SKILLS_THIEVING) <= 4 && CoinPouch.stackSize() <= triggerCount) {
            ShouldThieve();
        }
        return false;
    }
    private void ShouldOpenPouches() {
        if (Inventory.stream().name("Coin pouch").isNotEmpty()) {
            mMain.State = "Opening pouches";
            Item CoinPouch = Inventory.stream().name("Coin pouch").first();
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
            PlayerHelper.walkToTile(SkillData.movementThieving());
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