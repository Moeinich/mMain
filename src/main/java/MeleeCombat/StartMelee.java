package MeleeCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import Helpers.EatFood;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

import java.util.ArrayList;

public class StartMelee {
    private ArrayList<Task> meleeTask = new ArrayList<>();
    public void Combat() {
        mMain.RunningSkill = "Melee";
        if (Skills.realLevel(Constants.SKILLS_STRENGTH) >= 70 && Skills.realLevel(Constants.SKILLS_ATTACK) >= 70 && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 70) {
            mMain.State = "Melee done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        if (meleeTask.isEmpty()) {
            meleeTask.add(new EatFood());
            meleeTask.add(new SetAttackMode());
            meleeTask.add(new DrinkPotion());
            meleeTask.add(new GoCombat());
            meleeTask.add(new FightEnemy());
        }

        for (Task task : meleeTask) {
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
