package agility;

import static agility.AgilityData.DraynorAreas.OBSTACLE_2;
import static agility.AgilityData.DraynorAreas.OBSTACLE_3;
import static agility.AgilityData.DraynorAreas.OBSTACLE_4;
import static agility.AgilityData.DraynorAreas.OBSTACLE_5;
import static agility.AgilityData.DraynorAreas.OBSTACLE_6;
import static agility.AgilityData.DraynorAreas.OBSTACLE_7;
import static agility.AgilityData.DraynorAreas.START;
import static agility.AgilityData.DraynorAreas.TOP;
import static agility.AgilityData.obstacleInfo.draynor1;
import static agility.AgilityData.obstacleInfo.draynor2;
import static agility.AgilityData.obstacleInfo.draynor3;
import static agility.AgilityData.obstacleInfo.draynor4;
import static agility.AgilityData.obstacleInfo.draynor5;
import static agility.AgilityData.obstacleInfo.draynor6;
import static agility.AgilityData.obstacleInfo.draynor7;


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
        if (PlayerHelper.withinArea(START.getArea())) {
            AgilityHelper.handleObstacle(draynor1);
        }
        else if (PlayerHelper.withinArea(TOP.getArea())) {
            if (PlayerHelper.withinArea(OBSTACLE_2.getArea())) {
                AgilityHelper.handleObstacleWithLoot(draynor2);
            }
            else if (PlayerHelper.withinArea(OBSTACLE_3.getArea())) {
                AgilityHelper.handleObstacleWithLoot(draynor3);
            }
            else if (PlayerHelper.withinArea(OBSTACLE_4.getArea())) {
                AgilityHelper.handleObstacleWithLoot(draynor4);
            }
            else if (PlayerHelper.withinArea(OBSTACLE_5.getArea())) {
                AgilityHelper.handleObstacle(draynor5);
            }
            else if (PlayerHelper.withinArea(OBSTACLE_6.getArea())) {
                AgilityHelper.handleObstacle(draynor6);
            }
            else if (PlayerHelper.withinArea(OBSTACLE_7.getArea())) {
                AgilityHelper.handleObstacleWithLoot(draynor7);
            }
        }
        else {
            mMain.state = "Move to Draynor start";
            System.out.println("Moving to draynor start area");
            Movement.moveTo(START.getArea().getRandomTile());
        }
    }
}