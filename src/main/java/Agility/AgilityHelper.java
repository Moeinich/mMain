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
    public static String marksName = "Mark of grace";

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
    enum Canifis {
        OBS_1(14843, "Climb", "Tall tree", "OBS1"),
        OBS_2(14844, "Jump", "Gap", "OBS2"),
        OBS_3(14845, "Jump", "Gap", "OBS3"),
        OBS_4(14848, "Jump", "Gap", "OBS4"),
        OBS_5(14846, "Jump", "Gap", "OBS5"),
        OBS_6(14894, "Vault", "Pole-vault", "OBS&"),
        OBS_7(14847, "Jump", "Gap", "OBS7"),
        OBS_8(14897, "Jump", "Gap", "OBS8");

        private final int id;
        private final String action;
        private final String name;
        private final String state;

        Canifis(int id, String action, String name, String state) {
            this.id = id;
            this.action = action;
            this.name = name;
            this.state = state;
        }

        public int getId() {
            return id;
        }

        public String getAction() {
            return action;
        }
        public String getState() {
            return state;
        }

        public String getName() {
            return name;
        }
    }
}
