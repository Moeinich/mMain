package Mining;


import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.Task;

public class StartMining {
    private ArrayList<Task> miningTasks = new ArrayList<>();
    public void Mining() {
        if (miningTasks.isEmpty()) {
            miningTasks.add(new GetPickaxe());
            miningTasks.add(new DepositOre());
            miningTasks.add(new MiningBanking());
            miningTasks.add(new GoMining());
            miningTasks.add(new DoMining());
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
