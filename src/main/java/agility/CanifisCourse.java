package agility;

import static agility.AgilityData.CanifisAreas.OBSTACLE_1_BUG;
import static agility.AgilityData.CanifisAreas.OBSTACLE_2;
import static agility.AgilityData.CanifisAreas.OBSTACLE_2_MOVE_TO;
import static agility.AgilityData.CanifisAreas.OBSTACLE_3;
import static agility.AgilityData.CanifisAreas.OBSTACLE_4;
import static agility.AgilityData.CanifisAreas.OBSTACLE_5;
import static agility.AgilityData.CanifisAreas.OBSTACLE_6;
import static agility.AgilityData.CanifisAreas.OBSTACLE_7;
import static agility.AgilityData.CanifisAreas.OBSTACLE_7_MOVE_TO;
import static agility.AgilityData.CanifisAreas.OBSTACLE_8;
import static agility.AgilityData.CanifisAreas.START;
import static agility.AgilityData.VarrockAreas.OBSTACLE_7_MOVETO;
import static agility.AgilityData.obstacleInfo.canifis1;
import static agility.AgilityData.obstacleInfo.canifis2;
import static agility.AgilityData.obstacleInfo.canifis3;
import static agility.AgilityData.obstacleInfo.canifis4;
import static agility.AgilityData.obstacleInfo.canifis5;
import static agility.AgilityData.obstacleInfo.canifis6;
import static agility.AgilityData.obstacleInfo.canifis7;
import static agility.AgilityData.obstacleInfo.canifis8;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
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
        if (PlayerHelper.withinArea(START.getArea())) {
            AgilityHelper.handleObstacle(canifis1);
        }
        else if (PlayerHelper.withinArea(OBSTACLE_1_BUG.getArea())) {
            mMain.state = "Stuck due to RS bug";
            Movement.moveTo(OBSTACLE_2_MOVE_TO.getArea().getRandomTile());
        }
        else if (PlayerHelper.withinArea(OBSTACLE_2.getArea())) {
            AgilityHelper.handleObstacleWithLoot(canifis2);
        }
        else if (PlayerHelper.withinArea(OBSTACLE_3.getArea())) {
            AgilityHelper.handleObstacleWithLoot(canifis3);
        }
        else if (PlayerHelper.withinArea(OBSTACLE_4.getArea())) {
            AgilityHelper.handleObstacleWithLoot(canifis4);
        }
        else if (PlayerHelper.withinArea(OBSTACLE_5.getArea())) {
            AgilityHelper.handleObstacleWithLoot(canifis5);
        }
        else if (PlayerHelper.withinArea(OBSTACLE_6.getArea())) {
            AgilityHelper.handleObstacleWithLoot(canifis6);
        }
        else if (PlayerHelper.withinArea(OBSTACLE_7.getArea())) {
            GameObject CanifisObstacle7 = PlayerHelper.nearestGameObject(10, canifis7.getId());
            if (!CanifisObstacle7.inViewport()) {
                mMain.state = "Moving to obstacle 7";
                Movement.step(OBSTACLE_7_MOVE_TO.getArea().getRandomTile());
                Condition.wait(() -> OBSTACLE_7_MOVETO.getArea().contains(Players.local()), 300, 10);
            } else {
                AgilityHelper.handleObstacleWithLoot(canifis7);
            }
        }
        else if (PlayerHelper.withinArea(OBSTACLE_8.getArea())) {
            AgilityHelper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis8);
        }
        else {
            mMain.state = "Move to Canifis start";
            System.out.println("Moving to Canifis start area");
            Movement.moveTo(START.getArea().getRandomTile());
        }
    }
}
