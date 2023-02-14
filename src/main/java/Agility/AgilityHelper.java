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

    public void handleObstacle(IObstacleInfo obstacleInfo) {
        GameObject obstacleObject = Objects.stream().within(10).id(obstacleInfo.getId()).nearest().first();
        if (obstacleObject.inViewport()) {
            mMain.State = obstacleInfo.getDescription();
            if (obstacleObject.interact(obstacleInfo.getAction(), obstacleInfo.getName())) {
                Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.CanifisFloorArea.contains(Players.local())), 400, 50);
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
                Condition.wait( () -> (CurrentXP != Skills.experience(Skill.Agility) || SkillData.CanifisFloorArea.contains(Players.local())), 400, 50);
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

    enum obstacleInfo implements IObstacleInfo {
        //Canifis
        canifis1(14843, "Climb", "Tall tree", "Handle: OBS1"),
        canifis2(14844, "Jump", "Gap", "Handle: OBS2"),
        canifis3(14845, "Jump", "Gap", "Handle: OBS3"),
        canifis4(14848, "Jump", "Gap", "Handle: OBS4"),
        canifis5(14846, "Jump", "Gap", "Handle: OBS5"),
        canifis6(14894, "Vault", "Pole-vault", "OHandle: BS6"),
        canifis7(14847, "Jump", "Gap", "Handle: OBS7"),
        canifis8(14897, "Jump", "Gap", "Handle: OBS8");
        private final int id;
        private final String action;
        private final String name;
        private final String state;
        private final String getLoot = "Marks of grace";

        obstacleInfo(int id, String action, String name, String state) {
            this.id = id;
            this.action = action;
            this.name = name;
            this.state = state;
        }

        public int getId() {
            return id;
        }
        public String getLoot() {
            return getLoot;
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

        public String getDescription()  {
            return state;
        }
    }
}
