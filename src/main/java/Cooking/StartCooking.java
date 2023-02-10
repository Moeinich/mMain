package Cooking;

import Helpers.Task;
import script.mMain;

import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

public class StartCooking {
    private ArrayList<Task> cookingTasks = new ArrayList<>();
    public void Cooking() {
        mMain.RunningSkill = "Cooking";
        if (cookingTasks.isEmpty()) {
            cookingTasks.add(new CookingDone());
            cookingTasks.add(new Sardines());
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
