package agility;

import org.powbot.api.rt4.Camera;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Player;
import org.powbot.api.rt4.Skills;

import helpers.PlayerHelper;
import helpers.extentions.Task;
import script.mMain;

public class DraynorCourse extends Task {

    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) >= 10 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 29;
    }
    @Override
    public boolean execute() {
        if (!Movement.running() && Movement.energyLevel() > 30) {
            PlayerHelper.enableRun();
        }

        if (!(Camera.yaw() > 77 && Camera.yaw() < 104) || Camera.pitch() < 85) {
            Camera.turnTo(90,99);
        }

        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().action("Eat").isNotEmpty()) {
            ShouldRunObstacle();
        }
        return false;
    }

    public void ShouldRunObstacle() {
        if (PlayerHelper.withinArea(AgilityData.DraynorAreas.START.getArea())) {
            AgilityHelper.handleObstacle(AgilityData.obstacleInfo.draynor1);
        }
        else if (PlayerHelper.withinArea(AgilityData.DraynorAreas.TOP.getArea())) {
            if (PlayerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_2.getArea())) {
                AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.draynor2);
            }
            else if (PlayerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_3.getArea())) {
                AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.draynor3);
            }
            else if (PlayerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_4.getArea())) {
                AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.draynor4);
            }
            else if (PlayerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_5.getArea())) {
                AgilityHelper.handleObstacle(AgilityData.obstacleInfo.draynor5);
            }
            else if (PlayerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_6.getArea())) {
                AgilityHelper.handleObstacle(AgilityData.obstacleInfo.draynor6);
            }
            else if (PlayerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_7.getArea())) {
                AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.draynor7);
            }
        }
        else {
            mMain.state = "Move to Draynor start";
            System.out.println("Moving to draynor start area");
            Movement.moveTo(AgilityData.DraynorAreas.START.getArea().getRandomTile());
        }
    }
}