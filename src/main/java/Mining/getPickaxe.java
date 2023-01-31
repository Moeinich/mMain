package Mining;


import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Assets.Task;
import Assets.skillData;
import script.mMain;

public class getPickaxe extends Task {
    @Override
    public boolean activate() {
return Bank.opened() && Inventory.stream().id(skillData.pickaxes).count() == 0;
    }

    @Override
    public void execute() {
        mMain.State = "Getting pickaxe";
        int amountToWithdraw = 1;
        Bank.depositInventory();
        Bank.withdraw(skillData.withdrawPickaxe(), amountToWithdraw);
        }
}