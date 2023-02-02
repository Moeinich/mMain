package Firemaking;

import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.Task;

public class startFiremaking {
    private ArrayList<Task> firemakingTasks = new ArrayList<>();
    public void Firemaking() {
        if (firemakingTasks.isEmpty()) {
            firemakingTasks.add(new withdrawTinderbox());
            firemakingTasks.add(new withdrawLogs());
            firemakingTasks.add(new firemakingBanking());
            firemakingTasks.add(new goFiremaking());
            firemakingTasks.add(new doFiremaking());
        }

        for (Task task : firemakingTasks) {
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
