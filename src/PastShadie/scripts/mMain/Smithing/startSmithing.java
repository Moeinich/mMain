package src.PastShadie.scripts.mMain.Smithing;

import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

public class startSmithing {
    private ArrayList<Task> smithingTasks = new ArrayList<>();
    public void startSmithing() {
        if (smithingTasks.isEmpty()) {
            smithingTasks.add(new withdrawMaterials());
            smithingTasks.add(new depositProducts());
            smithingTasks.add(new smithingBanking());
            smithingTasks.add(new goSmithing());
            smithingTasks.add(new doProducts());
        }

        for (Task task : smithingTasks) {
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
