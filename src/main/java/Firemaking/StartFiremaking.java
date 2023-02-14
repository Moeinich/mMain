package Firemaking;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class StartFiremaking {
    private ArrayList<Task> firemakingTasks = new ArrayList<>();
    public void Firemaking() {
        if (Skills.realLevel(Constants.SKILLS_FIREMAKING) >= 70) {
            mMain.State = "Firemaking done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        mMain.RunningSkill = "Firemaking";
        if (firemakingTasks.isEmpty()) {
            firemakingTasks.add(new FiremakingDone());
            firemakingTasks.add(new GetMaterials());
            firemakingTasks.add(new DoFiremaking());
        }

        for (Task task : firemakingTasks) {
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
