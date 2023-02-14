package Agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class StartAgility {
    private ArrayList<Task> agilityTasks = new ArrayList<>();
    public void Agility() {
        mMain.RunningSkill = "Agility";
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 70) {
            mMain.State = "Agility done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        if (agilityTasks.isEmpty()) {
            agilityTasks.add(new WalkToAgilityObstacles());
            agilityTasks.add(new GnomeCourse());
            agilityTasks.add(new DraynorCourse());
            agilityTasks.add(new VarrockCourse());
            agilityTasks.add(new CanifisCourse());
        }

        for (Task task : agilityTasks) {
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
