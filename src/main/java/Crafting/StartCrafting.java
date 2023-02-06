package Crafting;

import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.GoToBank;
import Helpers.Task;

public class StartCrafting {
    private ArrayList<Task> craftingTasks = new ArrayList<>();
    public void Crafting() {
        if (craftingTasks.isEmpty()) {
            craftingTasks.add(new GoToBank());
            craftingTasks.add(new BeerGlass());
            craftingTasks.add(new EmptyOilLamp());
            craftingTasks.add(new Vial());
            craftingTasks.add(new EmptyFishBowl());
            craftingTasks.add(new UnpoweredOrb());
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
