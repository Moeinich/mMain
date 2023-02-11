package RangedCombat;

import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.Task;
import Helpers.EatFood;
import script.mMain;

public class StartRanged {
    private ArrayList<Task> rangedTask = new ArrayList<>();
    public void Ranged() {
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

