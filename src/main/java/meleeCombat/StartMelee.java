package meleeCombat;

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

public class StartMelee {
    public void Melee() {
        mMain.runningSkill = "Melee";
        List<Task> meleeTasks = Arrays.asList(
                //new BankBeforeTask(),
                new GetEquipment(),
                new SetAttackStyle(),
                new GetFood(),
                new EatFood(),
                new Goblins(),
                new Cows(),
                new Crabs()
        );

        for (Task task : meleeTasks) {
            if (Skills.realLevel(Constants.SKILLS_STRENGTH) >= 70
                    && Skills.realLevel(Constants.SKILLS_ATTACK) >= 70
                    && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 70
                    || SkillData.meleeCombatDone) {
                mMain.state = "Melee done!";
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
