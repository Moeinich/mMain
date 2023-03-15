package smithing;

import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

public class StartSmithing implements mMain.Start{
    public void run() {
        mMain.runningSkill = "smithing";
        List<Task> smithingTasks = Arrays.asList(
                new GoSmithing(),
                new BronzeDagger(),
                new BronzeScimitar(),
                new BronzePlatebody(),
                new IronPlatebody(),
                new SteelPlatebody()
        );

        for (Task task : smithingTasks) {
            if (Skills.realLevel(Constants.SKILLS_SMITHING) >= 70 || SkillData.skillsMap.get("smithing")) {
                mMain.state = "Smithing done!";
                SkillData.setSkillDone();
                mMain.skillRunning.set(false);
            }
            else if (task.activate()) {
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
