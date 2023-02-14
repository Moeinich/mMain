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
        //Gnome
        gnome1(23145,"Walk-across","Log balance", "Handle: gnome1"),
        gnome2(23134,"Climb-over","Obstacle net", "Handle: gnome2"),
        gnome3(23559,"Climb","Tree branch", "Handle: gnome3"),
        gnome4(23557,"Walk-on","Balancing rope", "Handle: gnome4"),
        gnome5(23560,"Climb-down","Tree branch", "Handle: gnome5"),
        gnome6(23135,"Climb-over","Obstacle net", "Handle: gnome6"),
        gnome7(23138,"Squeeze-through","Obstacle pipe", "Handle: gnome7"),

        //Draynor
        Draynor1(11404,"Climb","Rough wall", "Handle: Draynor1"),
        Draynor2(11405,"Cross","Tightrope", "Handle: Draynor2"),
        Draynor3(11406,"Cross","Tightrope", "Handle: Draynor3"),
        Draynor4(11430,"Balance","Narrow wall", "Handle: Draynor4"),
        Draynor5(11630,"Jump-up","Wall", "Handle: Draynor5"),
        Draynor6(11631,"Jump","Gap", "Handle: Draynor6"),
        Draynor7(11632,"Climb-down","Crate", "Handle: Draynor7"),

        //Varrock
        Varrock1(14412,"Climb","Rough wall", "Handle: Varrock1"),
        Varrock2(14413,"Cross","Clothes line", "Handle: Varrock2"),
        Varrock3(14414,"Leap","Gap", "Handle: Varrock3"),
        Varrock4(14832,"Balance","Wall", "Handle: Varrock4"),
        Varrock5(14833,"Leap","Gap", "Handle: Varrock5"),
        Varrock6(14834,"Leap","Gap", "Handle: Varrock6"),
        Varrock7(14835,"Leap","Gap", "Handle: Varrock7"),
        Varrock8(14836,"Hurdle","Ledge", "Handle: Varrock8"),
        Varrock9(14841,"Jump-off","Edge", "Handle: Varrock9"),

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
