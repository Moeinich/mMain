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
    String marksName = "Mark of grace";
    enum Obstacle {
        OBS_1(14843, "Climb", "Tall tree", "OBS1"),
        OBS_2(14844, "Jump", "Gap", "OBS2"),
        OBS_3(14845, "Jump", "Gap", "OBS3"),
        OBS_4(14848, "Jump", "Gap", "OBS4"),
        OBS_5(14846, "Jump", "Gap", "OBS5"),
        OBS_6(14894, "Vault", "Pole-vault", "OBS&"),
        OBS_7(14847, "Jump", "Gap", "OBS7"),
        OBS_8(14897, "Jump", "Gap", "OBS8");

        private final int id;
        private final String action;
        private final String name;
        private final String state;

        Obstacle(int id, String action, String name, String state) {
            this.id = id;
            this.action = action;
            this.name = name;
            this.state = state;
        }

        public int getId() {
            return id;
        }

        public String getAction() {
            return action;
        }
        public String getState() {
            return state;
        }

        public String getName() {
            return name;
        }
    }

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
            GameObject CanifisObstacle1 = Objects.stream().within(8).id(Obstacle.OBS_1.getId()).nearest().first();
            if (!CanifisObstacle1.inViewport()) {
                mMain.State = "Move to Canifis start";
                Movement.moveTo(SkillData.CanifisStart.getRandomTile());
            } else {
                AgilityHelper helper = new AgilityHelper();
                helper.handleObstacle(Obstacle.OBS_1.getId(), Obstacle.OBS_1.getAction(), Obstacle.OBS_1.name, "Handle " + Obstacle.OBS_1.getState());
            }
        } else if (SkillData.CanifisObstacle1Bug.contains(Players.local())) {
            mMain.State = "Stuck due to RS bug";
            Movement.moveTo(SkillData.CanifisObstacle2MoveTo.getRandomTile());
        }

        if (SkillData.CanifisObstacle2.contains(Players.local())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(Obstacle.OBS_2.getId(), Obstacle.OBS_2.getAction(), Obstacle.OBS_2.name, "Handle " + Obstacle.OBS_2.getState(), marksName);
        }

        if (SkillData.CanifisObstacle3.contains(Players.local())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(Obstacle.OBS_3.getId(), Obstacle.OBS_3.getAction(), Obstacle.OBS_3.name, "Handle " + Obstacle.OBS_2.getState(), marksName);
        }

        if (SkillData.CanifisObstacle4.contains(Players.local())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(Obstacle.OBS_4.getId(), Obstacle.OBS_4.getAction(), Obstacle.OBS_4.name, "Handle " + Obstacle.OBS_4.getState(), marksName);
        }

        if (SkillData.CanifisObstacle5.contains(Players.local())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(Obstacle.OBS_5.getId(), Obstacle.OBS_5.getAction(), Obstacle.OBS_5.name, "Handle " + Obstacle.OBS_5.getState(), marksName);
        }

        if (SkillData.CanifisObstacle6.contains(Players.local())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(Obstacle.OBS_6.getId(), Obstacle.OBS_6.getAction(), Obstacle.OBS_6.name, "Handle " + Obstacle.OBS_6.getState(), marksName);
        }

        if (SkillData.CanifisObstacle7.contains(Players.local())) {
            GameObject CanifisObstacle7 = Objects.stream().within(10).id(14847).nearest().first();
            if (!CanifisObstacle7.inViewport()) {
                mMain.State = "Moving to obstacle 7";
                Movement.step(SkillData.CanifisObstacle7MoveTo.getRandomTile());
            } else {
                AgilityHelper helper = new AgilityHelper();
                helper.handleObstacleWithLoot(Obstacle.OBS_7.getId(), Obstacle.OBS_7.getAction(), Obstacle.OBS_7.name, "Handle " + Obstacle.OBS_6.getState(), marksName);
            }
        }

        if (SkillData.CanifisObstacle8.contains(Players.local())) {
            AgilityHelper helper = new AgilityHelper();
            helper.handleObstacleWithLoot(Obstacle.OBS_8.getId(), Obstacle.OBS_8.getAction(), Obstacle.OBS_8.name, "Handle " + Obstacle.OBS_8.getState(), marksName);
        }
    }
}
