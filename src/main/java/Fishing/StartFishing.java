package Fishing;

import org.powbot.mobile.script.ScriptManager;


import java.util.ArrayList;

import Helpers.Task;

public class StartFishing {
    private ArrayList<Task> fishingTasks = new ArrayList<>();
    public void Fishing() {
        if (fishingTasks.isEmpty()) {
            fishingTasks.add(new GetFishingEquipment());
            fishingTasks.add(new DepositFish());
            fishingTasks.add(new FishBanking());
            fishingTasks.add(new GoFishing());
            fishingTasks.add(new DoFishing());
        }

        for (Task task : fishingTasks) {
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