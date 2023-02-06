package Helpers;

import org.powbot.api.Condition;
import org.powbot.api.Locatable;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GroundItem;
import org.powbot.api.rt4.GroundItems;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import script.mMain;

public class PlayerHelper {
    public void ShouldEat() {
        mMain.State = "Eating food";
        Item food = Inventory.stream().action("Eat").first();
        food.interact("Eat");
        Condition.wait(() -> !(Skills.level(Constants.SKILLS_HITPOINTS) >= 6), 100, 30);
    }
    public void BankForFood(int FoodName, int Amount) {
        Locatable nearestBank = Bank.nearest();
        if (nearestBank.tile().distanceTo(Players.local()) > 5) {
            Movement.moveToBank();
        }
        if (nearestBank.tile().distanceTo(Players.local()) < 5) {
            if (!Bank.opened()) {
                Bank.open();
                Condition.wait( () -> Bank.opened(), 200, 50);
            }
            if (Bank.opened()) {
                InteractionsHelper interactionsHelper = new InteractionsHelper();
                interactionsHelper.DepositAndWithdraw(FoodName, Amount);
                Condition.wait( () -> Inventory.stream().id(FoodName).count() >= 1, 200, 50);
                Condition.wait( () -> !Bank.opened(), 150, 50);
            }
        }
    }
    public void LootItems(String Action, String ItemName) {
        GroundItem groundItem = GroundItems.stream().within(7).name(ItemName).nearest().first();
        if (groundItem.inViewport()){
            groundItem.interact(Action, ItemName);
            Condition.wait(() -> GroundItems.stream().id(groundItem.id()).at(groundItem.tile()).isEmpty(), 150, 50);
        }
    }
}
