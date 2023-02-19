package Smithing;

import Helpers.skillData;
import Helpers.Task;
import Woodcutting.DropLogs;
import Woodcutting.GetAxe;
import Woodcutting.treeNormal;
import Woodcutting.treeOak;
import Woodcutting.treeTeak;
import Woodcutting.treeWillow;
import script.mMain;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartSmithing {
    public void Smithing() {
        mMain.runningSkill = "Smithing";
        if (Skills.realLevel(Constants.SKILLS_SMITHING) >= 70 || skillData.smithingDone) {
            mMain.state = "Smithing done!";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> smithingTasks = Arrays.asList(
                new GoSmithing(),
                new bronzeDagger(),
                new bronzeScimitar(),
                new bronzePlatebody(),
                new ironPlatebody(),
                new steelPlatebody()
        );

        for (Task task : smithingTasks) {
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
