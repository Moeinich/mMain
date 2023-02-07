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

public class DraynorCourse extends Task {
    int CurrentXP = Skills.experience(Skill.Agility);

    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) >= 10 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 29;
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

        if (Inventory.stream().id(ItemList.CAKE_1891, ItemList._23_CAKE_1893, ItemList.SLICE_OF_CAKE_1895).isNotEmpty()) {
            ShouldRunObstacle();
        }
    }
    public void LootMarks() {
        PlayerHelper playerHelper = new PlayerHelper();
        mMain.State = "Pickup mark";
        playerHelper.LootItems("Take", "Mark of grace");
    }

    public void ShouldRunObstacle() {
        if (!Movement.running() && Movement.energyLevel() > 30) {
            mMain.State = "Enable run..";
            Widgets.widget(160).component(29).click();
            Condition.wait( () -> Movement.running(), 150, 50);
        }

        GameObject DraynorObstacle1 = Objects.stream().within(7).id(11404).nearest().first();
        if (SkillData.DraynorStart.contains(Players.local()) && DraynorObstacle1.inViewport()) {
            mMain.State = "Handle obstacle 1";
            CurrentXP = Skills.experience(Skill.Agility);
            DraynorObstacle1.interact("Climb", "Rough wall");
            Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 800, 50);
        }

        GameObject DraynorObstacle2 = Objects.stream().within(7).id(11405).nearest().first();
        if (SkillData.DraynorObstacle2.contains(Players.local()) && DraynorObstacle2.inViewport()) {
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                mMain.State = "Pickup mark";
                LootMarks();
            }
            mMain.State = "Handle obstacle 2";
            CurrentXP = Skills.experience(Skill.Agility);
            DraynorObstacle2.interact("Cross", "Tightrope");
            Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 800, 50);
        }

        GameObject DraynorObstacle3 = Objects.stream().within(7).id(11406).nearest().first();
        if (SkillData.DraynorObstacle3.contains(Players.local()) && DraynorObstacle3.inViewport()) {
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                LootMarks();
            }
            mMain.State = "Handle obstacle 3";
            CurrentXP = Skills.experience(Skill.Agility);
            DraynorObstacle3.interact("Cross", "Tightrope");
            Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 800, 50);
        }

        GameObject DraynorObstacle4 = Objects.stream().within(7).id(11430).nearest().first();
        if (SkillData.DraynorObstacle4.contains(Players.local()) && DraynorObstacle4.inViewport()) {
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                LootMarks();
            }
            mMain.State = "Handle obstacle 4";
            CurrentXP = Skills.experience(Skill.Agility);
            DraynorObstacle4.interact("Balance", "Narrow wall");
            Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 800, 50);
        }

        GameObject DraynorObstacle5 = Objects.stream().within(7).id(11630).nearest().first();
        if (SkillData.DraynorObstacle5.contains(Players.local()) && DraynorObstacle5.inViewport()) {
            mMain.State = "Handle obstacle 5";
            CurrentXP = Skills.experience(Skill.Agility);
            DraynorObstacle5.interact("Jump-up", "Wall");
            Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 800, 50);
        }

        GameObject DraynorObstacle6 = Objects.stream().within(7).id(11631).nearest().first();
        if (SkillData.DraynorObstacle6.contains(Players.local()) && DraynorObstacle6.inViewport()) {
            mMain.State = "Handle obstacle 6";
            CurrentXP = Skills.experience(Skill.Agility);
            DraynorObstacle6.interact("Jump", "Gap");
            Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 800, 50);
        }

        GameObject DraynorObstacle7 = Objects.stream().within(10).id(11632).nearest().first();
        if (SkillData.DraynorObstacle7.contains(Players.local()) && DraynorObstacle7.inViewport()) {
            GroundItem groundItem = GroundItems.stream().within(5).name("Mark of grace").nearest().first();
            if (groundItem.inViewport()) {
                LootMarks();
            }
            mMain.State = "Handle obstacle 7";
            CurrentXP = Skills.experience(Skill.Agility);
            DraynorObstacle7.interact("Climb-down", "Crate");
            Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.VarrockFailArea.contains(Players.local())), 800, 50);
        }

        if (!DraynorObstacle1.inViewport() && SkillData.DraynorFloorArea.contains(Players.local())) {
            mMain.State = "Move to Draynor start";
            Movement.step(SkillData.DraynorStart.getRandomTile());
        }
    }
}