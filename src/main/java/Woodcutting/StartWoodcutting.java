package Woodcutting;

import Helpers.Task;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

public class StartWoodcutting {
    private ArrayList<Task> woodcuttingTasks = new ArrayList<>();
    public void Woodcutting() {
        if (woodcuttingTasks.isEmpty()) {
            woodcuttingTasks.add(new GetAxe());
            woodcuttingTasks.add(new DepositLogs());
            woodcuttingTasks.add(new WoodcuttingBanking());
            woodcuttingTasks.add(new GoWoodcutting());
            woodcuttingTasks.add(new DoWoodcutting());
        }

        for (Task task : woodcuttingTasks) {
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