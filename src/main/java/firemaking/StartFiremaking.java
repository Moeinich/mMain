package firemaking;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

public class StartFiremaking implements mMain.Start{
    public void run() {
        mMain.runningSkill = "firemaking";
        List<Task> firemakingTasks = Arrays.asList(
                new GetLogs(),
                new DoFiremaking()
        );

        for (Task task : firemakingTasks) {
            if (Skills.realLevel(Constants.SKILLS_FIREMAKING) >= 50 || SkillData.skillsMap.get("firemaking")) {
                mMain.state = "Firemaking done!";
                SkillData.setSkillDone();
                mMain.skillRunning.set(false);
            }
            else if (task.activate()) {
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
