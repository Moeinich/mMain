package Agility;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.GroundItem;
import org.powbot.api.rt4.GroundItems;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Widgets;
import org.powbot.api.rt4.walking.model.Skill;

import Helpers.ItemList;
import Helpers.PlayerHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class CanifisCourse extends Task {
    int CurrentXP = Skills.experience(Skill.Agility);

    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) >= 40 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 80;
    }
    @Override
    public void execute() {
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

        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().id(ItemList.CAKE_1891, ItemList._23_CAKE_1893, ItemList.SLICE_OF_CAKE_1895).isNotEmpty()) {
            ShouldRunObstacle();
        }
    }
    public void LootMarks() {
        PlayerHelper playerHelper = new PlayerHelper();
        mMain.State = "Pickup mark";
        playerHelper.LootItems("Take", "Mark of grace");
    }

    public void ShouldRunObstacle() {
        mMain.State = "In obstacle loop";
        if (!Movement.running() && Movement.energyLevel() > 30) {
            PlayerHelper.EnableRun();
        }

        if (SkillData.CanifisObstacle1.contains(Players.local())) {
            GameObject CanifisObstacle1 = Objects.stream().within(10).id(14843).nearest().first();
            if (CanifisObstacle1.inViewport()) {
                mMain.State = "Handle obstacle 1";
                CurrentXP = Skills.experience(Skill.Agility);
                if (CanifisObstacle1.interact("Climb", "Tall tree")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.CanifisObstacle1Bug.contains(Players.local())), 400, 50);
                }
            }
        }

        if (SkillData.CanifisObstacle1Bug.contains(Players.local())) {
            mMain.State = "Stuck due to RS bug";
            Movement.moveTo(SkillData.CanifisObstacle2MoveTo.getRandomTile());
        }

        if (SkillData.CanifisObstacle2.contains(Players.local())) {
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                mMain.State = "Pickup mark";
                LootMarks();
            }
            GameObject CanifisObstacle2 = Objects.stream().within(8).id(14844).nearest().first();
            if (CanifisObstacle2.inViewport()) {
                mMain.State = "Handle obstacle 2";
                CurrentXP = Skills.experience(Skill.Agility);
                if (CanifisObstacle2.interact("Jump", "Gap")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.CanifisFloorArea.contains(Players.local())), 400, 50);
                }
            }
        }

        if (SkillData.CanifisObstacle3.contains(Players.local())) {
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                LootMarks();
            }
            GameObject CanifisObstacle3 = Objects.stream().within(10).id(14845).nearest().first();
            if (CanifisObstacle3.inViewport()) {
                mMain.State = "Handle obstacle 3";
                CurrentXP = Skills.experience(Skill.Agility);
                if (CanifisObstacle3.interact("Jump", "Gap")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.CanifisFloorArea.contains(Players.local())), 400, 50);
                }
            }
        }

        if (SkillData.CanifisObstacle4.contains(Players.local())) {
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                LootMarks();
            }
            GameObject CanifisObstacle4 = Objects.stream().within(10).id(14848).nearest().first();
            if (CanifisObstacle4.inViewport()) {
                mMain.State = "Handle obstacle 4";
                CurrentXP = Skills.experience(Skill.Agility);
                if (CanifisObstacle4.interact("Jump", "Gap")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.CanifisFloorArea.contains(Players.local())), 400, 50);
                }
            }
        }

        if (SkillData.CanifisObstacle5.contains(Players.local())) {
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                LootMarks();
            }
            GameObject CanifisObstacle5 = Objects.stream().within(10).id(14846).nearest().first();
            if (CanifisObstacle5.inViewport()) {
                mMain.State = "Handle obstacle 5";
                CurrentXP = Skills.experience(Skill.Agility);
                if (CanifisObstacle5.interact("Jump", "Gap")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.CanifisFloorArea.contains(Players.local())), 400, 50);
                }
            }
        }

        if (SkillData.CanifisObstacle6.contains(Players.local())) {
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                mMain.State = "Pickup mark";
                LootMarks();
            }
            GameObject CanifisObstacle6 = Objects.stream().within(10).id(14894).nearest().first();
            if (CanifisObstacle6.inViewport()) {
                mMain.State = "Handle obstacle 6";
                CurrentXP = Skills.experience(Skill.Agility);
                if (CanifisObstacle6.interact("Vault", "Pole-vault")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.CanifisFloorArea.contains(Players.local())), 400, 50);
                }
            }
        }

        if (SkillData.CanifisObstacle7.contains(Players.local())) {
            GameObject CanifisObstacle7 = Objects.stream().within(10).id(14847).nearest().first();
            if (!CanifisObstacle7.inViewport()) {
                mMain.State = "Moving to obstacle 7";
                Movement.step(SkillData.CanifisObstacle7MoveTo.getRandomTile());
            }
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                LootMarks();
            }
            if (CanifisObstacle7.inViewport()) {
                mMain.State = "Handle obstacle 7";
                CurrentXP = Skills.experience(Skill.Agility);
                if (CanifisObstacle7.interact("Jump", "Gap")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.CanifisFloorArea.contains(Players.local())), 400, 50);
                }
            }
        }

        if (SkillData.CanifisObstacle8.contains(Players.local())) {
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                LootMarks();
            }
            GameObject CanifisObstacle8 = Objects.stream().within(8).id(14897).nearest().first();
            if (CanifisObstacle8.inViewport()) {
                mMain.State = "Handle obstacle 8";
                CurrentXP = Skills.experience(Skill.Agility);
                if (CanifisObstacle8.interact("Jump", "Gap")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility)), 400, 50);
                }
            }
        }

        GameObject CanifisObstacle1 = Objects.stream().within(8).id(14843).nearest().first();
        if (!CanifisObstacle1.inViewport() && SkillData.CanifisFloorArea.contains(Players.local())) {
            mMain.State = "Move to Canifis start";
            Movement.moveTo(SkillData.CanifisStart.getRandomTile());
        }
    }
}
