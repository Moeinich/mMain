package Agility;

import org.powbot.api.Condition;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.GroundItem;
import org.powbot.api.rt4.GroundItems;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

import Helpers.playerHelper;
import script.mMain;

public class AgilityHelper {

    public void handleObstacle(IObstacleInfo obstacleInfo) {
        GameObject obstacleObject = playerHelper.nearestGameObject(10, obstacleInfo.getId());
        if (obstacleObject.inViewport()) {
            mMain.state = obstacleInfo.getDescription();
            if (obstacleObject.interact(obstacleInfo.getAction(), obstacleInfo.getName())) {
                int CurrentXP = Skills.experience(Skill.Agility);
                Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || Players.local().healthBarVisible()), 400, 50);
            }
        }
    }

    public void handleObstacleWithLoot(IObstacleInfo obstacleInfo) {
        GroundItem groundItem = GroundItems.stream().within(10).name("Mark of grace").nearest().first();
        if (groundItem.inViewport()) {
            mMain.state = "Pickup mark";
            playerHelper.lootItems("Take", "Mark of grace");
        }

        GameObject obstacleObject = playerHelper.nearestGameObject(10, obstacleInfo.getId());
        if (obstacleObject.inViewport()) {
            mMain.state = obstacleInfo.getDescription();
            if (obstacleObject.interact(obstacleInfo.getAction(), obstacleInfo.getName())) {
                int CurrentXP = Skills.experience(Skill.Agility);
                Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || Players.local().healthBarVisible()), 400, 50);
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
