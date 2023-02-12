package Fishing;

import org.powbot.mobile.script.ScriptManager;


import java.util.ArrayList;

import Helpers.GoToBank;
import Helpers.Task;
import script.mMain;

public class StartFishing {
    private ArrayList<Task> fishingTasks = new ArrayList<>();
    public void Fishing() {
        mMain.RunningSkill = "Fishing";
        if (fishingTasks.isEmpty()) {
            fishingTasks.add(new FishingDone());
            fishingTasks.add(new GoToBank());
            fishingTasks.add(new GetFishingEquipment());
            fishingTasks.add(new DropFish());
            fishingTasks.add(new AlKharidFishing());
            fishingTasks.add(new BarbarianVillageFishing());
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