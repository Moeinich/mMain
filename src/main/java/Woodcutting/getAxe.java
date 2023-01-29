package Woodcutting;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Assets.Task;
import Assets.skillData;
import script.mMain;

public class getAxe extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && Inventory.stream().id(skillData.wcAxes).count() == 0;
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Getting an axe";
        int amountToWithdraw = 1;

        Bank.depositInventory();
        Bank.withdraw(skillData.withdrawAxe(), amountToWithdraw);
    }
}