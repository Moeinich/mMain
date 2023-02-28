package agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.GroundItem;
import org.powbot.api.rt4.GroundItems;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Skills;

import helpers.PlayerHelper;
import helpers.Task;
import script.mMain;

public class VarrockCourse extends Task {

    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) >= 30 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 39;
    }
    @Override
    public boolean execute() {
        if (!Movement.running() && Movement.energyLevel() > 30) {
            PlayerHelper.enableRun();
        }

        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().action("Eat").isNotEmpty()) {
            ShouldRunObstacle();
        }
        return false;
    }

    public void ShouldRunObstacle() {
        if (PlayerHelper.withinArea(AgilityData.VarrockAreas.FLOOR.getArea())) {
            if (!PlayerHelper.withinArea(AgilityData.VarrockAreas.START.getArea())) {
                mMain.state = "Move to Varrock start";
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
            GameObject VarrockObstacle6 = PlayerHelper.nearestGameObject(8, AgilityData.obstacleInfo.varrock6.getId());
            GroundItem groundItem = GroundItems.stream().within(10).name("Mark of grace").reachable().nearest().first();
            if (groundItem.inViewport()) {
                mMain.state = "Pickup mark";
                PlayerHelper.lootItems("Take", "Mark of grace");
            } else if (!VarrockObstacle6.inViewport()) {
                mMain.state = "Move to Varrock OBS6";
                Movement.step(AgilityData.VarrockAreas.OBSTACLE_6_MOVETO.getArea().getRandomTile());
            } else {
                AgilityHelper helper = new AgilityHelper();
                helper.handleObstacleWithLoot(AgilityData.obstacleInfo.varrock6);
            }
        }
        if (PlayerHelper.withinArea(AgilityData.VarrockAreas.OBSTACLE_7.getArea())) {
            GameObject VarrockObstacle7 = PlayerHelper.nearestGameObject(8, AgilityData.obstacleInfo.varrock7.getId());
            GroundItem groundItem = GroundItems.stream().within(10).name("Mark of grace").reachable().nearest().first();
            if (groundItem.reachable()) {
                mMain.state = "Pickup mark";
                PlayerHelper.lootItems("Take", "Mark of grace");
            } else if (!VarrockObstacle7.inViewport()) {
                mMain.state = "Move to Varrock OBS7";
                Movement.step(AgilityData.VarrockAreas.OBSTACLE_7_MOVETO.getArea().getRandomTile());
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