package Fishing;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Helpers.goToBank;
import Helpers.skillData;
import Helpers.Task;
import Herblore.AttackPotions;
import script.mMain;

public class StartFishing {
    public void Fishing() {
        mMain.runningSkill = "Fishing";
        if (Skills.realLevel(Constants.SKILLS_FISHING) >= 70) {
            mMain.state = "Fishing done!";
            skillData.setSkillDone();
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