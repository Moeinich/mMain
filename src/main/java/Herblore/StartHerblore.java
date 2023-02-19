package Herblore;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.goToBank;
import Helpers.skillData;
import Helpers.Task;
import script.mMain;

public class StartHerblore {
    private ArrayList<Task> HerbloreTasks = new ArrayList<>();
    public void Herblore() {
        mMain.runningSkill = "Herblore";
        if (Skills.realLevel(Constants.SKILLS_HERBLORE) >= 70) {
            mMain.state = "Herblore done!";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        if (HerbloreTasks.isEmpty()) {
            HerbloreTasks.add(new goToBank());
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
