package Woodcutting;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class StartWoodcutting {
    public void Woodcutting() {
        mMain.runningSkill = "Woodcutting";
        List<Task> woodcuttingTasks = Arrays.asList(
                new GetAxe(),
                new DropLogs(),
                new TreeNormal(),
                new TreeOak(),
                new TreeWillow(),
                new TreeTeak()
        );

        for (Task task : woodcuttingTasks) {
            if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 70 || SkillData.woodcuttingDone) {
                mMain.state = "Woodcutting done!";
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
