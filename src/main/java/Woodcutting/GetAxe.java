package Woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import org.powbot.dax.api.DaxWalker;
import org.powbot.dax.teleports.Teleport;

import Helpers.InteractionsHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class GetAxe extends Task {

    @Override
    public boolean activate() {
        return Game.tab(Game.Tab.INVENTORY) && Inventory.stream().id(SkillData.withdrawAxe()).isEmpty();
    }
    @Override
    public void execute() {
        if (Bank.nearest().tile().distanceTo(Players.local()) > 5) {
            mMain.State = "Get/Upgrade axe - GoToBank";
            DaxWalker.blacklistTeleports(Teleport.CASTLE_WARS_MINIGAME, Teleport.SOUL_WARS_MINIGAME, Teleport.CLAN_WARS_MINIGAME);
            DaxWalker.walkToBank();
        }
        if (!Bank.opened()) {
            mMain.State = "Get/Upgrade axe - Withdraw";
            if (Bank.open()) {
                InteractionsHelper interactionsHelper = new InteractionsHelper();
                interactionsHelper.DepositAndWithdraw(SkillData.withdrawAxe(), 1);
                Bank.close();
                Condition.wait( () -> !Bank.opened(), 250, 50);
            }
        }
    }
}