package RangedCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.SkillData;
import Helpers.Task;
import Helpers.EatFood;
import script.mMain;

public class StartRanged {
    private ArrayList<Task> rangedTask = new ArrayList<>();
    public void Ranged() {
        if (Skills.realLevel(Constants.SKILLS_RANGE) >= 30) {
            mMain.State = "Range done!";
            SkillData.SetSkillDone();
            mMain.taskRunning.set(false);
        }

        mMain.RunningSkill = "Ranged combat";
        if (rangedTask.isEmpty()) {
            rangedTask.add(new EatFood());
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

