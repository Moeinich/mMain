package Cooking;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

public class StartCooking {
    private ArrayList<Task> cookingTasks = new ArrayList<>();
    public void Cooking() {
        mMain.RunningSkill = "Cooking";
        if (Skills.realLevel(Constants.SKILLS_COOKING) >= 70) {
            mMain.State = "Cooking done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        if (cookingTasks.isEmpty()) {
            cookingTasks.add(new CookingDone());
            cookingTasks.add(new Sardines());
            cookingTasks.add(new Wines());
        }

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
