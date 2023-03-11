package agility;

import static agility.AgilityData.AgilityAreas.GNOME_LOWER;
import static agility.AgilityData.AgilityAreas.GNOME_MID;
import static agility.AgilityData.AgilityAreas.GNOME_OBSTACLE_2;
import static agility.AgilityData.AgilityAreas.GNOME_OBSTACLE_3;
import static agility.AgilityData.AgilityAreas.GNOME_OBSTACLE_4;
import static agility.AgilityData.AgilityAreas.GNOME_OBSTACLE_5;
import static agility.AgilityData.AgilityAreas.GNOME_OBSTACLE_6;
import static agility.AgilityData.AgilityAreas.GNOME_OBSTACLE_7;
import static agility.AgilityData.AgilityAreas.GNOME_START;
import static agility.AgilityData.AgilityAreas.GNOME_TOP;
import static agility.AgilityData.obstacleInfo.gnome1;
import static agility.AgilityData.obstacleInfo.gnome2;
import static agility.AgilityData.obstacleInfo.gnome3;
import static agility.AgilityData.obstacleInfo.gnome4;
import static agility.AgilityData.obstacleInfo.gnome5;
import static agility.AgilityData.obstacleInfo.gnome6;
import static agility.AgilityData.obstacleInfo.gnome7;


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
        if (PlayerHelper.withinArea(GNOME_START.getArea())) {
            AgilityHelper.handleObstacle(gnome1);
        }
        if (PlayerHelper.withinArea(GNOME_TOP.getArea())
                || PlayerHelper.withinArea(GNOME_MID.getArea())
                || PlayerHelper.withinArea(GNOME_LOWER.getArea()))
        {
            if (PlayerHelper.withinArea(GNOME_OBSTACLE_2.getArea())) {
                AgilityHelper.handleObstacle(gnome2);
            }
            else if (PlayerHelper.withinArea(GNOME_OBSTACLE_3.getArea())) {
                AgilityHelper.handleObstacle(gnome3);
            }
            else if (PlayerHelper.withinArea(GNOME_OBSTACLE_4.getArea())) {
                AgilityHelper.handleObstacle(gnome4);
            }
            else if (PlayerHelper.withinArea(GNOME_OBSTACLE_5.getArea())) {
                AgilityHelper.handleObstacle(gnome5);
            }
            else if (PlayerHelper.withinArea(GNOME_OBSTACLE_6.getArea())) {
                AgilityHelper.handleObstacle(gnome6);
                Condition.wait( () -> PlayerHelper.withinArea(GNOME_OBSTACLE_7.getArea()), 250, 50);
            }
            else if (PlayerHelper.withinArea(GNOME_OBSTACLE_7.getArea())) {
                AgilityHelper.handleObstacle(gnome7);
            }
        }
        else {
            mMain.state = "Move to Gnome start";
            System.out.println("Moving to Gnome start area");
            Movement.moveTo(GNOME_START.getArea().getRandomTile());
        }
    }
}
