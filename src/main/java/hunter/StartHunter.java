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

public class StartHunter implements mMain.Start{
    public void run() {
        mMain.runningSkill = "hunter";
        List<Task> huntingTasks = Arrays.asList(
                new DoMuseumQuiz()
                //new BirdTrapping()
        );

        for (Task task : huntingTasks) {
            if (Skills.realLevel(Constants.SKILLS_HUNTER) >= 9 || SkillData.skillsMap.get("hunter")) {
                mMain.state = "Hunter done!";
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
