package Combat;

import org.powbot.mobile.script.ScriptManager;
import Helpers.Task;

import java.util.ArrayList;

public class StartCombat {
    private ArrayList<Task> combatTask = new ArrayList<>();
    public void Combat() {
        if (combatTask.isEmpty()) {
            combatTask.add(new EatFood());
            combatTask.add(new SetAttackMode());
            combatTask.add(new DrinkPotion());
            combatTask.add(new GoCombat());
            combatTask.add(new FightEnemy());
        }

        for (Task task : combatTask) {
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
