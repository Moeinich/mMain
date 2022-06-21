package src.PastShadie.scripts.mMain.Smithing;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

public class depositProducts extends Task {
    @Override
    public boolean activate() {
        if (Bank.opened() && Inventory.stream().id(skillData.smithingBars).count() >= 1 && skillData.edgevilleBank.contains(Players.local().tile())) {
            return true;
        }
        if (Bank.opened() && Inventory.stream().id(skillData.bronzeWarhammer).count() >= 1 && skillData.varrockWestBank.contains(Players.local().tile())) {
            return true;
        }
        return false;
    }


    @Override
    public void execute() {
        mMain.scriptStatus = "Deposit products";
        Bank.depositInventory();
    }
}