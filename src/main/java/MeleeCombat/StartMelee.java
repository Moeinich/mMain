package MeleeCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import Helpers.bankBeforeTask;
import Helpers.eatFood;
import Helpers.skillData;
import Helpers.Task;
import Helpers.getFood;
import script.mMain;

import java.util.ArrayList;

public class StartMelee {
    private ArrayList<Task> meleeTask = new ArrayList<>();
    public void Melee() {
        mMain.runningSkill = "Melee";
        if (Skills.realLevel(Constants.SKILLS_STRENGTH) >= 70 && Skills.realLevel(Constants.SKILLS_ATTACK) >= 70 && Skills.realLevel(Constants.SKILLS_DEFENSE) >= 70) {
            mMain.state = "Melee done!";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        if (meleeTask.isEmpty()) {
            meleeTask.add(new bankBeforeTask());
            meleeTask.add(new getEquipment());
            meleeTask.add(new getFood());
            meleeTask.add(new eatFood());
            meleeTask.add(new setAttackStyle());
            meleeTask.add(new Goblins());
            meleeTask.add(new Cows());
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
