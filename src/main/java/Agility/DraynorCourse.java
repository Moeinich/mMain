package Agility;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Camera;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.GroundItem;
import org.powbot.api.rt4.GroundItems;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Widgets;

import Helpers.ItemList;
import Helpers.PlayerHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class DraynorCourse extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) >= 10 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 80;
    }
    @Override
    public void execute() {
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().action("Eat").isEmpty()) {
            PlayerHelper playerHelper = new PlayerHelper();
            playerHelper.BankForFood(ItemList.CAKE_1891, 27);
        }

        if (Skills.level(Constants.SKILLS_HITPOINTS) < 5) {
            PlayerHelper playerHelper = new PlayerHelper();
            playerHelper.ShouldEat();
        }

        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().id(ItemList.CAKE_1891, ItemList._23_CAKE_1893, ItemList.SLICE_OF_CAKE_1895).count() >= 1) {
            String ItemName = "Mark of grace";
            GroundItem groundItem = GroundItems.stream().within(5).name(ItemName).nearest().first();
            if (groundItem.inViewport()) {
                LootMarks();
            }
            ShouldRunObstacle();
        }
    }
    public void LootMarks() {
        PlayerHelper playerHelper = new PlayerHelper();
        playerHelper.LootItems("Take", "Mark of grace");
    }

    public void ShouldRunObstacle() {
        if (!Movement.running() && Movement.energyLevel() > 30) {
            mMain.State = "Enable run..";
            Widgets.widget(160).component(29).click();
            Condition.wait( () -> Movement.running(), 150, 50);
        }

        GameObject DraynorObstacle1 = Objects.stream().within(7).id(11404).nearest().first();
        if (SkillData.DraynorStart.contains(Players.local()) && DraynorObstacle1.inViewport()) {

            Camera.angleToLocatable(DraynorObstacle1);

            DraynorObstacle1.interact("Climb", "Rough wall");
            Condition.wait( () -> (!Players.local().inMotion()), 800, 50);
        }

        GameObject DraynorObstacle2 = Objects.stream().within(7).id(11405).nearest().first();
        if (SkillData.DraynorObstacle2.contains(Players.local()) && DraynorObstacle2.inViewport()) {
            DraynorObstacle2.interact("Cross", "Tightrope");
            Condition.wait( () -> (!Players.local().inMotion()), 800, 50);
        }

        GameObject DraynorObstacle3 = Objects.stream().within(7).id(11406).nearest().first();
        if (SkillData.DraynorObstacle3.contains(Players.local()) && DraynorObstacle3.inViewport()) {
            DraynorObstacle3.interact("Cross", "Tightrope");
            Condition.wait( () -> (!Players.local().inMotion()), 800, 50);
        }
        GameObject DraynorObstacle4 = Objects.stream().within(7).id(11430).nearest().first();
        if (SkillData.DraynorObstacle4.contains(Players.local()) && DraynorObstacle4.inViewport()) {
            DraynorObstacle4.interact("Balance", "Narrow wall");
            Condition.wait( () -> (!Players.local().inMotion()), 800, 50);
        }

        GameObject DraynorObstacle5 = Objects.stream().within(7).id(11630).nearest().first();
        if (SkillData.DraynorObstacle5.contains(Players.local()) && DraynorObstacle5.inViewport()) {
            DraynorObstacle5.interact("Jump-up", "Wall");
            Condition.wait( () -> (!Players.local().inMotion()), 1200, 100);
        }

        GameObject DraynorObstacle6 = Objects.stream().within(7).id(11631).nearest().first();
        if (SkillData.DraynorObstacle6.contains(Players.local()) && DraynorObstacle6.inViewport()) {
            DraynorObstacle6.interact("Jump", "Gap");
            Condition.wait( () -> (!Players.local().inMotion()), 1200, 100);
        }

        GameObject DraynorObstacle7 = Objects.stream().within(7).id(11632).nearest().first();
        if (SkillData.DraynorObstacle7.contains(Players.local()) && DraynorObstacle7.inViewport()) {
            DraynorObstacle7.interact("Climb-down", "Crate");
            Condition.wait( () -> (!Players.local().inMotion()), 1200, 100);
        }

        if (SkillData.DraynorFailArea.contains(Players.local())) {
            mMain.State = "Move to start";
            Movement.step(SkillData.DraynorStart.getRandomTile());
            Condition.wait( () -> SkillData.DraynorStart.contains(Players.local()), 800, 50);
        }
    }
}
