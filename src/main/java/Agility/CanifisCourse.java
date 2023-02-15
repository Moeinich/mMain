package Agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Skills;

import Helpers.ItemList;
import Helpers.PlayerHelper;
import Helpers.Task;
import script.mMain;

public class CanifisCourse extends Task {

    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) >= 40 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 80;
    }
    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().action("Eat").isEmpty()) {
            mMain.state = "Get food";
            PlayerHelper playerHelper = new PlayerHelper();
            playerHelper.bankForFood(ItemList.CAKE_1891, 27);
        }

        if (Skills.level(Constants.SKILLS_HITPOINTS) < 5 && Game.tab(Game.Tab.INVENTORY)) {
            mMain.state = "Eating..";
            PlayerHelper playerHelper = new PlayerHelper();
            playerHelper.shouldEat();
        }

        if (!Movement.running() && Movement.energyLevel() > 30) {
            PlayerHelper.enableRun();
        }

        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().id(ItemList.CAKE_1891, ItemList._23_CAKE_1893, ItemList.SLICE_OF_CAKE_1895).isNotEmpty()) {
            ShouldRunObstacle();
        }
        return false;
    }

    public void ShouldRunObstacle() {
        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.FLOOR.getArea())) {
            GameObject CanifisObstacle1 = Objects.stream().within(8).id(AgilityData.obstacleInfo.canifis1.getId()).nearest().first();
            if (!CanifisObstacle1.inViewport()) {
                mMain.state = "Move to Canifis start";
                Movement.moveTo(AgilityData.CanifisAreas.START.getArea().getRandomTile());
            } else {
                AgilityHelper helper = new AgilityHelper();
                helper.handleObstacle(AgilityData.obstacleInfo.canifis1);
            }
        } else if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_1_BUG.getArea())) {
            mMain.state = "Stuck due to RS bug";
            Movement.moveTo(AgilityData.CanifisAreas.OBSTACLE_2_MOVE_TO.getArea().getRandomTile());
        }

        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_2.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis2);
        }

        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_3.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis3);
        }

        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_4.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis4);
        }

        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_5.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis5);
        }

        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_6.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis6);
        }

        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_7.getArea())) {
            GameObject CanifisObstacle7 = Objects.stream().within(10).id(AgilityData.obstacleInfo.canifis7.getId()).nearest().first();
            if (!CanifisObstacle7.inViewport()) {
                mMain.state = "Moving to obstacle 7";
                Movement.step(AgilityData.CanifisAreas.OBSTACLE_7_MOVE_TO.getArea().getRandomTile());
            } else {
                AgilityHelper helper = new AgilityHelper();
                helper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis7);
            }
        }

        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_8.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis8);
        }
    }
}
