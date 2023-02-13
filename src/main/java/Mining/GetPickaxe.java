package Mining;


import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.dax.api.DaxWalker;

import Helpers.InteractionsHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class GetPickaxe extends Task {
    @Override
    public boolean activate() {
return Game.tab(Game.Tab.INVENTORY) && Inventory.stream().id(SkillData.pickaxes).isEmpty();}

    @Override
    public boolean execute() {
        if (Skills.realLevel(Constants.SKILLS_MINING) >= 70) {
            mMain.State = "Mining done!";
            SkillData.SetSkillDone();
            mMain.taskRunning.set(false);
        }

        if (Bank.nearest().tile().distanceTo(Players.local()) > 5) {
            mMain.State = "Moving to bank";
            DaxWalker.walkToBank();
        }
        if (!Bank.opened()) {
            mMain.State = "Get pickaxe - Withdraw";
            if (Bank.open()) {
                InteractionsHelper interactionsHelper = new InteractionsHelper();
                interactionsHelper.DepositAndWithdraw(SkillData.withdrawPickaxe(), 1);
                Bank.close();
                Condition.wait( () -> !Bank.opened(), 250, 50);
            }
        }
        return false;
    }
}