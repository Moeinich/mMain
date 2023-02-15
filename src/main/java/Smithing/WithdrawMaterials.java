package Smithing;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Skills;

import Helpers.ItemList;
import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class WithdrawMaterials extends Task {
    @Override
    public boolean activate() {
        return Bank.opened()
                && Inventory.isEmpty();
    }


    @Override
    public boolean execute() {
        mMain.state = "Withdraw materials";

        //Withdraw Ores for bars!
        if (Bank.stream().id(SkillData.copperOre).first().stackSize() >= 14 && Bank.stream().id(SkillData.tinOre).first().stackSize() >= 14 && Skills.realLevel(Constants.SKILLS_SMITHING) <= 14) {
            Bank.withdraw(SkillData.copperOre, 14);
            Bank.withdraw(SkillData.tinOre, 14);
        }

        if (Bank.stream().id(SkillData.ironOre).first().stackSize() >= 14 && Skills.realLevel(Constants.SKILLS_SMITHING) >= 15) {
            Bank.withdraw(SkillData.ironOre, 14);
        }

        //Withdraw hammer if doing bars.
        if (Bank.stream().id(SkillData.bronzeBar).first().stackSize() >= 28 && Bank.stream().id(ItemList.HAMMER_2347).first().stackSize() >= 1) {
            Bank.withdraw(ItemList.HAMMER_2347, 1);
        }
        //Withdraw bars for smithing!
        if (Bank.stream().id(SkillData.bronzeBar).first().stackSize() >= 28 && Skills.realLevel(Constants.SKILLS_SMITHING) >= 9) {
            Bank.withdraw(SkillData.bronzeBar, 28);
        }
        return false;
    }
}