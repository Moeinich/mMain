package agility;

import org.powbot.api.Condition;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.GroundItem;
import org.powbot.api.rt4.GroundItems;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

import helpers.PlayerHelper;
import helpers.extentions.Conditions;
import script.mMain;

public class AgilityHelper {

    public static void handleObstacle(IObstacleInfo obstacleInfo) {
        GameObject obstacleObject = PlayerHelper.nearestGameObject(10, obstacleInfo.getId());
        if (obstacleObject.inViewport()) {
            mMain.state = obstacleInfo.getDescription();
            if (obstacleObject.interact(obstacleInfo.getAction(), obstacleInfo.getName())) {
                Condition.wait( () -> (Conditions.Companion.expGained(Skill.Agility).call() || Players.local().healthBarVisible()), 700, 10);
                System.out.println("Healthbar visible or we gained xp");
            }
        }
    }

    public static void handleObstacleWithLoot(IObstacleInfo obstacleInfo) {
        GroundItem groundItem = GroundItems.stream().within(10).name("Mark of grace").reachable().nearest().first();
        if (groundItem.inViewport()) {
            mMain.state = "Pickup mark";
            PlayerHelper.lootItems("Take", "Mark of grace");
        }

        GameObject obstacleObject = PlayerHelper.nearestGameObject(15, obstacleInfo.getId());
        if (obstacleObject.inViewport()) {
            mMain.state = obstacleInfo.getDescription();
            if (obstacleObject.interact(obstacleInfo.getAction(), obstacleInfo.getName())) {
                Condition.wait( () -> (Conditions.Companion.expGained(Skill.Agility).call() || Players.local().healthBarVisible()), 700, 10);
                System.out.println("Healthbar visible or we gained xp");
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
