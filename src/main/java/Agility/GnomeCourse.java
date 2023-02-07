package Agility;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Widgets;
import org.powbot.api.rt4.walking.model.Skill;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class GnomeCourse extends Task {
    int CurrentXP = Skills.experience(Skill.Agility);

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_AGILITY) <= 9;
    }
    @Override
    public void execute() {
        if (!Movement.running() && Movement.energyLevel() > 30) {
            mMain.State = "Enable run..";
            Widgets.widget(160).component(29).click();
            Condition.wait( () -> Movement.running(), 150, 50);
        }
        ShouldRunObstacle();
        }
    public void ShouldRunObstacle() {
        GameObject GnomeObstacle1ID = Objects.stream().within(20).id(23145).nearest().first();
        if (SkillData.GnomeObstacle1Area.contains(Players.local()) && GnomeObstacle1ID.inViewport()) {
            mMain.State = "Handle obstacle 1";
            CurrentXP = Skills.experience(Skill.Agility);
            GnomeObstacle1ID.interact("Walk-across", "Log balance");
            Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility)), 800, 50);
        }

        GameObject GnomeObstacle2ID = Objects.stream().within(5).id(23134).nearest().first();
        if (SkillData.GnomeObstacle2Area.contains(Players.local()) && GnomeObstacle2ID.inViewport()) {
            mMain.State = "Handle obstacle 2";
            CurrentXP = Skills.experience(Skill.Agility);
            GnomeObstacle2ID.interact("Climb-over", "Obstacle net");
            Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility)), 800, 50);
        }

        GameObject GnomeObstacle3ID = Objects.stream().within(5).id(23559).nearest().first();
        if (SkillData.GnomeObstacle3Area.contains(Players.local()) && GnomeObstacle3ID.inViewport()) {
            mMain.State = "Handle obstacle 3";
            CurrentXP = Skills.experience(Skill.Agility);
            GnomeObstacle3ID.interact("Climb", "Tree branch");
            Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility)), 800, 50);
        }

        GameObject GnomeObstacle4ID = Objects.stream().within(5).id(23557).nearest().first();
        if (SkillData.GnomeObstacle4Area.contains(Players.local()) && GnomeObstacle4ID.inViewport()) {
            mMain.State = "Handle obstacle 4";
            CurrentXP = Skills.experience(Skill.Agility);
            GnomeObstacle4ID.interact("Walk-on", "Balancing rope");
            Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility)), 800, 50);
        }

        GameObject GnomeObstacle5ID = Objects.stream().within(5).id(23560).nearest().first();
        if (SkillData.GnomeObstacle5Area.contains(Players.local()) && GnomeObstacle5ID.inViewport()) {
            mMain.State = "Handle obstacle 5";
            CurrentXP = Skills.experience(Skill.Agility);
            GnomeObstacle5ID.interact("Climb-down", "Tree branch");
            Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility)), 800, 50);
        }

        GameObject GnomeObstacle6ID = Objects.stream().within(10).id(23135).nearest().first();
        if (SkillData.GnomeObstacle6Area.contains(Players.local()) && GnomeObstacle6ID.inViewport()) {
            mMain.State = "Handle obstacle 6";
            CurrentXP = Skills.experience(Skill.Agility);
            GnomeObstacle6ID.interact("Climb-over", "Obstacle net");
            Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility)), 800, 50);
        }

        GameObject GnomeObstacle7ID = Objects.stream().within(20).id(23138).nearest().first();
        if (SkillData.GnomeObstacle7Area.contains(Players.local()) && GnomeObstacle7ID.inViewport()) {
            mMain.State = "Handle obstacle 7";
            CurrentXP = Skills.experience(Skill.Agility);
            GnomeObstacle7ID.interact("Squeeze-through", "Obstacle pipe");
            Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility)), 800, 50);
        }
    }
}
