package Herblore;

import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.GoToBank;
import Helpers.Task;
import script.mMain;

public class StartHerblore {
    private ArrayList<Task> HerbloreTasks = new ArrayList<>();
    public void Herblore() {
        mMain.RunningSkill = "Herblore";
        if (HerbloreTasks.isEmpty()) {
            HerbloreTasks.add(new HerbloreDone());
            HerbloreTasks.add(new GoToBank());
            HerbloreTasks.add(new AttackPotions());
        }

        for (Task task : HerbloreTasks) {
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
