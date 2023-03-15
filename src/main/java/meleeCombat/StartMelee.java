package meleeCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import helpers.questTasks.DoPriestInPeril;
import helpers.tasks.BankBeforeTask;
import helpers.tasks.EatFood;
import helpers.tasks.GetFood;
import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

public class StartMelee implements mMain.Start{
    public void run() {
        mMain.runningSkill = "melee";
        List<Task> meleeTasks = Arrays.asList(
                new BankBeforeTask(),
                //new DoPriestInPeril(),
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
                    || SkillData.skillsMap.get("melee")) {
                mMain.state = "Melee done!";
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
