package Smithing;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

public class StartSmithing {
    public void Smithing() {
        mMain.runningSkill = "Smithing";
        if (Skills.realLevel(Constants.SKILLS_SMITHING) >= 70 || SkillData.smithingDone) {
            mMain.state = "Smithing done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> smithingTasks = Arrays.asList(
                new GoSmithing(),
                new BronzeDagger(),
                new BronzeScimitar(),
                new BronzePlatebody(),
                new IronPlatebody(),
                new SteelPlatebody()
        );

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
