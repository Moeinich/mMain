package Firemaking;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.skillData;
import Helpers.Task;
import script.mMain;

public class StartFiremaking {
    private ArrayList<Task> firemakingTasks = new ArrayList<>();
    public void Firemaking() {
        mMain.runningSkill = "Firemaking";
        if (Skills.realLevel(Constants.SKILLS_FIREMAKING) >= 50) {
            mMain.state = "Firemaking done!";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        if (firemakingTasks.isEmpty()) {
            firemakingTasks.add(new getLogs());
            firemakingTasks.add(new DoFiremaking());
        }

        for (Task task : firemakingTasks) {
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
