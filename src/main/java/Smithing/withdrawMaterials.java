package Smithing;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Skills;

import Assets.ItemList;
import Assets.Task;
import Assets.skillData;
import script.mMain;

public class withdrawMaterials extends Task {
    @Override
    public boolean activate() {
        return Bank.opened()
                && Inventory.isEmpty();
    }


    @Override
    public void execute() {
        mMain.scriptStatus = "Withdraw materials";

        //Withdraw Ores for bars!
        if (Bank.stream().id(skillData.copperOre).first().stackSize() >= 14 && Bank.stream().id(skillData.tinOre).first().stackSize() >= 14 && Skills.realLevel(Constants.SKILLS_SMITHING) <= 14) {
            Bank.withdraw(skillData.copperOre, 14);
            Bank.withdraw(skillData.tinOre, 14);
        } mMain.taskRunning.set(false);

        if (Bank.stream().id(skillData.ironOre).first().stackSize() >= 14 && Skills.realLevel(Constants.SKILLS_SMITHING) >= 15) {
            Bank.withdraw(skillData.ironOre, 14);
        } mMain.taskRunning.set(false);

        //Withdraw hammer if doing bars.
        if (Bank.stream().id(skillData.bronzeBar).first().stackSize() >= 28 && Bank.stream().id(ItemList.HAMMER_2347).first().stackSize() >= 1) {
            Bank.withdraw(ItemList.HAMMER_2347, 1);
        } mMain.taskRunning.set(false);
        //Withdraw bars for smithing!
        if (Bank.stream().id(skillData.bronzeBar).first().stackSize() >= 28 && Skills.realLevel(Constants.SKILLS_SMITHING) >= 9) {
            Bank.withdraw(skillData.bronzeBar, 28);
        } mMain.taskRunning.set(false);
    }
}