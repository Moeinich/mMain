package src.PastShadie.scripts.mMain.Mining;

import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.mobile.script.ScriptManager;
import src.PastShadie.scripts.mMain.Assets.doBanking;

import java.util.ArrayList;

public class startMining {
    private ArrayList<Task> taskList = new ArrayList<>();
    public void Mining() {
        if (taskList.isEmpty()) {
            taskList.add(new depositOre());
            taskList.add(new getPickaxe());
            taskList.add(new doBanking());
            taskList.add(new goMining());
            taskList.add(new doMining());
        }
        if(Inventory.stream().id(ItemList.BRONZE_PICKAXE_1265, ItemList.STEEL_PICKAXE_1269, ItemList.MITHRIL_PICKAXE_1273, ItemList.ADAMANT_PICKAXE_1271, ItemList.RUNE_PICKAXE_1275).count() == 0) {
            System.out.println("Theres no pickaxe!");
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
