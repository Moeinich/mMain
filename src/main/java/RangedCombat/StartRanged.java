package RangedCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Helpers.bankBeforeTask;
import Helpers.skillData;
import Helpers.Task;
import script.mMain;

public class StartRanged {
    public void Ranged() {
        mMain.runningSkill = "Ranged";
        if (Skills.realLevel(Constants.SKILLS_RANGE) >= 30 || skillData.rangeCombatDone) {
            mMain.state = "Range done!";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> rangeTasks = Arrays.asList(
                new bankBeforeTask(),
                new CowSafespot()
        );

        for (Task task : rangeTasks) {
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

