package Smithing;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

public class StartSmithing {
    private ArrayList<Task> smithingTasks = new ArrayList<>();
    public void Smithing() {
        mMain.runningSkill = "Smithing";
        if (Skills.realLevel(Constants.SKILLS_SMITHING) >= 70) {
            mMain.state = "Smithing done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        if (smithingTasks.isEmpty()) {
            smithingTasks.add(new GoSmithing());
            smithingTasks.add(new bronzeDagger());
            smithingTasks.add(new bronzeScimitar());
            smithingTasks.add(new bronzePlatebody());
            smithingTasks.add(new ironPlatebody());
        }

        for (Task task : smithingTasks) {
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
