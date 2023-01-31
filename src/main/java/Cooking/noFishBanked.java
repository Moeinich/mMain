package Cooking;

import org.powbot.api.rt4.*;
import org.powbot.mobile.script.ScriptManager;

import Assets.ItemList;
import Assets.Task;
import script.mMain;

public class noFishBanked extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && Inventory.isEmpty();
    }

    @Override
    public void execute() {
        mMain.State = "no fish banked!";
        if (Bank.stream().id(ItemList.RAW_SHRIMPS_317).first().stackSize() == 0) {
            ScriptManager.INSTANCE.stop();
        }
        if (Bank.stream().id(ItemList.RAW_TROUT_335).first().stackSize() == 0) {
            ScriptManager.INSTANCE.stop();
        }
        if (Bank.stream().id(ItemList.RAW_SALMON_331).first().stackSize() == 0) {
            ScriptManager.INSTANCE.stop();
        }
    }
}