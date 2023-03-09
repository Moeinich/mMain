package agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Varpbits;

import helpers.PlayerHelper;
import helpers.extentions.Task;
import quests.common.QuestVarpbits;
import script.mMain;

public class CanifisCourse extends Task {

    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) >= 40 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 59;
    }
    @Override
    public boolean execute() {
        if (Varpbits.varpbit(QuestVarpbits.PRIEST_IN_PERIL.getQuestVarbit()) != QuestVarpbits.PRIEST_IN_PERIL.getFinishedValue()) {
            System.out.println("Priest in Peril not done, skipping agility for now");
            mMain.state = "PiP not done";
            mMain.skillRunning.set(false);
        }

        if (!Movement.running() && Movement.energyLevel() > 30) {
            PlayerHelper.enableRun();
        }

        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().action("Eat").isNotEmpty()) {
            ShouldRunObstacle();
        }
        return false;
    }

    public void ShouldRunObstacle() {
        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.FLOOR.getArea())) {
            if (!PlayerHelper.withinArea(AgilityData.CanifisAreas.START.getArea())) {
                mMain.state = "Move to Canifis start";
                Movement.moveTo(AgilityData.CanifisAreas.START.getArea().getRandomTile());
            } else {
                AgilityHelper.handleObstacle(AgilityData.obstacleInfo.canifis1);
            }
        } else if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_1_BUG.getArea())) {
            mMain.state = "Stuck due to RS bug";
            Movement.moveTo(AgilityData.CanifisAreas.OBSTACLE_2_MOVE_TO.getArea().getRandomTile());
        }

        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_2.getArea())) {
            AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis2);
        }

        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_3.getArea())) {
            AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis3);
        }

        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_4.getArea())) {
            AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis4);
        }

        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_5.getArea())) {
            AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis5);
        }

        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_6.getArea())) {
            AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis6);
        }

        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_7.getArea())) {
            GameObject CanifisObstacle7 = PlayerHelper.nearestGameObject(10, AgilityData.obstacleInfo.canifis7.getId());
            if (!CanifisObstacle7.inViewport()) {
                mMain.state = "Moving to obstacle 7";
                Movement.step(AgilityData.CanifisAreas.OBSTACLE_7_MOVE_TO.getArea().getRandomTile());
            } else {
                AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis7);
            }
        }

        if (PlayerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_8.getArea())) {
            AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis8);
        }
    }
}
