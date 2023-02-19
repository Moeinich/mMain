package Mining;


import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Helpers.skillData;
import Helpers.Task;
import Woodcutting.DropLogs;
import Woodcutting.GetAxe;
import Woodcutting.treeNormal;
import Woodcutting.treeOak;
import Woodcutting.treeTeak;
import Woodcutting.treeWillow;
import script.mMain;

public class StartMining {
    public void Mining() {
        mMain.runningSkill = "Mining";
        if (Skills.realLevel(Constants.SKILLS_MINING) >= 70 || skillData.miningDone) {
            mMain.state = "Mining done!";
            skillData.setSkillDone();
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
