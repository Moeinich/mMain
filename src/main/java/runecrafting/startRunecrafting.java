package runecrafting;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import helpers.SkillData;
import helpers.extentions.Task;
import hunter.MuseumQuiz;
import script.mMain;

public class startRunecrafting {
    public void Runecrafting() {
        mMain.runningSkill = "runecrafting";
        List<Task> runecraftingTasks = Arrays.asList(
                new MuseumQuiz()
        );

        for (Task task : runecraftingTasks) {
            if (Skills.realLevel(Constants.SKILLS_RUNECRAFTING) >= 9 || SkillData.skillsMap.get("runecrafting")) {
                mMain.state = "Runecrafting done!";
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
