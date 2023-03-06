package hunter;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import helpers.SkillData;
import helpers.extentions.Task;
import helpers.questTasks.DoMuseumQuiz;
import script.mMain;

public class StartHunter {
    public void Hunter() {
        mMain.runningSkill = "hunter";
        List<Task> huntingTasks = Arrays.asList(
                new DoMuseumQuiz()
        );

        for (Task task : huntingTasks) {
            if (Skills.realLevel(Constants.SKILLS_HUNTER) >= 9 || SkillData.skillsMap.get("hunter")) {
                mMain.state = "Hunter done!";
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
