package src.PastShadie.scripts.mMain.Combat;

import org.powbot.mobile.script.ScriptManager;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.doBanking;
import src.PastShadie.scripts.mMain.Mining.depositOre;
import src.PastShadie.scripts.mMain.Mining.doMining;
import src.PastShadie.scripts.mMain.Mining.getPickaxe;
import src.PastShadie.scripts.mMain.Mining.goMining;

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
