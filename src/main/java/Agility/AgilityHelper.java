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
import Helpers.SkillData;
import script.mMain;

public class AgilityHelper {
    int CurrentXP = Skills.experience(Skill.Agility);

    public void handleObstacle(int obstacleID, String interaction, String interactionName, String state) {
        GameObject obstacleObject = Objects.stream().within(10).id(obstacleID).nearest().first();
        if (obstacleObject.inViewport()) {
            mMain.State = state;
            if (obstacleObject.interact(interaction, interactionName)) {
                Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.CanifisFloorArea.contains(Players.local())), 400, 50);
            }
        }
    }
    public void handleObstacleWithLoot(int obstacleID, String interaction, String interactionName, String state, String lootName) {
        GroundItem groundItem = GroundItems.stream().within(5).name(lootName).nearest().first();
        if (groundItem.inViewport()) {
            PlayerHelper playerHelper = new PlayerHelper();
            mMain.State = "Pickup mark";
            playerHelper.LootItems("Take", "Mark of grace");
        }

        GameObject obstacleObject = Objects.stream().within(10).id(obstacleID).nearest().first();
        if (obstacleObject.inViewport()) {
            mMain.State = state;
            if (obstacleObject.interact(interaction, interactionName)) {
                Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.CanifisFloorArea.contains(Players.local())), 400, 50);
            }
        }
    }
}
