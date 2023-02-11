package MeleeCombat;

import org.powbot.mobile.script.ScriptManager;

import Helpers.EatFood;
import Helpers.Task;
import script.mMain;

import java.util.ArrayList;

public class StartMelee {
    private ArrayList<Task> meleeTask = new ArrayList<>();
    public void Combat() {
        mMain.RunningSkill = "Melee combat";
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
