package agility;

import static agility.AgilityData.AgilityAreas.VARROCK_FIRST;
import static agility.AgilityData.AgilityAreas.VARROCK_GHOST;
import static agility.AgilityData.AgilityAreas.VARROCK_MID;
import static agility.AgilityData.AgilityAreas.VARROCK_OBSTACLE_2;
import static agility.AgilityData.AgilityAreas.VARROCK_OBSTACLE_3;
import static agility.AgilityData.AgilityAreas.VARROCK_OBSTACLE_4;
import static agility.AgilityData.AgilityAreas.VARROCK_OBSTACLE_5;
import static agility.AgilityData.AgilityAreas.VARROCK_OBSTACLE_6;
import static agility.AgilityData.AgilityAreas.VARROCK_OBSTACLE_6_MOVETO;
import static agility.AgilityData.AgilityAreas.VARROCK_OBSTACLE_7;
import static agility.AgilityData.AgilityAreas.VARROCK_OBSTACLE_7_MOVETO;
import static agility.AgilityData.AgilityAreas.VARROCK_OBSTACLE_8;
import static agility.AgilityData.AgilityAreas.VARROCK_OBSTACLE_9;
import static agility.AgilityData.AgilityAreas.VARROCK_START;
import static agility.AgilityData.AgilityAreas.VARROCK_TOP;
import static agility.AgilityData.obstacleInfo.varrock1;
import static agility.AgilityData.obstacleInfo.varrock2;
import static agility.AgilityData.obstacleInfo.varrock3;
import static agility.AgilityData.obstacleInfo.varrock4;
import static agility.AgilityData.obstacleInfo.varrock5;
import static agility.AgilityData.obstacleInfo.varrock6;
import static agility.AgilityData.obstacleInfo.varrock7;
import static agility.AgilityData.obstacleInfo.varrock8;
import static agility.AgilityData.obstacleInfo.varrock9;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Camera;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import helpers.PlayerHelper;
import helpers.extentions.Task;
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

        if (!(Camera.yaw() > 77 && Camera.yaw() < 104) || Camera.pitch() < 85) {
            Camera.turnTo(90,99);
        }

        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().action("Eat").isNotEmpty()) {
            ShouldRunObstacle();
        }
        return false;
    }

    public void ShouldRunObstacle() {
        if (PlayerHelper.withinArea(VARROCK_START.getArea())) {
            AgilityHelper.handleObstacle(varrock1);
        }
        if (PlayerHelper.withinArea(VARROCK_TOP.getArea())
                || PlayerHelper.withinArea(VARROCK_MID.getArea())
                || PlayerHelper.withinArea(VARROCK_GHOST.getArea())
                || PlayerHelper.withinArea(VARROCK_FIRST.getArea()))
        {
            if (PlayerHelper.withinArea(VARROCK_OBSTACLE_2.getArea())) {
                AgilityHelper.handleObstacleWithLoot(varrock2);
            }
            else if (PlayerHelper.withinArea(VARROCK_OBSTACLE_3.getArea())) {
                AgilityHelper.handleObstacleWithLoot(varrock3);
            }
            else if (PlayerHelper.withinArea(VARROCK_OBSTACLE_4.getArea())) {
                AgilityHelper.handleObstacleWithLoot(varrock4);
            }
            else if (PlayerHelper.withinArea(VARROCK_OBSTACLE_5.getArea())) {
                AgilityHelper.handleObstacleWithLoot(varrock5);
            }
            else if (PlayerHelper.withinArea(VARROCK_OBSTACLE_6.getArea())) {
                if (PlayerHelper.nearestGroundItem(15, "Mark of grace").inViewport()) {
                    mMain.state = "Pickup mark";
                    PlayerHelper.lootItems("Take", "Mark of grace");
                } else if (!PlayerHelper.nearestGameObject(8, varrock6.getId()).inViewport()) {
                    mMain.state = "Move to Varrock OBS6";
                    Movement.step(VARROCK_OBSTACLE_6_MOVETO.getArea().getRandomTile());
                    Condition.wait(() -> VARROCK_OBSTACLE_6_MOVETO.getArea().contains(Players.local()), 300, 10);
                } else {
                    AgilityHelper.handleObstacle(varrock6);
                }
            }
            else if (PlayerHelper.withinArea(VARROCK_OBSTACLE_7.getArea())) {
                if (PlayerHelper.nearestGroundItem(10,"Mark of grace").inViewport()) {
                    mMain.state = "Pickup mark";
                    PlayerHelper.lootItems("Take", "Mark of grace");
                } else if (!PlayerHelper.nearestGameObject(8, varrock7.getId()).inViewport()) {
                    mMain.state = "Move to Varrock OBS7";
                    Movement.step(VARROCK_OBSTACLE_7_MOVETO.getArea().getRandomTile());
                    Condition.wait(() -> VARROCK_OBSTACLE_7_MOVETO.getArea().contains(Players.local()), 300, 10);
                } else {
                    AgilityHelper.handleObstacle(varrock7);
                }
            }
            else if (PlayerHelper.withinArea(VARROCK_OBSTACLE_8.getArea())) {
                AgilityHelper.handleObstacleWithLoot(varrock8);
            }
            else if (PlayerHelper.withinArea(VARROCK_OBSTACLE_9.getArea())) {
                AgilityHelper.handleObstacleWithLoot(varrock9);
            }
        }
        else {
            mMain.state = "Move to Seers start";
            System.out.println("Moving to Seers start area");
            Movement.moveTo(VARROCK_START.getArea().getRandomTile());
        }
    }
}
