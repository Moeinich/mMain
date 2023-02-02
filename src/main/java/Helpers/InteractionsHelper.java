package Helpers;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Widgets;
import org.powbot.mobile.script.ScriptManager;

import java.util.concurrent.Callable;

import script.mMain;

public class InteractionsHelper {

    public void WithdrawItem(int ItemName, int Amount) {
            if (Bank.stream().id(ItemName).first().stackSize() < 27) {
                mMain.State = "We ran out of " + ItemName;
                mMain.taskRunning.set(false); //Skip task on progressive
            } else {
                Bank.withdraw(ItemName, 27);
            }
        }
        public void CheckInventoryItemAndWithdraw(int item) {
            mMain.State = "Check knife";
            if (!Bank.opened() && Bank.inViewport()) {
                Bank.open();
            }
            if (Inventory.stream().id(item).count() == 0) {
                Bank.withdraw(item, 1);
            }
        }

        public void CombineItems(int RequiredItemID, int CombineWithItemID, int WidgetID, int ComponentID) {
            int timer = 0;
            int initialCount = (int) Inventory.stream().id(ItemList.WILLOW_LOGS_1519).count();
            while (!ScriptManager.INSTANCE.isStopping()) {
                mMain.State = "Combining..";
                int currentCount = (int) Inventory.stream().id(ItemList.WILLOW_LOGS_1519).count();
                if (currentCount >= initialCount) {
                    timer += 2;
                    if (timer >= 2) {
                        Item Tool = Inventory.stream().id(RequiredItemID).first();
                        Item CombineWithID = Inventory.stream().id(CombineWithItemID).first();

                        if (Inventory.stream().id(CombineWithItemID).count() >= 1 && Game.tab(Game.Tab.INVENTORY)) {
                            if (ScriptManager.INSTANCE.isStopping()) {
                                ScriptManager.INSTANCE.stop();
                            }
                            if (Inventory.selectedItem().id() != RequiredItemID && !Widgets.widget(WidgetID).valid()) {
                                Tool.interact("Use");
                                Condition.wait( () -> Inventory.selectedItem().id() == RequiredItemID, 150, 20);
                            }
                            if (Inventory.selectedItem().id() == RequiredItemID) {
                                CombineWithID.interact("Use");
                                Condition.wait( () -> Widgets.widget(WidgetID).valid(), 500, 20);
                            }
                            if (Widgets.widget(WidgetID).valid()) {
                                Widgets.widget(WidgetID).component(ComponentID).click();
                                Condition.wait( () -> !Widgets.widget(WidgetID).valid(), 150, 20);
                            }
                        }
                        if (Inventory.stream().id(ItemList.WILLOW_LOGS_1519).count() == 0) {
                            break;
                        }
                        timer = 0;
                    }
                } else {
                    initialCount = currentCount;
                    timer = 0;
                }
                int randomSleep = Random.nextInt(1750, 1850);
                Condition.sleep(randomSleep);
            }
        }
}