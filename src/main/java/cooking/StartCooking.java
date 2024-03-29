package cooking;

import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

public class StartCooking implements mMain.Start {
    public void run() {
        mMain.runningSkill = "cooking";
        List<Task> cookingTasks = Arrays.asList(
                new Sardines(),
                new Wines()
        );

        for (Task task : cookingTasks) {
            if (Skills.realLevel(Constants.SKILLS_COOKING) >= 70 || SkillData.skillsMap.get("cooking")) {
                mMain.state = "Cooking done!";
                SkillData.setSkillDone();
                mMain.skillRunning.set(false);
            }
            else if (task.activate()) {
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
