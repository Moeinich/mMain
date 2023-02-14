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

public class VarrockCourse extends Task {

    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) >= 30 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 39;
    }
    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().action("Eat").isEmpty()) {
            mMain.State = "Get food";
            PlayerHelper playerHelper = new PlayerHelper();
            playerHelper.bankForFood(ItemList.CAKE_1891, 27);
        }

        if (Skills.level(Constants.SKILLS_HITPOINTS) < 5 && Game.tab(Game.Tab.INVENTORY)) {
            mMain.State = "Eating..";
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
        if (PlayerHelper.withinArea(AgilityData.VarrockAreas.FLOOR.getArea())) {
            GameObject VarrockObstacle1 = Objects.stream().within(8).id(AgilityData.obstacleInfo.varrock1.getId()).nearest().first();
            if (!VarrockObstacle1.inViewport()) {
                mMain.State = "Move to Canifis start";
                Movement.moveTo(AgilityData.VarrockAreas.START.getArea().getRandomTile());
            } else {
                AgilityHelper helper = new AgilityHelper();
                helper.handleObstacle(AgilityData.obstacleInfo.varrock1);
            }
        }
        if (PlayerHelper.withinArea(AgilityData.VarrockAreas.OBSTACLE_2.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.varrock2);
        }
        if (PlayerHelper.withinArea(AgilityData.VarrockAreas.OBSTACLE_3.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.varrock3);
        }
        if (PlayerHelper.withinArea(AgilityData.VarrockAreas.OBSTACLE_4.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.varrock4);
        }
        if (PlayerHelper.withinArea(AgilityData.VarrockAreas.OBSTACLE_5.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.varrock5);
        }
        if (PlayerHelper.withinArea(AgilityData.VarrockAreas.OBSTACLE_6.getArea())) {
            GameObject VarrockObstacle1 = Objects.stream().within(8).id(AgilityData.obstacleInfo.varrock6.getId()).nearest().first();
            if (!VarrockObstacle1.inViewport()) {
                mMain.State = "Move to Canifis start";
                Movement.moveTo(AgilityData.VarrockAreas.OBSTACLE_6_MOVETO.getArea().getRandomTile());
            } else {
                AgilityHelper helper = new AgilityHelper();
                helper.handleObstacleWithLoot(AgilityData.obstacleInfo.varrock6);
            }
        }
        if (PlayerHelper.withinArea(AgilityData.VarrockAreas.OBSTACLE_7.getArea())) {
            GameObject VarrockObstacle1 = Objects.stream().within(8).id(AgilityData.obstacleInfo.varrock7.getId()).nearest().first();
            if (!VarrockObstacle1.inViewport()) {
                mMain.State = "Move to Canifis start";
                Movement.moveTo(AgilityData.VarrockAreas.OBSTACLE_7_MOVETO.getArea().getRandomTile());
            } else {
                AgilityHelper helper = new AgilityHelper();
                helper.handleObstacleWithLoot(AgilityData.obstacleInfo.varrock7);
            }
        }

        if (PlayerHelper.withinArea(AgilityData.VarrockAreas.OBSTACLE_8.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.varrock8);
        }
        if (PlayerHelper.withinArea(AgilityData.VarrockAreas.OBSTACLE_9.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.varrock9);
        }
    }
}
