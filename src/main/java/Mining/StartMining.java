package Mining;


import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class StartMining {
    public void Mining() {
        mMain.runningSkill = "Mining";
        if (Skills.realLevel(Constants.SKILLS_MINING) >= 70 || SkillData.miningDone) {
            mMain.state = "Mining done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> miningTasks = Arrays.asList(
                new GetPickaxe(),
                new DropOres(),
                new CopperOres(),
                new IronOres()
        );

        for (Task task : miningTasks) {
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
