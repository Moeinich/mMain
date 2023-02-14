package Agility;

import org.powbot.api.Condition;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.GroundItem;
import org.powbot.api.rt4.GroundItems;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

import Helpers.PlayerHelper;
import script.mMain;

public class AgilityHelper {
    int CurrentXP = Skills.experience(Skill.Agility);

    public void handleObstacle(IObstacleInfo obstacleInfo) {
        GameObject obstacleObject = Objects.stream().within(10).id(obstacleInfo.getId()).nearest().first();
        if (obstacleObject.inViewport()) {
            mMain.State = obstacleInfo.getDescription();
            if (obstacleObject.interact(obstacleInfo.getAction(), obstacleInfo.getName())) {
                Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || Players.local().healthBarVisible()), 400, 50);
            }
        }
    }

    public void handleObstacleWithLoot(IObstacleInfo obstacleInfo) {
        GroundItem groundItem = GroundItems.stream().within(5).name(obstacleInfo.getLoot()).nearest().first();
        if (groundItem.inViewport()) {
            PlayerHelper playerHelper = new PlayerHelper();
            mMain.State = "Pickup mark";
            playerHelper.lootItems("Take", "Mark of grace");
        }

        GameObject obstacleObject = Objects.stream().within(10).id(obstacleInfo.getId()).nearest().first();
        if (obstacleObject.inViewport()) {
            mMain.State = obstacleInfo.getDescription();
            if (obstacleObject.interact(obstacleInfo.getAction(), obstacleInfo.getName())) {
                Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || Players.local().healthBarVisible()), 400, 50);
            }
        }
    }

    interface IObstacleInfo  {
        int getId();
        String getAction();
        String getName();
        String getDescription();
        String getLoot();
    }
}
