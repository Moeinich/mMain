package Cooking;

import Helpers.Task;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

public class StartCooking {
    private ArrayList<Task> cookingTasks = new ArrayList<>();
    public void Cooking() {
        if (cookingTasks.isEmpty()) {
            cookingTasks.add(new DepositFish());
            cookingTasks.add(new NoFishBanked());
            cookingTasks.add(new WithdrawFish());
            cookingTasks.add(new CookingBanking());
            cookingTasks.add(new GoCooking());
            cookingTasks.add(new DoCooking());
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
