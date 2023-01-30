package Cooking;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Assets.ItemList;
import Assets.Task;
import script.mMain;

public class withdrawFish extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && Inventory.isEmpty();
    }
    @Override
    public void execute() {

        int amountToWithdraw = 28;
        if (Skills.realLevel(Constants.SKILLS_COOKING) <= 14) {
            Bank.withdraw(ItemList.RAW_SHRIMPS_317, amountToWithdraw);
        } mMain.taskRunning.set(false);
        if (Skills.realLevel(Constants.SKILLS_COOKING) >= 15 && Skills.realLevel(Constants.SKILLS_COOKING) <= 24) {
            Bank.withdraw(ItemList.RAW_TROUT_335, amountToWithdraw);
        } mMain.taskRunning.set(false);
        if (Skills.realLevel(Constants.SKILLS_COOKING) > 25) {
            Bank.withdraw(ItemList.RAW_SALMON_331, amountToWithdraw);
        } mMain.taskRunning.set(false);
    }
}