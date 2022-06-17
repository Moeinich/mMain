package src.PastShadie.scripts.mMain.Cooking;

import org.powbot.api.rt4.Constants;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class withdrawFish extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && Inventory.isEmpty();
    }
    @Override
    public void execute() {
        int amountToWithdraw = 28;
        if (Constants.SKILLS_COOKING <= 14) {
            Bank.withdraw(ItemList.RAW_SHRIMPS_317, amountToWithdraw);
        }
        if (Constants.SKILLS_COOKING >= 15 && Constants.SKILLS_COOKING <= 24) {
            Bank.withdraw(ItemList.RAW_TROUT_335, amountToWithdraw);
        }
        if (Constants.SKILLS_COOKING > 25) {
            Bank.withdraw(ItemList.RAW_SALMON_331, amountToWithdraw);
        }
    }
}