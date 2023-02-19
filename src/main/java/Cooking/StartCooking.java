package Cooking;

import Helpers.goToBank;
import Helpers.skillData;
import Helpers.Task;
import Herblore.AttackPotions;
import script.mMain;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartCooking {
    public void Cooking() {
        mMain.runningSkill = "Cooking";
        if (Skills.realLevel(Constants.SKILLS_COOKING) >= 70 || skillData.cookingDone) {
            mMain.state = "Cooking done!";
            skillData.setSkillDone();
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
