package RangedCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import Helpers.BankBeforeTask;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class StartRanged {
    public void Ranged() {
        mMain.runningSkill = "Ranged";
        List<Task> rangeTasks = Arrays.asList(
                new BankBeforeTask(),
                new CowSafespot()
        );

        for (Task task : rangeTasks) {
            if (Skills.realLevel(Constants.SKILLS_RANGE) >= 30 || SkillData.rangeCombatDone) {
                mMain.state = "Range done!";
                SkillData.setSkillDone();
                mMain.taskRunning.set(false);
            }
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

