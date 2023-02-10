package Agility;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

import Helpers.PlayerHelper;
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
            PlayerHelper.EnableRun();
        } else ShouldRunObstacle();
        }
    public void ShouldRunObstacle() {
        if (SkillData.GnomeObstacle1Area.contains(Players.local())) {
            GameObject GnomeObstacle1ID = Objects.stream().within(20).id(23145).nearest().first();
            if (GnomeObstacle1ID.inViewport()) {
                mMain.State = "Handle obstacle 1";
                CurrentXP = Skills.experience(Skill.Agility);
                if (GnomeObstacle1ID.interact("Walk-across", "Log balance")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility)), 400, 50);
                }
            }
        }

        if (SkillData.GnomeObstacle2Area.contains(Players.local())) {
            GameObject GnomeObstacle2ID = Objects.stream().within(5).id(23134).nearest().first();
            if (GnomeObstacle2ID.inViewport()) {
                mMain.State = "Handle obstacle 2";
                CurrentXP = Skills.experience(Skill.Agility);
                if (GnomeObstacle2ID.interact("Climb-over", "Obstacle net")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility)), 400, 50);
                }
            }
        }
        if (SkillData.GnomeObstacle3Area.contains(Players.local())) {
            GameObject GnomeObstacle3ID = Objects.stream().within(5).id(23559).nearest().first();
            if (GnomeObstacle3ID.inViewport()) {
                mMain.State = "Handle obstacle 3";
                CurrentXP = Skills.experience(Skill.Agility);
                if (GnomeObstacle3ID.interact("Climb", "Tree branch")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility)), 400, 50);
                }
            }
        }

        if (SkillData.GnomeObstacle4Area.contains(Players.local())) {
            GameObject GnomeObstacle4ID = Objects.stream().within(5).id(23557).nearest().first();
            if (GnomeObstacle4ID.inViewport()) {
                mMain.State = "Handle obstacle 4";
                CurrentXP = Skills.experience(Skill.Agility);
                if (GnomeObstacle4ID.interact("Walk-on", "Balancing rope")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility)), 400, 50);
                }
            }
        }

        if (SkillData.GnomeObstacle5Area.contains(Players.local())) {
            GameObject GnomeObstacle5ID = Objects.stream().within(5).id(23560).nearest().first();
            if (GnomeObstacle5ID.inViewport()) {
                mMain.State = "Handle obstacle 5";
                CurrentXP = Skills.experience(Skill.Agility);
                if (GnomeObstacle5ID.interact("Climb-down", "Tree branch")){
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility)), 400, 50);
                }
            }
        }

        if (SkillData.GnomeObstacle6Area.contains(Players.local())) {
            GameObject GnomeObstacle6ID = Objects.stream().within(10).id(23135).nearest().first();
            if (GnomeObstacle6ID.inViewport()) {
                mMain.State = "Handle obstacle 6";
                CurrentXP = Skills.experience(Skill.Agility);
                if (GnomeObstacle6ID.interact("Climb-over", "Obstacle net")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility)), 400, 50);
                    int sleep = Random.nextInt(400, 600);
                    Condition.sleep(sleep);
                }
            }
        }

        if (SkillData.GnomeObstacle7Area.contains(Players.local())) {
            GameObject GnomeObstacle7ID = Objects.stream().within(20).id(23138).nearest().first();
            if (GnomeObstacle7ID.inViewport()) {
                mMain.State = "Handle obstacle 7";
                CurrentXP = Skills.experience(Skill.Agility);
                if (GnomeObstacle7ID.interact("Squeeze-through", "Obstacle pipe")) {
                    Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility)), 400, 50);
                }
            }
        }
    }
}
