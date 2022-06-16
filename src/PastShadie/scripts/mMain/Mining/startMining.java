package src.PastShadie.scripts.mMain.Mining;

import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.mobile.script.ScriptManager;
import src.PastShadie.scripts.mMain.Assets.doBanking;

import java.util.ArrayList;

public class startMining {
    private ArrayList<Task> taskList = new ArrayList<>();
    public void startMining() {
        if (taskList.isEmpty()) {
            System.out.print("Added tasks...");
            taskList.add(new depositOre());
            taskList.add(new getPickaxe());
            taskList.add(new doBanking());
            taskList.add(new goMining());
            taskList.add(new doMining());
        } else {
            System.out.println("starting mining task");
            Mining();
        }
    }
    public void Mining() {
            if(Inventory.stream().name("pickaxe").isEmpty()) {
                taskList.clear();
                taskList.add(new doBanking());
                System.out.print("Do banking...");
            }

            System.out.print("Starting the tasklist function");
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
