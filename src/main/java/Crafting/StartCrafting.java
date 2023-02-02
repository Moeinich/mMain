package Crafting;

import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.Task;

public class StartCrafting {
    private ArrayList<Task> craftingTasks = new ArrayList<>();
    public void Crafting() {
        if (craftingTasks.isEmpty()) {
            craftingTasks.add(new GoCraftingArea());
            craftingTasks.add(new DoBeerGlass());
            craftingTasks.add(new DoEmptyOilLamp());
            craftingTasks.add(new DoVial());
            craftingTasks.add(new DoEmptyFishBowl());
            craftingTasks.add(new DoUnpoweredOrb());
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
