package src.PastShadie.scripts.mMain.Smithing;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

public class depositProducts extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && Inventory.stream().id(skillData.bars).count() >= 1;
    }


    @Override
    public void execute() {
        mMain.scriptStatus = "Deposit products";
        Bank.depositInventory();
    }
}