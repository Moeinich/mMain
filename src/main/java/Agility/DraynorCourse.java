package Agility;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
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

public class DraynorCourse extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) >= 10 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 80;
    }
    @Override
    public void execute() {
        if (Inventory.stream().id(ItemList.CAKE_1891, ItemList._23_CAKE_1893, ItemList.SLICE_OF_CAKE_1895).count() == 0) {
            PlayerHelper playerHelper = new PlayerHelper();
            playerHelper.BankForFood(ItemList.CAKE_1891, 27);
        }

        if (Skills.level(Constants.SKILLS_HITPOINTS) < 5) {
            PlayerHelper playerHelper = new PlayerHelper();
            playerHelper.ShouldEat();
        }

        if (Inventory.stream().id(ItemList.CAKE_1891, ItemList._23_CAKE_1893, ItemList.SLICE_OF_CAKE_1895).count() >= 1) {
            ShouldRunObstacle();
            mMain.State = "Inventory Eat count: " + Inventory.stream().action("Eat").count();
        }
    }
    public void ShouldRunObstacle() {
        GameObject DraynorObstacle1 = Objects.stream().within(10).id(11404).nearest().first();
        if (SkillData.DraynorStart.contains(Players.local()) && DraynorObstacle1.inViewport()) {
            DraynorObstacle1.interact("Climb", "Rough wall");
            Condition.wait( () -> SkillData.DraynorObstacle2.contains(Players.local()), 500, 50);
        }

        GameObject DraynorObstacle2 = Objects.stream().within(10).id(11405).nearest().first();
        if (SkillData.DraynorObstacle2.contains(Players.local()) && DraynorObstacle2.inViewport()) {
            DraynorObstacle2.interact("Cross", "Tightrope");
            Condition.wait( () -> SkillData.DraynorObstacle3.contains(Players.local()), 500, 50);
        }

        GameObject DraynorObstacle3 = Objects.stream().within(10).id(11406).nearest().first();
        if (SkillData.DraynorObstacle3.contains(Players.local()) && DraynorObstacle3.inViewport()) {
            DraynorObstacle3.interact("Cross", "Tightrope");
            Condition.wait( () -> SkillData.DraynorObstacle4.contains(Players.local()), 500, 50);
        }

        GameObject DraynorObstacle4 = Objects.stream().within(10).id(11430).nearest().first();
        if (SkillData.DraynorObstacle4.contains(Players.local()) && DraynorObstacle4.inViewport()) {
            DraynorObstacle4.interact("Balance", "Narrow wall");
            Condition.wait( () -> SkillData.DraynorObstacle5.contains(Players.local()), 500, 50);
        }

        GameObject DraynorObstacle5 = Objects.stream().within(10).id(11630).nearest().first();
        if (SkillData.DraynorObstacle5.contains(Players.local()) && DraynorObstacle5.inViewport()) {
            DraynorObstacle5.interact("Jump-up", "Wall");
            Condition.wait( () -> SkillData.DraynorObstacle6.contains(Players.local()), 500, 50);
        }

        GameObject DraynorObstacle6 = Objects.stream().within(10).id(11631).nearest().first();
        if (SkillData.DraynorObstacle6.contains(Players.local()) && DraynorObstacle6.inViewport()) {
            DraynorObstacle6.interact("Jump", "Gap");
            Condition.wait( () -> SkillData.DraynorObstacle7.contains(Players.local()), 500, 50);
        }

        GameObject DraynorObstacle7 = Objects.stream().within(10).id(11632).nearest().first();
        if (SkillData.DraynorObstacle7.contains(Players.local()) && DraynorObstacle7.inViewport()) {
            DraynorObstacle6.interact("Climb-down", "Crate");
            Condition.wait( () -> SkillData.DraynorFailArea.contains(Players.local()), 500, 50);
        }

        if (SkillData.DraynorFailArea.contains(Players.local())) {
            mMain.State = "Move to start";
            Movement.moveTo(SkillData.DraynorStart.getRandomTile());
            Condition.wait( () -> SkillData.DraynorStart.contains(Players.local()), 500, 50);
        }
    }
}
