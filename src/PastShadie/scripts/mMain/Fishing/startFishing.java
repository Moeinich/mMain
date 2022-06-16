package src.PastShadie.scripts.mMain.Fishing;

import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

public class startFishing {
    private ArrayList<Task> fishingTasks = new ArrayList<>();
    public void Fishing() {
        if (fishingTasks.isEmpty()) {
            fishingTasks.add(new getFishingEquipment());
            fishingTasks.add(new depositFish());
            fishingTasks.add(new fishBanking());
            fishingTasks.add(new goFishing());
            fishingTasks.add(new doFishing());
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
