package src.PastShadie.scripts.mMain.Mining;

import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

public class startMining {
    private ArrayList<Task> miningTasks = new ArrayList<>();
    public void Mining() {
        if (miningTasks.isEmpty()) {
            miningTasks.add(new getPickaxe());
            miningTasks.add(new depositOre());
            miningTasks.add(new miningBanking());
            miningTasks.add(new goMining());
            miningTasks.add(new doMining());
        }

        for (Task task : miningTasks) {
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
