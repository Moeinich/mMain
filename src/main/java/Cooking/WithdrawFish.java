package Cooking;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Helpers.ItemList;
import Helpers.Task;
import script.mMain;

public class WithdrawFish extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && Inventory.isEmpty();
    }
    @Override
    public void execute() {

        int amountToWithdraw = 28;
        if (Skills.realLevel(Constants.SKILLS_COOKING) <= 34) {
            Bank.withdraw(ItemList.RAW_SARDINE_327, amountToWithdraw);
        }
    }
}