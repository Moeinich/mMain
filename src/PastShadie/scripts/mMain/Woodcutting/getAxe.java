package src.PastShadie.scripts.mMain.Woodcutting;

import org.powbot.api.rt4.walking.model.Skill;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

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