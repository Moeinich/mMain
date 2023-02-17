package Smithing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.InteractionsHelper;
import Helpers.ItemList;
import Helpers.PlayerHelper;
import Helpers.Task;
import script.mMain;

public class ironPlatebody extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_SMITHING) >= 33;
    }

    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().name("Iron bar").count() <= 2 || Inventory.stream().name("Hammer").isEmpty()) {
            ShouldBank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().name("Iron bar").count() >= 5) {
            ShouldSmith();
        }
        return false;
    }
    private void ShouldBank() {
        mMain.state = "Get bars";
        if (!PlayerHelper.withinArea(smithingData.varrockWestBank)) {
            Movement.step(smithingData.varrockWestBankTile);
        }
        if (PlayerHelper.withinArea(smithingData.varrockWestBank)) {
            if (!Bank.opened() && Bank.inViewport()) {
                Bank.open();
            }
            if (Inventory.stream().name("Hammer").isEmpty()) {
                InteractionsHelper.depositAndWithdraw(ItemList.HAMMER_2347, 1);
            } else {
                Bank.depositAllExcept("Hammer", "Iron bar");
                InteractionsHelper.withdrawItem(ItemList.IRON_BAR_2351, 27);
                Bank.close();
            }
        }
    }
    private void ShouldSmith() {
        mMain.state = "Smith platebodies";
        if (!PlayerHelper.withinArea(smithingData.anvilArea)) {
            Movement.step(smithingData.smithingTile);
            Condition.wait( () -> smithingData.smithingTile.equals(Players.local().tile()), 250, 50);
        }
        if (PlayerHelper.withinArea(smithingData.anvilArea)) {
            GameObject anvil = PlayerHelper.nearestGameObject(1, 2097);
            InteractionsHelper.interactWithGameobject(ItemList.IRON_BAR_2351, anvil, 312, 22, "Smith", "Anvil", 5);
        }
    }
}