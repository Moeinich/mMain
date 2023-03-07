package fishing;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;


import java.util.Arrays;
import java.util.List;

import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

public class StartFishing implements mMain.Start{
    public void start() {
        mMain.runningSkill = "fishing";
        List<Task> fishingTasks = Arrays.asList(
                new GetFishingEquipment(),
                new DropFish(),
                new AlKharidFishing(),
                new BarbarianVillageFishing()
        );

        for (Task task : fishingTasks) {
            if (Skills.realLevel(Constants.SKILLS_FISHING) >= 70 || SkillData.skillsMap.get("fishing")) {
                mMain.state = "Fishing done!";
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