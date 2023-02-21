package Cooking;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

public class StartCooking {
    public void Cooking() {
        mMain.runningSkill = "Cooking";
        if (Skills.realLevel(Constants.SKILLS_COOKING) >= 70 || SkillData.cookingDone) {
            mMain.state = "Cooking done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> cookingTasks = Arrays.asList(
                new Sardines(),
                new Wines()
        );

        for (Task task : cookingTasks) {
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
