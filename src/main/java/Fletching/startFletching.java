package Fletching;

import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.Task;

public class startFletching {
    private ArrayList<Task> fletchingTasks = new ArrayList<>();
    public void Fletching() {
        if (fletchingTasks.isEmpty()) {
            fletchingTasks.add(new DoArrowShafts());
            fletchingTasks.add(new DoLongbow());
            fletchingTasks.add(new DoOakShortbow());
            fletchingTasks.add(new DoOakLongbow());
            fletchingTasks.add(new DoWillowShortbow());
            fletchingTasks.add(new DoWillowLongbow());
            fletchingTasks.add(new DoMapleShortbow());
            fletchingTasks.add(new DoMapleLongbow());
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
