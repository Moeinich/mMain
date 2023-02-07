package Agility;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Camera;
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

public class VarrockCourse extends Task {
    int CurrentXP = Skills.experience(Skill.Agility);

    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) >= 30 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 80;
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
            mMain.State = "Enable run..";
            Widgets.widget(160).component(29).click();
            Condition.wait( () -> Movement.running(), 150, 50);
        }

        if (SkillData.VarrockStart.contains(Players.local())) {
            GameObject VarrockObstacle1 = Objects.stream().within(7).id(14412).nearest().first();
            if (VarrockObstacle1.inViewport()) {
                mMain.State = "Handle obstacle 1";
                CurrentXP = Skills.experience(Skill.Agility);
                if (VarrockObstacle1.interact("Climb", "Rough wall")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 400, 50);
                }
            }
        }

        if (SkillData.VarrockObstacle2.contains(Players.local())) {
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                mMain.State = "Pickup mark";
                LootMarks();
            }
            GameObject VarrockObstacle2 = Objects.stream().within(7).id(14413).nearest().first();
            if (VarrockObstacle2.inViewport()) {
                mMain.State = "Handle obstacle 2";
                CurrentXP = Skills.experience(Skill.Agility);
                if (VarrockObstacle2.interact("Cross", "Clothes line")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 400, 50);
                }
            }
        }

        if (SkillData.VarrockObstacle3.contains(Players.local())) {
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                LootMarks();
            }
            GameObject VarrockObstacle3 = Objects.stream().within(10).id(14414).nearest().first();
            if (VarrockObstacle3.inViewport()) {
                mMain.State = "Handle obstacle 3";
                CurrentXP = Skills.experience(Skill.Agility);
                if (VarrockObstacle3.interact("Leap", "Gap")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 400, 50);
                }
            }
        }

        if (SkillData.VarrockObstacle4.contains(Players.local())) {
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                LootMarks();
            }
            GameObject VarrockObstacle4 = Objects.stream().within(7).id(14832).nearest().first();
            if (VarrockObstacle4.inViewport()) {
                mMain.State = "Handle obstacle 4";
                CurrentXP = Skills.experience(Skill.Agility);
                if (VarrockObstacle4.interact("Balance", "Wall")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 400, 50);
                }
            }
        }

        if (SkillData.VarrockObstacle5.contains(Players.local())) {
            GameObject VarrockObstacle5 = Objects.stream().within(10).id(14833).nearest().first();
            if (VarrockObstacle5.inViewport()) {
                mMain.State = "Handle obstacle 5";
                CurrentXP = Skills.experience(Skill.Agility);
                if (VarrockObstacle5.interact("Leap", "Gap")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 400, 50);
                }
            }
        }

        if (SkillData.VarrockObstacle6.contains(Players.local())) {
            GroundItem groundItem = GroundItems.stream().within(20).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                mMain.State = "Pickup mark";
                LootMarks();
            }
            GameObject VarrockObstacle6 = Objects.stream().within(10).id(14834).nearest().first();
            if (!VarrockObstacle6.inViewport()) {
                mMain.State = "Moving to obstacle 6";
                Movement.step(SkillData.VarrockObstacle6MoveTo.getRandomTile());
            }

            if (VarrockObstacle6.inViewport()) {
                mMain.State = "Handle obstacle 6";
                CurrentXP = Skills.experience(Skill.Agility);
                if (VarrockObstacle6.interact("Leap", "Gap")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 400, 50);
                }
            }
        }

        if (SkillData.VarrockObstacle7.contains(Players.local())) {
            GameObject VarrockObstacle7 = Objects.stream().within(20).id(14835).nearest().first();
            if (!VarrockObstacle7.inViewport()) {
                mMain.State = "Moving to obstacle 7";
                Movement.step(SkillData.VarrockObstacle7MoveTo.getRandomTile());
            }
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                LootMarks();
            }
            if (VarrockObstacle7.inViewport()) {
                mMain.State = "Handle obstacle 7";
                CurrentXP = Skills.experience(Skill.Agility);
                if (VarrockObstacle7.interact("Leap", "Gap")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 400, 50);
                }
            }
        }

        if (SkillData.VarrockObstacle8.contains(Players.local())) {
            GroundItem groundItem = GroundItems.stream().within(10).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                LootMarks();
            }
            GameObject VarrockObstacle8 = Objects.stream().within(10).id(14836).nearest().first();
            if (VarrockObstacle8.inViewport()) {
                mMain.State = "Handle obstacle 8";
                CurrentXP = Skills.experience(Skill.Agility);
                if (VarrockObstacle8.interact("Hurdle", "Ledge")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 400, 50);
                }
            }
        }

        if (SkillData.VarrockObstacle9.contains(Players.local())) {
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                LootMarks();
            }
            GameObject VarrockObstacle9 = Objects.stream().within(10).id(14841).nearest().first();
            if (VarrockObstacle9.inViewport()) {
                mMain.State = "Handle obstacle 9";
                CurrentXP = Skills.experience(Skill.Agility);
                if (VarrockObstacle9.interact("Jump-off", "Edge")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 400, 50);
                }
            }
        }

        GameObject VarrockObstacle1 = Objects.stream().within(7).id(14412).nearest().first();
        if (!VarrockObstacle1.inViewport() && SkillData.VarrockFailArea.contains(Players.local())) {
            mMain.State = "Move to Varrock start";
            Movement.moveTo(SkillData.VarrockStart.getRandomTile());
        }
    }
}
