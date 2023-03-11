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
import helpers.extentions.Task;
import script.mMain;

public class SeersCourse extends Task {

    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) >= 60 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 80;
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
        if (PlayerHelper.withinArea(AgilityData.SeersAreas.START.getArea())) {
            AgilityHelper.handleObstacle(AgilityData.obstacleInfo.seers1);
        }
        if (PlayerHelper.withinArea(AgilityData.SeersAreas.TOP.getArea()) || PlayerHelper.withinArea(AgilityData.SeersAreas.MID.getArea())) {
            if (PlayerHelper.withinArea(AgilityData.SeersAreas.OBSTACLE_2.getArea())) {
                AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.seers2);
            }
            else if (PlayerHelper.withinArea(AgilityData.SeersAreas.OBSTACLE_3.getArea())) {
                AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.seers3);
            }
            else if (PlayerHelper.withinArea(AgilityData.SeersAreas.OBSTACLE_4.getArea())) {
                AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.seers4);
            }
            else if (PlayerHelper.withinArea(AgilityData.SeersAreas.OBSTACLE_5.getArea())) {
                AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.seers5);
            }
            else if (PlayerHelper.withinArea(AgilityData.SeersAreas.OBSTACLE_6.getArea())) {
                AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.seers6);
            }
        }
        else {
            mMain.state = "Move to Seers start";
            Movement.moveTo(AgilityData.SeersAreas.START.getArea().getRandomTile());
        }
    }
}
