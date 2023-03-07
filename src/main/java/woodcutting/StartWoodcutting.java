package woodcutting;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import helpers.extentions.Task;
import helpers.SkillData;
import script.mMain;

public class StartWoodcutting implements mMain.Start{
    public void start() {
        mMain.runningSkill = "woodcutting";
        List<Task> woodcuttingTasks = Arrays.asList(
                new GetAxe(),
                new DropLogs(),
                new TreeNormal(),
                new TreeOak(),
                new TreeWillow(),
                new TreeTeak()
        );

        for (Task task : woodcuttingTasks) {
            if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 70 || SkillData.skillsMap.get("woodcutting")) {
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
