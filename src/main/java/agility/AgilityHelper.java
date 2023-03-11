package agility;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Camera;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.GroundItem;
import org.powbot.api.rt4.GroundItems;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

import helpers.PlayerHelper;
import script.mMain;

public class AgilityHelper {

    public static void handleObstacle(IObstacleInfo obstacleInfo) {
        GameObject obstacleObject = PlayerHelper.nearestGameObject(10, obstacleInfo.getId());
        if (obstacleObject.inViewport()) {
            mMain.state = obstacleInfo.getDescription();
            if (obstacleObject.interact(obstacleInfo.getAction(), obstacleInfo.getName())) {
                System.out.println("Clicked obstacle, sleeping");
                int startExp = Skills.experience(Skill.Agility);
                Condition.wait( () -> (startExp != Skills.experience(Skill.Agility) || Players.local().healthBarVisible()), 700, 10);
                System.out.println("Stopped sleeping - We gained xp or failed the obstacle");
            }
        }
    }

    public static void handleObstacleWithLoot(IObstacleInfo obstacleInfo, Integer... distanceToLoot) {
        int distance = distanceToLoot.length > 0 ? distanceToLoot[0] : 10;
        if (PlayerHelper.nearestGroundItem(distance, "Mark of grace").inViewport()) {
            mMain.state = "Pickup mark";
            PlayerHelper.lootItems("Take", "Mark of grace");
        }

        GameObject obstacleObject = PlayerHelper.nearestGameObject(15, obstacleInfo.getId());
        if (obstacleObject.inViewport()) {
            mMain.state = obstacleInfo.getDescription();
            if (obstacleObject.interact(obstacleInfo.getAction(), obstacleInfo.getName())) {
                System.out.println("Clicked obstacle, sleeping");
                int startExp = Skills.experience(Skill.Agility);
                Condition.wait( () -> (startExp != Skills.experience(Skill.Agility) || Players.local().healthBarVisible()), 700, 10);
                System.out.println("Stopped sleeping - We gained xp or failed the obstacle");
            }
        }
    }

    interface IObstacleInfo  {
        int getId();
        String getAction();
        String getName();
        String getDescription();
    }
}
