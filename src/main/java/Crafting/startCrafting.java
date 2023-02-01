package Crafting;

import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Assets.Task;

public class startCrafting {
    private ArrayList<Task> craftingTasks = new ArrayList<>();
    public void Crafting() {
        if (craftingTasks.isEmpty()) {
            craftingTasks.add(new goCraftingArea());
            craftingTasks.add(new doBeerGlass());
        }

        for (Task task : craftingTasks) {
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
