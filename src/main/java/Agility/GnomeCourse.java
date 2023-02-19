package Agility;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Skills;

import Helpers.playerHelper;
import Helpers.Task;

public class GnomeCourse extends Task {

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) <= 9;
    }
    @Override
    public boolean execute() {
        if (!Movement.running() && Movement.energyLevel() > 30) {
            playerHelper.enableRun();
        } else ShouldRunObstacle();
        return false;
    }
    public void ShouldRunObstacle() {
        if (playerHelper.withinArea(AgilityData.GnomeAreas.OBSTACLE_1.getArea()) || playerHelper.withinArea(AgilityData.GnomeAreas.END.getArea())) {
            if (playerHelper.withinArea(AgilityData.GnomeAreas.END.getArea())) {
                Movement.step(AgilityData.GnomeAreas.OBSTACLE_1.getArea().getRandomTile());
            }
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacle(AgilityData.obstacleInfo.gnome1);
        }
        if (playerHelper.withinArea(AgilityData.GnomeAreas.OBSTACLE_2.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacle(AgilityData.obstacleInfo.gnome2);
        }
        if (playerHelper.withinArea(AgilityData.GnomeAreas.OBSTACLE_3.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacle(AgilityData.obstacleInfo.gnome3);
        }
        if (playerHelper.withinArea(AgilityData.GnomeAreas.OBSTACLE_4.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacle(AgilityData.obstacleInfo.gnome4);
        }
        if (playerHelper.withinArea(AgilityData.GnomeAreas.OBSTACLE_5.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacle(AgilityData.obstacleInfo.gnome5);
        }
        if (playerHelper.withinArea(AgilityData.GnomeAreas.OBSTACLE_6.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacle(AgilityData.obstacleInfo.gnome6);
            Condition.wait( () -> playerHelper.withinArea(AgilityData.GnomeAreas.OBSTACLE_7.getArea()), 250, 50);
        }
        if (playerHelper.withinArea(AgilityData.GnomeAreas.OBSTACLE_7.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacle(AgilityData.obstacleInfo.gnome7);
        }
    }
}
