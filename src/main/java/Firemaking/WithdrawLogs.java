package Firemaking;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

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
        int amountToWithdraw = 27;
        
        if (Bank.stream().id(ItemList.LOGS_1511).count() > 27 || Bank.stream().id(ItemList.OAK_LOGS_1521).count() > 27 || Bank.stream().id(ItemList.WILLOW_LOGS_1519).count() > 27) {
            Bank.withdraw(SkillData.withdrawLogs(), amountToWithdraw);
        } mMain.taskRunning.set(false);
    }
}