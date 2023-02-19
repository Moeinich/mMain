package Agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Varpbits;

import Helpers.ItemList;
import Helpers.playerHelper;
import Helpers.Task;
import Helpers.questVarpbits;
import Helpers.skillData;
import script.mMain;

public class CanifisCourse extends Task {

    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) >= 40 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 80;
    }
    @Override
    public boolean execute() {
        if (Varpbits.varpbit(questVarpbits.PRIEST_IN_PERIL.getQuestVarbit()) != questVarpbits.PRIEST_IN_PERIL.getFinishedValue()) {
            System.out.println("Priest in Peril not done, blacklisting agility.");
            mMain.state = "PiP not done";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().action("Eat").isEmpty()) {
            mMain.state = "Get food";
            playerHelper.bankForFood(ItemList.CAKE_1891, 27);
        }

        if (Players.local().healthPercent() < 60 && Game.tab(Game.Tab.INVENTORY)) {
            mMain.state = "Eating..";
            playerHelper.shouldEat();
        }

        if (!Movement.running() && Movement.energyLevel() > 30) {
            playerHelper.enableRun();
        }

        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().action("Eat").isNotEmpty()) {
            ShouldRunObstacle();
        }
        return false;
    }

    public void ShouldRunObstacle() {
        if (playerHelper.withinArea(AgilityData.CanifisAreas.FLOOR.getArea())) {
            if (!playerHelper.withinArea(AgilityData.CanifisAreas.START.getArea())) {
                mMain.state = "Move to Canifis start";
                Movement.moveTo(AgilityData.CanifisAreas.START.getArea().getRandomTile());
            } else {
                AgilityHelper helper = new AgilityHelper();
                helper.handleObstacle(AgilityData.obstacleInfo.canifis1);
            }
        } else if (playerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_1_BUG.getArea())) {
            mMain.state = "Stuck due to RS bug";
            Movement.moveTo(AgilityData.CanifisAreas.OBSTACLE_2_MOVE_TO.getArea().getRandomTile());
        }

        if (playerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_2.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis2);
        }

        if (playerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_3.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis3);
        }

        if (playerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_4.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis4);
        }

        if (playerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_5.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis5);
        }

        if (playerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_6.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis6);
        }

        if (playerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_7.getArea())) {
            GameObject CanifisObstacle7 = playerHelper.nearestGameObject(10, AgilityData.obstacleInfo.canifis7.getId());
            if (!CanifisObstacle7.inViewport()) {
                mMain.state = "Moving to obstacle 7";
                Movement.step(AgilityData.CanifisAreas.OBSTACLE_7_MOVE_TO.getArea().getRandomTile());
            } else {
                AgilityHelper helper = new AgilityHelper();
                helper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis7);
            }
        }

        if (playerHelper.withinArea(AgilityData.CanifisAreas.OBSTACLE_8.getArea())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityData.obstacleInfo.canifis8);
        }
    }
}
