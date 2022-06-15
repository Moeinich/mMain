package Mining;

import Assets.doBanking;
import Assets.Task;
import org.powbot.api.rt4.Inventory;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

public class startMining {
    private ArrayList<Task> taskList = new ArrayList<>();
    public void startMining() {
        taskList.add(new depositOre());
        taskList.add(new getPickaxe());
        taskList.add(new doBanking());
        taskList.add(new goMining());
        taskList.add(new doMining());

        if(Inventory.stream().name("pickaxe").isEmpty()) {
            //start banking
        }
        for (Task task : taskList) {
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
