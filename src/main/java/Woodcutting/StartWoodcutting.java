package Woodcutting;

import Helpers.Task;
import script.mMain;

import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

public class StartWoodcutting {
    private ArrayList<Task> woodcuttingTasks = new ArrayList<>();
    public void Woodcutting() {
        mMain.RunningSkill = "Woodcutting";
        if (woodcuttingTasks.isEmpty()) {
            woodcuttingTasks.add(new WoodcuttingDone());
            woodcuttingTasks.add(new GetAxe());
            woodcuttingTasks.add(new DropLogs());
            woodcuttingTasks.add(new treeNormal());
            woodcuttingTasks.add(new treeOak());
            woodcuttingTasks.add(new treeWillow());
            woodcuttingTasks.add(new treeTeak());
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