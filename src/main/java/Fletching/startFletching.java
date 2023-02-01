package Fletching;

import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Assets.Task;

public class startFletching {
    private ArrayList<Task> fletchingTasks = new ArrayList<>();
    public void Fletching() {
        if (fletchingTasks.isEmpty()) {
            //fletchingTasks.add(new goCraftingArea());
        }

        for (Task task : fletchingTasks) {
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
