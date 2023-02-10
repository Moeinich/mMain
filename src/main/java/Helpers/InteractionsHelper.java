package Helpers;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Camera;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Widgets;
import org.powbot.mobile.script.ScriptManager;

import script.mMain;

public class InteractionsHelper {

    public void WithdrawItem(int ItemName, int Amount) {
        if (!Bank.opened() && Bank.inViewport()) {
            Bank.open();
        }
        if (Bank.stream().id(ItemName).isEmpty()) {
            mMain.State = "We ran out of " + ItemName;
            SkillData.SetSkillDone();
            Bank.close();
            mMain.taskRunning.set(false); //Skip task on progressive
        } else {
            Bank.withdrawModeQuantity();
            Bank.withdrawAmount(ItemName, Amount);
        }
    }
    public void DepositAndWithdraw(int item, int amount) {
        if (!Bank.opened() && Bank.inViewport()) {
            Bank.open();
        }
        if (Bank.stream().id(item).isEmpty()) {
            mMain.State = "We ran out of " + item;
            SkillData.SetSkillDone();
            Bank.close();
            mMain.taskRunning.set(false);//Skip task on progressive
        } else {
            Bank.depositInventory();
            Condition.wait( () -> Inventory.isEmpty(), 150, 50);
            Bank.withdraw(item, amount);
        }
    }

    public void CombineItems(int RequiredItemID, int CombineWithItemID, int WidgetID, int ComponentID) {
        int timer = 0;
        int initialCount = (int) Inventory.stream().id(CombineWithItemID).count();
        while (!ScriptManager.INSTANCE.isStopping() && Inventory.stream().id(CombineWithItemID).isNotEmpty()) {
            mMain.State = "Combining.. ";
            int currentCount = (int) Inventory.stream().id(CombineWithItemID).count();
            if (currentCount >= initialCount) {
                timer += 1;
                if (timer >= 3) {
                    Item Tool = Inventory.stream().id(RequiredItemID).first();
                    Item CombineWithID = Inventory.stream().id(CombineWithItemID).first();

                    if (Inventory.stream().id(CombineWithItemID).isNotEmpty() && Game.tab(Game.Tab.INVENTORY)) {
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
                    if (Inventory.stream().id(RequiredItemID).isEmpty()) {
                        break;
                    }
                    timer = 0;
                }
            } else {
                initialCount = currentCount;
                timer = 0;
            }
            int randomSleep = Random.nextInt(1000, 1300);
            Condition.sleep(randomSleep);
        }
    }
    public void InteractWithGameobject(int RequiredItemID, GameObject Gameobject, int WidgetID, int ComponentID, String Action, String Name) {
        int timer = 0;
        int initialCount = (int) Inventory.stream().id(RequiredItemID).count();
        while (!ScriptManager.INSTANCE.isStopping() && Inventory.stream().id(RequiredItemID).isNotEmpty()) {
            int currentCount = (int) Inventory.stream().id(RequiredItemID).count();
            if (currentCount >= initialCount) {
                timer += 1;
                if (timer >= 3) {
                    if (Inventory.stream().id(RequiredItemID).isNotEmpty() && Game.tab(Game.Tab.INVENTORY)) {
                        if (ScriptManager.INSTANCE.isStopping()) {
                            ScriptManager.INSTANCE.stop();
                        }
                        if (Inventory.selectedItem().id() != RequiredItemID && !Widgets.widget(WidgetID).valid()) {
                            Gameobject.interact(Action, Name);
                        }
                        if (Widgets.widget(WidgetID).valid()) {
                            Widgets.widget(WidgetID).component(ComponentID).click();
                            Condition.wait( () -> !Widgets.widget(WidgetID).valid(), 300, 100);
                        }
                    }
                    if (Inventory.stream().id(RequiredItemID).isEmpty()) {
                        break;
                    }
                    timer = 0;
                }
            } else {
                initialCount = currentCount;
                timer = 0;
            }
            int randomSleep = Random.nextInt(800, 1000);
            Condition.sleep(randomSleep);
        }
    }

    public static void cameraCheck() {
        if (Game.tab(Game.Tab.SETTINGS) && Camera.getZoom() > 4) {
            Camera.moveZoomSlider(Camera.ZOOM_MAX);
            Condition.wait( () -> Camera.getZoom() < 3, 250, 50);
        }
    }
}
