package Firemaking;

import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.Task;
import script.mMain;

public class StartFiremaking {
    private ArrayList<Task> firemakingTasks = new ArrayList<>();
    public void Firemaking() {
        mMain.RunningSkill = "Firemaking";
        if (firemakingTasks.isEmpty()) {
            firemakingTasks.add(new WithdrawTinderbox());
            firemakingTasks.add(new WithdrawLogs());
            firemakingTasks.add(new FiremakingBanking());
            firemakingTasks.add(new GoFiremaking());
            firemakingTasks.add(new DoFiremaking());
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
