package src.PastShadie.scripts.mMain.Cooking;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.mobile.script.ScriptManager;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class noFishBanked extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && Inventory.isEmpty();
    }

    @Override
    public void execute() {
        if (Bank.stream().id(ItemList.RAW_SHRIMPS_317).count() == 0) {
            ScriptManager.INSTANCE.stop();
        }
        if (Bank.stream().id(ItemList.RAW_TROUT_335).count() == 0) {
            ScriptManager.INSTANCE.stop();
        }
        if (Bank.stream().id(ItemList.RAW_SALMON_331).count() == 0) {
            ScriptManager.INSTANCE.stop();
        }
    }
}