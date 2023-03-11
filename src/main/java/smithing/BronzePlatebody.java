package smithing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import helpers.InteractionsHelper;
import helpers.extentions.ItemList;
import helpers.PlayerHelper;
import helpers.extentions.Task;
import script.mMain;

public class BronzePlatebody extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_SMITHING) >= 18 && Skills.realLevel(Constants.SKILLS_SMITHING) <= 32;
    }

    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().name("Bronze bar").count() <= 2 || Inventory.stream().name("Hammer").isEmpty()) {
            ShouldBank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().name("Bronze bar").count() >= 5) {
            ShouldSmith();
        }
        return false;
    }
    private void ShouldBank() {
        mMain.state = "Get bars";
        if (!PlayerHelper.withinArea(SmithingData.varrockWestBank)) {
            Movement.step(SmithingData.varrockWestBankTile);
        }
        if (PlayerHelper.withinArea(SmithingData.varrockWestBank)) {
            if (!Bank.opened() && Bank.inViewport()) {
                Bank.open();
            }
            if (Inventory.stream().name("Hammer").isEmpty()) {
                System.out.print("Getting hammer");
                InteractionsHelper.depositAndWithdraw(ItemList.HAMMER_2347, 1);
            }
            else if (Inventory.stream().name("Bronze bar").count() < 5) {
                System.out.print("Getting bronze bars");
                Bank.depositAllExcept("Hammer", "Bronze bar");
                InteractionsHelper.withdrawItem(ItemList.BRONZE_BAR_2349, 27);
                Bank.close();
            }
        }
    }
    private void ShouldSmith() {
        mMain.state = "Smith platebodies";
        if (!PlayerHelper.atTile(SmithingData.smithingTile)) {
            Movement.step(SmithingData.smithingTile);
            Condition.wait( () -> SmithingData.smithingTile.equals(Players.local().tile()), 250, 10);
        }
        if (PlayerHelper.atTile(SmithingData.smithingTile)) {
            GameObject anvil = PlayerHelper.nearestGameObject(1, 2097);
            InteractionsHelper.interactWithGameobject(ItemList.BRONZE_BAR_2349, anvil, 312, 22, "Smith", 5);
        }
    }
}