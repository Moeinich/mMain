package Thieving;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Helpers.skillData;
import Helpers.Task;
import Helpers.bankBeforeTask;
import Woodcutting.DropLogs;
import Woodcutting.GetAxe;
import Woodcutting.treeNormal;
import Woodcutting.treeOak;
import Woodcutting.treeTeak;
import Woodcutting.treeWillow;
import script.mMain;

public class StartThieving {
    public void Thieving() {
        mMain.runningSkill = "Thieving";
        if (Skills.realLevel(Constants.SKILLS_THIEVING) >= 60) {
            mMain.state = "Thieving done!";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> thievingTasks = Arrays.asList(
                new bankBeforeTask(),
                new ThievingMen(),
                new TeaStall(),
                new FruitStall()
        );

        for (Task task : thievingTasks) {
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
