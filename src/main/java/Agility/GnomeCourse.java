package Agility;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.SkillData;
import Helpers.Task;

public class GnomeCourse extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_FLETCHING) <= 9;
    }
    @Override
    public void execute() {
        GameObject GnomeObstacle1ID = Objects.stream().within(5).id(23145).nearest().first();
        GameObject GnomeObstacle2ID = Objects.stream().within(5).id(23134).nearest().first();
        if (SkillData.GnomeObstacle1Area.contains(Players.local()) && GnomeObstacle1ID.inViewport()) {
            GnomeObstacle1ID.interact("Walk-across", "Log balance");
            Condition.wait( () -> SkillData.GnomeObstacle2Area.contains(Players.local()), 500, 50);
        }

        GameObject GnomeObstacle3ID = Objects.stream().within(5).id(23559).nearest().first();
        if (SkillData.GnomeObstacle2Area.contains(Players.local()) && GnomeObstacle2ID.inViewport()) {
            GnomeObstacle1ID.interact("Climb-over", "Obstacle net");
            Condition.wait( () -> SkillData.GnomeObstacle3Area.contains(Players.local()), 500, 50);
        }

        GameObject GnomeObstacle4ID = Objects.stream().within(5).id(23557).nearest().first();
        if (SkillData.GnomeObstacle3Area.contains(Players.local()) && GnomeObstacle3ID.inViewport()) {
            GnomeObstacle1ID.interact("Climb", "Tree branch");
            Condition.wait( () -> SkillData.GnomeObstacle4Area.contains(Players.local()), 500, 50);
        }

        GameObject GnomeObstacle5ID = Objects.stream().within(5).id(23560).nearest().first();
        if (SkillData.GnomeObstacle4Area.contains(Players.local()) && GnomeObstacle4ID.inViewport()) {
            GnomeObstacle1ID.interact("Walk-on", "Balacing rope");
            Condition.wait( () -> SkillData.GnomeObstacle5Area.contains(Players.local()), 500, 50);
        }

        GameObject GnomeObstacle6ID = Objects.stream().within(5).id(23135).nearest().first();
        if (SkillData.GnomeObstacle5Area.contains(Players.local()) && GnomeObstacle5ID.inViewport()) {
            GnomeObstacle1ID.interact("Climb-down", "Tree branch");
            Condition.wait( () -> SkillData.GnomeObstacle6Area.contains(Players.local()), 500, 50);
        }

        GameObject GnomeObstacle7ID = Objects.stream().within(5).id(23138).nearest().first();
        if (SkillData.GnomeObstacle6Area.contains(Players.local()) && GnomeObstacle6ID.inViewport()) {
            GnomeObstacle1ID.interact("Climb-over", "Obstacle net");
            Condition.wait( () -> SkillData.GnomeObstacle7Area.contains(Players.local()), 500, 50);
        }
        if (SkillData.GnomeObstacle7Area.contains(Players.local()) && GnomeObstacle7ID.inViewport()) {
            GnomeObstacle1ID.interact("Squeeze-through", "Obstacle pipe");
            Condition.wait( () -> GnomeObstacle1ID.inViewport(), 500, 50);
            Movement.moveTo(SkillData.GnomeObstacle1Area.getRandomTile());
        }
    }
}
