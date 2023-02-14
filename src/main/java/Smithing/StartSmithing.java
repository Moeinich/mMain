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
        mMain.RunningSkill = "Smithing";
        if (Skills.realLevel(Constants.SKILLS_SMITHING) >= 70) {
            mMain.State = "Smithing done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        if (smithingTasks.isEmpty()) {
            smithingTasks.add(new WithdrawMaterials());
            smithingTasks.add(new DepositProducts());
            smithingTasks.add(new SmithingBanking());
            smithingTasks.add(new GoSmithing());
            smithingTasks.add(new DoBars());
            smithingTasks.add(new DoProducts());
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
