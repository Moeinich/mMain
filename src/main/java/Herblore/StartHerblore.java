package Herblore;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import Helpers.GoToBank;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class StartHerblore {
    public void Herblore() {
        mMain.runningSkill = "Herblore";
        if (Skills.realLevel(Constants.SKILLS_HERBLORE) >= 70 || SkillData.herbloreDone) {
            mMain.state = "Herblore done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> herbloreTasks = Arrays.asList(
                new GoToBank(),
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
