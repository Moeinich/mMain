package Agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.ItemList;
import Helpers.playerHelper;
import Helpers.Task;
import script.mMain;

public class DraynorCourse extends Task {

    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) >= 10 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 29;
    }
    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().action("Eat").isEmpty()) {
            mMain.state = "Get food";
            playerHelper playerHelper = new playerHelper();
            playerHelper.bankForFood(ItemList.CAKE_1891, 27);
        }

        if (Players.local().healthPercent() < 60 && Game.tab(Game.Tab.INVENTORY)) {
            mMain.state = "Eating..";
            playerHelper playerHelper = new playerHelper();
            playerHelper.shouldEat();
        }

        if (!Movement.running() && Movement.energyLevel() > 30) {
            playerHelper.enableRun();
        }

        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().action("Eat").isNotEmpty()) {
            ShouldRunObstacle();
        }
        return false;
    }

    public void ShouldRunObstacle() {
        if (playerHelper.withinArea(AgilityData.DraynorAreas.FLOOR.getArea())) {
            if (!playerHelper.withinArea(AgilityData.DraynorAreas.START.getArea())) {
                mMain.state = "Move to draynor start";
                Movement.moveTo(AgilityData.DraynorAreas.START.getArea().getRandomTile());
            } else {
                AgilityHelper helper = new AgilityHelper();
                helper.handleObstacle(AgilityData.obstacleInfo.draynor1);
            }
        }
        if (playerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_2.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.draynor2);
        }
        if (playerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_3.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.draynor3);
        }
        if (playerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_4.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.draynor4);
        }
        if (playerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_5.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacle(AgilityData.obstacleInfo.draynor5);
        }
        if (playerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_6.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacle(AgilityData.obstacleInfo.draynor6);
        }
        if (playerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_7.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.draynor7);
        }
    }
}