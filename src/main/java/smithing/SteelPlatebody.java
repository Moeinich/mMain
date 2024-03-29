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

import helpers.extentions.ItemList;
import helpers.extentions.Task;
import helpers.InteractionsHelper;
import helpers.PlayerHelper;
import script.mMain;

public class SteelPlatebody extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_SMITHING) >= 48;
    }

    @Override
    public boolean execute() {
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().name("Steel bar").count() <= 4 || !PlayerHelper.hasItem("Hammer")) {
            ShouldBank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().name("Steel bar").count() >= 5) {
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
                System.out.println("opening bank");
                Bank.open();
            }
            if (!PlayerHelper.hasItem("Hammer")) {
                System.out.println("Get a hammer");
                InteractionsHelper.depositAndWithdraw(ItemList.HAMMER_2347, 1);
            }
            else if (Inventory.stream().name("Steel bar").count() < 5) {
                Bank.depositAllExcept("Hammer", "Steel bar");
                System.out.println("Withdraw steel bars");
                InteractionsHelper.withdrawItem(ItemList.STEEL_BAR_2353, 27);
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
            InteractionsHelper.interactWithGameobject(ItemList.STEEL_BAR_2353, anvil, 312, 22, "Smith", 5);
        }
    }
}
