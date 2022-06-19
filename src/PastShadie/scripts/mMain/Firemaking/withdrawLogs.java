package src.PastShadie.scripts.mMain.Firemaking;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class withdrawLogs extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && !Inventory.isFull() && Inventory.stream().id(skillData.logs).count() == 0;
    }
    @Override
    public void execute() {
        int amountToWithdraw = 27;
        if (Skills.realLevel(Constants.SKILLS_FIREMAKING) <= 14) {
            Bank.withdraw(ItemList.LOGS_1511, amountToWithdraw);
        }
        if (Skills.realLevel(Constants.SKILLS_FIREMAKING) >= 15 && Skills.realLevel(Constants.SKILLS_COOKING) <= 29) {
            Bank.withdraw(ItemList.OAK_LOGS_1521, amountToWithdraw);
        }
        if (Skills.realLevel(Constants.SKILLS_FIREMAKING) > 30) {
            Bank.withdraw(ItemList.WILLOW_LOGS_1519, amountToWithdraw);
        }
    }
}