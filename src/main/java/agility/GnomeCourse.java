package agility;

import static agility.AgilityData.GnomeAreas.LOWER;
import static agility.AgilityData.GnomeAreas.MID;
import static agility.AgilityData.GnomeAreas.OBSTACLE_2;
import static agility.AgilityData.GnomeAreas.OBSTACLE_3;
import static agility.AgilityData.GnomeAreas.OBSTACLE_4;
import static agility.AgilityData.GnomeAreas.OBSTACLE_5;
import static agility.AgilityData.GnomeAreas.OBSTACLE_6;
import static agility.AgilityData.GnomeAreas.OBSTACLE_7;
import static agility.AgilityData.GnomeAreas.START;
import static agility.AgilityData.GnomeAreas.TOP;
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
        if (PlayerHelper.withinArea(START.getArea())) {
            AgilityHelper.handleObstacle(gnome1);
        }
        if (PlayerHelper.withinArea(TOP.getArea())
                || PlayerHelper.withinArea(MID.getArea())
                || PlayerHelper.withinArea(LOWER.getArea()))
        {
            if (PlayerHelper.withinArea(OBSTACLE_2.getArea())) {
                AgilityHelper.handleObstacle(gnome2);
            }
            else if (PlayerHelper.withinArea(OBSTACLE_3.getArea())) {
                AgilityHelper.handleObstacle(gnome3);
            }
            else if (PlayerHelper.withinArea(OBSTACLE_4.getArea())) {
                AgilityHelper.handleObstacle(gnome4);
            }
            else if (PlayerHelper.withinArea(OBSTACLE_5.getArea())) {
                AgilityHelper.handleObstacle(gnome5);
            }
            else if (PlayerHelper.withinArea(OBSTACLE_6.getArea())) {
                AgilityHelper.handleObstacle(gnome6);
                Condition.wait( () -> PlayerHelper.withinArea(OBSTACLE_7.getArea()), 250, 50);
            }
            else if (PlayerHelper.withinArea(OBSTACLE_7.getArea())) {
                AgilityHelper.handleObstacle(gnome7);
            }
        }
        else {
            mMain.state = "Move to Gnome start";
            System.out.println("Moving to Gnome start area");
            Movement.moveTo(START.getArea().getRandomTile());
        }
    }
}
