package Fishing;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;


import java.util.ArrayList;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class StartFishing {
    private ArrayList<Task> fishingTasks = new ArrayList<>();
    public void Fishing() {
        mMain.RunningSkill = "Fishing";
        if (Skills.realLevel(Constants.SKILLS_FISHING) >= 70) {
            mMain.State = "Fishing done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        if (fishingTasks.isEmpty()) {
            fishingTasks.add(new GetFishingEquipment());
            fishingTasks.add(new DropFish());
            fishingTasks.add(new AlKharidFishing());
            fishingTasks.add(new BarbarianVillageFishing());
        }

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