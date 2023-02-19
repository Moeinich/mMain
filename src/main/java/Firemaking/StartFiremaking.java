package Firemaking;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Helpers.goToBank;
import Helpers.skillData;
import Helpers.Task;
import Herblore.AttackPotions;
import script.mMain;

public class StartFiremaking {
    public void Firemaking() {
        mMain.runningSkill = "Firemaking";
        if (Skills.realLevel(Constants.SKILLS_FIREMAKING) >= 50 || skillData.firemakingDone) {
            mMain.state = "Firemaking done!";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> firemakingTasks = Arrays.asList(
                new getLogs(),
                new DoFiremaking()
        );

        for (Task task : firemakingTasks) {
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
