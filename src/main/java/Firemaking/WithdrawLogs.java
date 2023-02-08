package Firemaking;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Helpers.InteractionsHelper;
import Helpers.ItemList;
import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class WithdrawLogs extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && !Inventory.isFull() && Inventory.stream().id(SkillData.logs).isEmpty();
    }
    @Override
    public void execute() {
        mMain.State = "Withdraw logs";
        if (Bank.stream().id(ItemList.LOGS_1511).isEmpty() || Bank.stream().id(ItemList.OAK_LOGS_1521).isEmpty() || Bank.stream().id(ItemList.WILLOW_LOGS_1519).isEmpty()) {
            InteractionsHelper interactionsHelper = new InteractionsHelper();
            interactionsHelper.WithdrawItem(SkillData.withdrawLogs(), 27);
        } mMain.taskRunning.set(false);
    }
}