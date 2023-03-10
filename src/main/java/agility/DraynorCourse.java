package agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
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

        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().action("Eat").isNotEmpty()) {
            ShouldRunObstacle();
        }
        return false;
    }

    public void ShouldRunObstacle() {
        if (PlayerHelper.withinArea(AgilityData.DraynorAreas.FLOOR.getArea())) {
            if (!PlayerHelper.withinArea(AgilityData.DraynorAreas.START.getArea())) {
                mMain.state = "Move to draynor start";
                Movement.moveTo(AgilityData.DraynorAreas.START.getArea().getRandomTile());
            } else {
                AgilityHelper.handleObstacle(AgilityData.obstacleInfo.draynor1);
            }
        }
        if (PlayerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_2.getArea())) {
            AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.draynor2);
        }
        if (PlayerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_3.getArea())) {
            AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.draynor3);
        }
        if (PlayerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_4.getArea())) {
            AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.draynor4);
        }
        if (PlayerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_5.getArea())) {
            AgilityHelper.handleObstacle(AgilityData.obstacleInfo.draynor5);
        }
        if (PlayerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_6.getArea())) {
            AgilityHelper.handleObstacle(AgilityData.obstacleInfo.draynor6);
        }
        if (PlayerHelper.withinArea(AgilityData.DraynorAreas.OBSTACLE_7.getArea())) {
            AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.draynor7);
        }
        if (!PlayerHelper.withinArea(AgilityData.DraynorAreas.FLOOR.getArea())) {
            mMain.state = "Move to Seers start";
            Movement.moveTo(AgilityData.DraynorAreas.START.getArea().getRandomTile());
        }
    }
}