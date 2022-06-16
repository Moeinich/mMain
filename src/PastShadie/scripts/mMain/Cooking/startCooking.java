package src.PastShadie.scripts.mMain.Cooking;

import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

public class startCooking {
    private ArrayList<Task> cookingTasks = new ArrayList<>();
    public void Cooking() {
        if (cookingTasks.isEmpty()) {
            cookingTasks.add(new depositFish());
            cookingTasks.add(new withdrawFish());
            cookingTasks.add(new cookingBanking());
            cookingTasks.add(new doCooking());
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
