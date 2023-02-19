package Agility;

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

public class StartAgility {
    public void Agility() {
        mMain.runningSkill = "Agility";
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 70 || skillData.agilityDone) {
            mMain.state = "Agility done!";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> agilityTasks = Arrays.asList(
                new WalkToAgilityObstacles(),
                new GnomeCourse(),
                new DraynorCourse(),
                new VarrockCourse(),
                new CanifisCourse()
        );

        for (Task task : agilityTasks) {
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
