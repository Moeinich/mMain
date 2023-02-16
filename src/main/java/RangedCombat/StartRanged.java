package RangedCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.BankBeforeTask;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class StartRanged {
    private ArrayList<Task> rangedTask = new ArrayList<>();
    public void Ranged() {
        mMain.runningSkill = "Ranged";
        if (Skills.realLevel(Constants.SKILLS_RANGE) >= 30) {
            mMain.state = "Range done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        if (rangedTask.isEmpty()) {
            rangedTask.add(new BankBeforeTask());
            rangedTask.add(new CowSafespot());
        }

        for (Task task : rangedTask) {
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

