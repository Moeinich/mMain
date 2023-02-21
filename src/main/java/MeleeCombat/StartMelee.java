package MeleeCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import Helpers.Task;
import Helpers.EatFood;
import Helpers.GetFood;
import Helpers.SkillData;
import script.mMain;

public class StartMelee {
    public void Melee() {
        mMain.runningSkill = "Melee";
        if (Skills.realLevel(Constants.SKILLS_STRENGTH) >= 40 && Skills.realLevel(Constants.SKILLS_ATTACK) >= 40 && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 40 || SkillData.meleeCombatDone) {
            mMain.state = "Melee done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> meleeTasks = Arrays.asList(
                //new bankBeforeTask(),
                new GetEquipment(),
                new GetFood(),
                new EatFood(),
                new SetAttackStyle(),
                new Goblins(),
                new Cows(),
                new Crabs()
        );

        for (Task task : meleeTasks) {
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
