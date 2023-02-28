package agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import helpers.EatFood;
import helpers.GetFood;
import helpers.SkillData;
import helpers.Task;
import script.mMain;

public class StartAgility {
    public void Agility() {
        mMain.runningSkill = "agility";
        List<Task> agilityTasks = Arrays.asList(
                new GetFood(),
                new EatFood(),
                new WalkToAgilityObstacles(),
                new GnomeCourse(),
                new DraynorCourse(),
                new VarrockCourse(),
                new CanifisCourse()
        );

        for (Task task : agilityTasks) {
            if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 70 || SkillData.agilityDone) {
                mMain.state = "Agility done!";
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
