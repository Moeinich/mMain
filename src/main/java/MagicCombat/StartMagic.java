package MagicCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class StartMagic {
    private ArrayList<Task> MagicTasks = new ArrayList<>();
    public void Magic() {
        if (Skills.realLevel(Constants.SKILLS_MAGIC) >= 30) {
            mMain.State = "Magic done!";
            SkillData.SetSkillDone();
            mMain.taskRunning.set(false);
        }

        mMain.RunningSkill = "Magic";
        if (MagicTasks.isEmpty()) {
            MagicTasks.add(new CowSafespot());
        }

        for (Task task : MagicTasks) {
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