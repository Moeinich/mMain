package Firemaking;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class StartFiremaking {
    public void Firemaking() {
        mMain.runningSkill = "Firemaking";
        List<Task> firemakingTasks = Arrays.asList(
                new GetLogs(),
                new DoFiremaking()
        );

        for (Task task : firemakingTasks) {
            if (Skills.realLevel(Constants.SKILLS_FIREMAKING) >= 50 || SkillData.firemakingDone) {
                mMain.state = "Firemaking done!";
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
