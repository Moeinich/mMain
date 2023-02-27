package Hunter;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import Quests.NaturalHistory.MuseumQuiz;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class StartHunter {
    public void Hunter() {
        mMain.runningSkill = "Hunter";
        List<Task> huntingTasks = Arrays.asList(
                new MuseumQuiz()
        );

        for (Task task : huntingTasks) {
            if (Skills.realLevel(Constants.SKILLS_HUNTER) >= 70 || SkillData.huntingDone) {
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
