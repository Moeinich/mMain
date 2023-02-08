package Thieving;

import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.Task;
import script.mMain;

public class StartThieving {
    private ArrayList<Task> thievingTasks = new ArrayList<>();
    public void Thieving() {
        mMain.RunningSkill = "Thieving";
        if (thievingTasks.isEmpty()) {
            thievingTasks.add(new ShouldBank());
            thievingTasks.add(new ThievingMen());
            thievingTasks.add(new TeaStall());
            thievingTasks.add(new FruitStall());
            thievingTasks.add(new ThievingDone());
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
