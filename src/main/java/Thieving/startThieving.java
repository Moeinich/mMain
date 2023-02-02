package Thieving;

import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.Task;

public class startThieving {
    private ArrayList<Task> thievingTasks = new ArrayList<>();
    public void Thieving() {
        if (thievingTasks.isEmpty()) {
            thievingTasks.add(new thievingMen());
            thievingTasks.add(new teaStall());
            thievingTasks.add(new fruitStall());
            thievingTasks.add(new thievingDone());
        }

        for (Task task : thievingTasks) {
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
