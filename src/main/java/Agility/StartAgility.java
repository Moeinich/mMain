package Agility;

import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.Task;
import script.mMain;

public class StartAgility {
    private ArrayList<Task> agilityTasks = new ArrayList<>();
    public void Agility() {
        mMain.RunningSkill = "Agility";
        if (agilityTasks.isEmpty()) {
            agilityTasks.add(new WalkToAgilityObstacles());
            agilityTasks.add(new GnomeCourse());
            agilityTasks.add(new DraynorCourse());
            agilityTasks.add(new VarrockCourse());
        }

        for (Task task : agilityTasks) {
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
