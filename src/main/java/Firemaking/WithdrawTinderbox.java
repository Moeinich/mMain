package Firemaking;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Helpers.InteractionsHelper;
import Helpers.ItemList;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class WithdrawTinderbox extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && Inventory.stream().id(ItemList.TINDERBOX_590).isEmpty();
    }
    @Override
    public void execute() {
        mMain.State = "Withdraw Tinderbox";
        InteractionsHelper interactionsHelper = new InteractionsHelper();
        interactionsHelper.DepositAndWithdraw(ItemList.TINDERBOX_590, 1);
    }
}