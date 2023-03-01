package herblore;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import helpers.tasks.GoToBank;
import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

public class StartHerblore {
    public void Herblore() {
        mMain.runningSkill = "herblore";
        List<Task> herbloreTasks = Arrays.asList(
                new GoToBank(),
                new AttackPotions()
        );

        for (Task task : herbloreTasks) {
            if (Skills.realLevel(Constants.SKILLS_HERBLORE) >= 70 || SkillData.skillsMap.get("herblore")) {
                mMain.state = "Herblore done!";
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
