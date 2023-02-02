package Smithing;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;

import Helpers.Task;
import Helpers.skillData;
import script.mMain;

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
        mMain.State = "Deposit products";
        Bank.depositInventory();
    }
}