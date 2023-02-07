package Mining;


import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.Task;
import script.mMain;

public class StartMining {
    private ArrayList<Task> miningTasks = new ArrayList<>();
    public void Mining() {
        if (miningTasks.isEmpty()) {
            mMain.RunningSkill = "Mining";
            miningTasks.add(new GetPickaxe());
            miningTasks.add(new DropOres());
            miningTasks.add(new CopperOres());
            miningTasks.add(new IronOres());
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
