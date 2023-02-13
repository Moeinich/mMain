package Agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.ItemList;
import Helpers.PlayerHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class CanifisCourse_test extends Task {

    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) >= 40 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 80;
    }
    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().action("Eat").isEmpty()) {
            mMain.State = "Get food";
            PlayerHelper playerHelper = new PlayerHelper();
            playerHelper.BankForFood(ItemList.CAKE_1891, 27);
        }

        if (Skills.level(Constants.SKILLS_HITPOINTS) < 5 && Game.tab(Game.Tab.INVENTORY)) {
            mMain.State = "Eating..";
            PlayerHelper playerHelper = new PlayerHelper();
            playerHelper.ShouldEat();
        }

        if (!Movement.running() && Movement.energyLevel() > 30) {
            PlayerHelper.EnableRun();
        }

        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().id(ItemList.CAKE_1891, ItemList._23_CAKE_1893, ItemList.SLICE_OF_CAKE_1895).isNotEmpty()) {
            ShouldRunObstacle();
        }
        return false;
    }

    public void ShouldRunObstacle() {
        if (SkillData.CanifisFloorArea.contains(Players.local())) {
            GameObject CanifisObstacle1 = Objects.stream().within(8).id(AgilityHelper.Canifis.OBS_1.getId()).nearest().first();
            if (!CanifisObstacle1.inViewport()) {
                mMain.State = "Move to Canifis start";
                Movement.moveTo(SkillData.CanifisStart.getRandomTile());
            } else {
                AgilityHelper helper = new AgilityHelper();
                helper.handleObstacle(AgilityHelper.Canifis.OBS_1.getId(), AgilityHelper.Canifis.OBS_1.getAction(), AgilityHelper.Canifis.OBS_1.getName(), "Handle " + AgilityHelper.Canifis.OBS_1.getState());
            }
        } else if (SkillData.CanifisObstacle1Bug.contains(Players.local())) {
            mMain.State = "Stuck due to RS bug";
            Movement.moveTo(SkillData.CanifisObstacle2MoveTo.getRandomTile());
        }

        if (SkillData.CanifisObstacle2.contains(Players.local())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityHelper.Canifis.OBS_2.getId(), AgilityHelper.Canifis.OBS_2.getAction(), AgilityHelper.Canifis.OBS_2.getName(), "Handle " + AgilityHelper.Canifis.OBS_2.getState(), AgilityHelper.marksName);
        }

        if (SkillData.CanifisObstacle3.contains(Players.local())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityHelper.Canifis.OBS_3.getId(), AgilityHelper.Canifis.OBS_3.getAction(), AgilityHelper.Canifis.OBS_3.getName(), "Handle " + AgilityHelper.Canifis.OBS_2.getState(), AgilityHelper.marksName);
        }

        if (SkillData.CanifisObstacle4.contains(Players.local())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityHelper.Canifis.OBS_4.getId(), AgilityHelper.Canifis.OBS_4.getAction(), AgilityHelper.Canifis.OBS_4.getName(), "Handle " + AgilityHelper.Canifis.OBS_4.getState(), AgilityHelper.marksName);
        }

        if (SkillData.CanifisObstacle5.contains(Players.local())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityHelper.Canifis.OBS_5.getId(), AgilityHelper.Canifis.OBS_5.getAction(), AgilityHelper.Canifis.OBS_5.getName(), "Handle " + AgilityHelper.Canifis.OBS_5.getState(), AgilityHelper.marksName);
        }

        if (SkillData.CanifisObstacle6.contains(Players.local())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityHelper.Canifis.OBS_6.getId(), AgilityHelper.Canifis.OBS_6.getAction(), AgilityHelper.Canifis.OBS_6.getName(), "Handle " + AgilityHelper.Canifis.OBS_6.getState(), AgilityHelper.marksName);
        }

        if (SkillData.CanifisObstacle7.contains(Players.local())) {
            GameObject CanifisObstacle7 = Objects.stream().within(10).id(14847).nearest().first();
            if (!CanifisObstacle7.inViewport()) {
                mMain.State = "Moving to obstacle 7";
                Movement.step(SkillData.CanifisObstacle7MoveTo.getRandomTile());
            } else {
                AgilityHelper helper = new AgilityHelper();
                helper.handleObstacleWithLoot(AgilityHelper.Canifis.OBS_7.getId(), AgilityHelper.Canifis.OBS_7.getAction(), AgilityHelper.Canifis.OBS_7.getName(), "Handle " + AgilityHelper.Canifis.OBS_6.getState(), AgilityHelper.marksName);
            }
        }

        if (SkillData.CanifisObstacle8.contains(Players.local())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(AgilityHelper.Canifis.OBS_8.getId(), AgilityHelper.Canifis.OBS_8.getAction(), AgilityHelper.Canifis.OBS_8.getName(), "Handle " + AgilityHelper.Canifis.OBS_8.getState(), AgilityHelper.marksName);
        }
    }
}
