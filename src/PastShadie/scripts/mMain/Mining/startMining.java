package src.PastShadie.scripts.mMain.Mining;

import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

public class startMining {
    private ArrayList<Task> taskList = new ArrayList<>();
    public void Mining() {
        if (taskList.isEmpty()) {
            taskList.add(new getPickaxe());
            taskList.add(new depositOre());
            taskList.add(new miningBanking());
            taskList.add(new goMining());
            taskList.add(new doMining());
        }

        for (Task task : taskList) {
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
