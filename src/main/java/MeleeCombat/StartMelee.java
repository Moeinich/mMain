package MeleeCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import Helpers.bankBeforeTask;
import Helpers.eatFood;
import Helpers.skillData;
import Helpers.Task;
import Helpers.getFood;
import Woodcutting.DropLogs;
import Woodcutting.GetAxe;
import Woodcutting.treeNormal;
import Woodcutting.treeOak;
import Woodcutting.treeTeak;
import Woodcutting.treeWillow;
import script.mMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartMelee {
    public void Melee() {
        mMain.runningSkill = "Melee";
        if (Skills.realLevel(Constants.SKILLS_STRENGTH) >= 30 && Skills.realLevel(Constants.SKILLS_ATTACK) >= 30 && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 30) {
            mMain.state = "Melee done!";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> meleeTasks = Arrays.asList(
                new bankBeforeTask(),
                new getEquipment(),
                new getFood(),
                new eatFood(),
                new setAttackStyle(),
                new Goblins(),
                new Cows()
        );

        for (Task task : meleeTasks) {
            if (task.activate()) {
                task.execute();
                if (ScriptManager.INSTANCE.isStopping()) {
                    ScriptManager.INSTANCE.stop();
                    break;
                }
                return;
            }
        }
    }
}
