package src.PastShadie.scripts.mMain.Combat;

import org.powbot.mobile.script.ScriptManager;
import src.PastShadie.scripts.mMain.Assets.Task;

import java.util.ArrayList;

public class startCombat {
    private ArrayList<Task> combatTask = new ArrayList<>();
    public void Combat() {
        if (combatTask.isEmpty()) {
            combatTask.add(new eatFood());
            combatTask.add(new setAttackMode());
            combatTask.add(new drinkPotion());
            combatTask.add(new goCombat());
            combatTask.add(new fightEnemy());
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
