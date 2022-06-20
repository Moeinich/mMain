package src.PastShadie.scripts.mMain.Firemaking;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

public class withdrawLogs extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && !Inventory.isFull() && Inventory.stream().id(skillData.logs).count() == 0;
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Withdraw logs";
        int amountToWithdraw = 27;
        
        if (Bank.stream().id(ItemList.LOGS_1511).count() > 27 || Bank.stream().id(ItemList.OAK_LOGS_1521).count() > 27 || Bank.stream().id(ItemList.WILLOW_LOGS_1519).count() > 27) {
            Bank.withdraw(skillData.withdrawLogs(), amountToWithdraw);
        } ScriptManager.INSTANCE.stop();
    }
}