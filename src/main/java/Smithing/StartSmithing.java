package Smithing;

import Helpers.Task;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

public class StartSmithing {
    private ArrayList<Task> smithingTasks = new ArrayList<>();
    public void Smithing() {
        if (smithingTasks.isEmpty()) {
            smithingTasks.add(new WithdrawMaterials());
            smithingTasks.add(new DepositProducts());
            smithingTasks.add(new SmithingBanking());
            smithingTasks.add(new GoSmithing());
            smithingTasks.add(new DoBars());
            smithingTasks.add(new DoProducts());
        }

        for (Task task : smithingTasks) {
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
