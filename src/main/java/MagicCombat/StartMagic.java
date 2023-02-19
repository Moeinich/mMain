package MagicCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Helpers.bankBeforeTask;
import Helpers.skillData;
import Helpers.Task;
import Woodcutting.DropLogs;
import Woodcutting.GetAxe;
import Woodcutting.treeNormal;
import Woodcutting.treeOak;
import Woodcutting.treeTeak;
import Woodcutting.treeWillow;
import script.mMain;

public class StartMagic {
    public void Magic() {
        mMain.runningSkill = "Magic";
        if (Skills.realLevel(Constants.SKILLS_MAGIC) >= 30 || skillData.magicCombatDone) {
            mMain.state = "Magic done!";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> magicTasks = Arrays.asList(
                new bankBeforeTask(),
                new CowSafespot()
        );

        for (Task task : magicTasks) {
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