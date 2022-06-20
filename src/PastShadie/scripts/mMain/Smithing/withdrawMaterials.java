package src.PastShadie.scripts.mMain.Smithing;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

public class withdrawMaterials extends Task {
    @Override
    public boolean activate() {
        return Bank.opened()
                && Bank.stream().id(skillData.ores).count() >= 27
                && !Inventory.isFull();
    }


    @Override
    public void execute() {
        mMain.scriptStatus = "Withdraw materials";
        if (Bank.stream().id(skillData.copperOre).count() > 14 && Bank.stream().id(skillData.tinOre).count() > 14 && Skills.realLevel(Constants.SKILLS_SMITHING) <= 14) {
            Bank.withdraw(skillData.copperOre, 14);
            Bank.withdraw(skillData.tinOre, 14);
        } ScriptManager.INSTANCE.stop();
        if (Bank.stream().id(skillData.ironOre).count() > 28 && Skills.realLevel(Constants.SKILLS_SMITHING) >= 15) {
            Bank.withdraw(skillData.ironOre, 14);
        } ScriptManager.INSTANCE.stop();
    }
}