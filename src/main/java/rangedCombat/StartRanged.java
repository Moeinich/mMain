package rangedCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import helpers.tasks.BankBeforeTask;
import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

public class StartRanged {
    public void Ranged() {
        mMain.runningSkill = "ranged";
        List<Task> rangeTasks = Arrays.asList(
                new BankBeforeTask(),
                new GetRangeEquipment(),
                new CowSafespot(),
                new CrabsRanged()
        );

        for (Task task : rangeTasks) {
            if (Skills.realLevel(Constants.SKILLS_RANGE) >= 30 || SkillData.skillsMap.get("ranged")) {
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

