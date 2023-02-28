package fletching;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import helpers.tasks.GoToBank;
import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

public class StartFletching {
    public void Fletching() {
        mMain.runningSkill = "fletching";
        List<Task> fletchingTasks = Arrays.asList(
                new GoToBank(),
                new ArrowShafts(),
                new Longbow(),
                new OakShortbow(),
                new OakLongbow(),
                new WillowShortbow(),
                new WillowLongbow(),
                new MapleShortbow(),
                new MapleLongbow()
        );

        for (Task task : fletchingTasks) {
            if (Skills.realLevel(Constants.SKILLS_FLETCHING) >= 70 || SkillData.fletchingDone) {
                mMain.state = "Fletching done!";
                SkillData.setSkillDone();
                mMain.taskRunning.set(false);
            }
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
