package Mining;


import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class StartMining {
    private ArrayList<Task> miningTasks = new ArrayList<>();
    public void Mining() {
        mMain.runningSkill = "Mining";
        if (Skills.realLevel(Constants.SKILLS_MINING) >= 70) {
            mMain.state = "Mining done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        if (miningTasks.isEmpty()) {
            miningTasks.add(new GetPickaxe());
            miningTasks.add(new DropOres());
            miningTasks.add(new CopperOres());
            miningTasks.add(new IronOres());
        }

        for (Task task : miningTasks) {
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
