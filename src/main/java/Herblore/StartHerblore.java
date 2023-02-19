package Herblore;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Helpers.goToBank;
import Helpers.skillData;
import Helpers.Task;
import Woodcutting.DropLogs;
import Woodcutting.GetAxe;
import Woodcutting.treeNormal;
import Woodcutting.treeOak;
import Woodcutting.treeTeak;
import Woodcutting.treeWillow;
import script.mMain;

public class StartHerblore {
    public void Herblore() {
        mMain.runningSkill = "Herblore";
        if (Skills.realLevel(Constants.SKILLS_HERBLORE) >= 70 || skillData.herbloreDone) {
            mMain.state = "Herblore done!";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> herbloreTasks = Arrays.asList(
                new goToBank(),
                new AttackPotions()
        );

        for (Task task : herbloreTasks) {
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
