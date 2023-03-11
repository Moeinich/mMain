package agility;

import static agility.AgilityData.AgilityAreas.SEERS_FIRST;
import static agility.AgilityData.AgilityAreas.SEERS_MID;
import static agility.AgilityData.AgilityAreas.SEERS_OBSTACLE_2;
import static agility.AgilityData.AgilityAreas.SEERS_OBSTACLE_3;
import static agility.AgilityData.AgilityAreas.SEERS_OBSTACLE_4;
import static agility.AgilityData.AgilityAreas.SEERS_OBSTACLE_5;
import static agility.AgilityData.AgilityAreas.SEERS_OBSTACLE_6;
import static agility.AgilityData.AgilityAreas.SEERS_START;
import static agility.AgilityData.AgilityAreas.SEERS_TOP;
import static agility.AgilityData.obstacleInfo.seers1;
import static agility.AgilityData.obstacleInfo.seers2;
import static agility.AgilityData.obstacleInfo.seers3;
import static agility.AgilityData.obstacleInfo.seers4;
import static agility.AgilityData.obstacleInfo.seers5;
import static agility.AgilityData.obstacleInfo.seers6;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
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
        if (PlayerHelper.withinArea(SEERS_START.getArea())) {
            AgilityHelper.handleObstacle(seers1);
        }
        if (PlayerHelper.withinArea(SEERS_TOP.getArea())
                || PlayerHelper.withinArea(SEERS_MID.getArea())
                || PlayerHelper.withinArea(SEERS_FIRST.getArea()))
        {
            if (PlayerHelper.withinArea(SEERS_OBSTACLE_2.getArea())) {
                AgilityHelper.handleObstacleWithLoot(seers2);
            }
            else if (PlayerHelper.withinArea(SEERS_OBSTACLE_3.getArea())) {
                AgilityHelper.handleObstacleWithLoot(seers3);
            }
            else if (PlayerHelper.withinArea(SEERS_OBSTACLE_4.getArea())) {
                AgilityHelper.handleObstacleWithLoot(seers4);
            }
            else if (PlayerHelper.withinArea(SEERS_OBSTACLE_5.getArea())) {
                AgilityHelper.handleObstacleWithLoot(seers5);
            }
            else if (PlayerHelper.withinArea(SEERS_OBSTACLE_6.getArea())) {
                AgilityHelper.handleObstacleWithLoot(seers6);
            }
        }
        else {
            mMain.state = "Move to Seers start";
            Movement.moveTo(SEERS_START.getArea().getRandomTile());
        }
    }
}
