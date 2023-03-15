package agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import helpers.tasks.EatFood;
import helpers.tasks.GetFood;
import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

public class StartAgility implements mMain.Start {
    public void run() {
        mMain.runningSkill = "agility";
        List<Task> agilityTasks = Arrays.asList(
                new GetFood(),
                new EatFood(),
                new GnomeCourse(),
                new DraynorCourse(),
                new VarrockCourse(),
                new CanifisCourse(),
                new SeersCourse()

        );

        for (Task task : agilityTasks) {
            if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 70 || SkillData.skillsMap.get("agility")) {
                mMain.state = "Agility done!";
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
