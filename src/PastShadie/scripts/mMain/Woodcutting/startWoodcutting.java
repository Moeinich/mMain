package src.PastShadie.scripts.mMain.Woodcutting;

import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

public class startWoodcutting {
    private ArrayList<Task> woodcuttingTasks = new ArrayList<>();
    public void Woodcutting() {
        if (woodcuttingTasks.isEmpty()) {
            woodcuttingTasks.add(new getAxe());
            woodcuttingTasks.add(new depositLogs());
            woodcuttingTasks.add(new woodcuttingBanking());
            woodcuttingTasks.add(new goWoodcutting());
            woodcuttingTasks.add(new doWoodcutting());
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
