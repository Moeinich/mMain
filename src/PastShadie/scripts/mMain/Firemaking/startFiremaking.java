package src.PastShadie.scripts.mMain.Firemaking;

import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

public class startFiremaking {
    private ArrayList<Task> firemakingTasks = new ArrayList<>();
    public void Firemaking() {
        if (firemakingTasks.isEmpty()) {
            //firemakingTasks.add(new getAxe());
        }

        for (Task task : firemakingTasks) {
            System.out.print("We are starting firemaking");
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
