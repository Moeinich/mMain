package Woodcutting;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Helpers.Task;
import Helpers.skillData;
import script.mMain;

public class StartWoodcutting {
    public void Woodcutting() {
        mMain.runningSkill = "Woodcutting";
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 70 || skillData.woodcuttingDone) {
            mMain.state = "Woodcutting done!";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> woodcuttingTasks = Arrays.asList(
                new GetAxe(),
                new DropLogs(),
                new treeNormal(),
                new treeOak(),
                new treeWillow(),
                new treeTeak()
        );

        for (Task task : woodcuttingTasks) {
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
