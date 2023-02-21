package Fishing;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;


import java.util.Arrays;
import java.util.List;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class StartFishing {
    public void Fishing() {
        mMain.runningSkill = "Fishing";
        if (Skills.realLevel(Constants.SKILLS_FISHING) >= 70 || SkillData.fishingDone) {
            mMain.state = "Fishing done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> fishingTasks = Arrays.asList(
                new GetFishingEquipment(),
                new DropFish(),
                new AlKharidFishing(),
                new BarbarianVillageFishing()
        );

        for (Task task : fishingTasks) {
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