package agility;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Skills;

import helpers.PlayerHelper;
import helpers.extentions.Task;
import script.mMain;

public class GnomeCourse extends Task {

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) <= 9;
    }
    @Override
    public boolean execute() {
        if (!Movement.running() && Movement.energyLevel() > 30) {
            PlayerHelper.enableRun();
        } else ShouldRunObstacle();
        return false;
    }
    public void ShouldRunObstacle() {
        if (PlayerHelper.withinArea(AgilityData.GnomeAreas.START.getArea())) {
            AgilityHelper.handleObstacle(AgilityData.obstacleInfo.gnome1);
        }
        if (PlayerHelper.withinArea(AgilityData.GnomeAreas.TOP.getArea())
                || PlayerHelper.withinArea(AgilityData.GnomeAreas.MID.getArea())
                || PlayerHelper.withinArea(AgilityData.GnomeAreas.LOWER.getArea()))
        {
            if (PlayerHelper.withinArea(AgilityData.GnomeAreas.OBSTACLE_2.getArea())) {
                AgilityHelper.handleObstacle(AgilityData.obstacleInfo.gnome2);
            }
            else if (PlayerHelper.withinArea(AgilityData.GnomeAreas.OBSTACLE_3.getArea())) {
                AgilityHelper.handleObstacle(AgilityData.obstacleInfo.gnome3);
            }
            else if (PlayerHelper.withinArea(AgilityData.GnomeAreas.OBSTACLE_4.getArea())) {
                AgilityHelper.handleObstacle(AgilityData.obstacleInfo.gnome4);
            }
            else if (PlayerHelper.withinArea(AgilityData.GnomeAreas.OBSTACLE_5.getArea())) {
                AgilityHelper.handleObstacle(AgilityData.obstacleInfo.gnome5);
            }
            else if (PlayerHelper.withinArea(AgilityData.GnomeAreas.OBSTACLE_6.getArea())) {
                AgilityHelper.handleObstacle(AgilityData.obstacleInfo.gnome6);
                Condition.wait( () -> PlayerHelper.withinArea(AgilityData.GnomeAreas.OBSTACLE_7.getArea()), 250, 50);
            }
            else if (PlayerHelper.withinArea(AgilityData.GnomeAreas.OBSTACLE_7.getArea())) {
                AgilityHelper.handleObstacle(AgilityData.obstacleInfo.gnome7);
            }
        }
        else {
            mMain.state = "Move to Gnome start";
            System.out.println("Moving to Gnome start area");
            Movement.moveTo(AgilityData.SeersAreas.START.getArea().getRandomTile());
        }
    }
}
