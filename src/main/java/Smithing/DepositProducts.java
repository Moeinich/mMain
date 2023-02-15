package Smithing;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class DepositProducts extends Task {
    @Override
    public boolean activate() {
        if (Bank.opened() && Inventory.stream().id(SkillData.smithingBars).count() >= 1 && SkillData.edgevilleBank.contains(Players.local().tile())) {
            return true;
        }
        if (Bank.opened() && Inventory.stream().id(SkillData.bronzeWarhammer).count() >= 1 && SkillData.varrockWestBank.contains(Players.local().tile())) {
            return true;
        }
        return false;
    }


    @Override
    public boolean execute() {
        mMain.state = "Deposit products";
        Bank.depositInventory();
        return false;
    }
}