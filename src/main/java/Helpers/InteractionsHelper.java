package Helpers;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Camera;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Widgets;
import org.powbot.mobile.script.ScriptManager;

import java.util.concurrent.atomic.AtomicBoolean;

import script.mMain;

public class InteractionsHelper {

    public static void withdrawItem(int ItemName, int Amount) {
        if (!Bank.opened() && Bank.inViewport()) {
            if (Bank.open()) {
                Condition.wait(Bank::opened, 200, 50);
            }
        }
        if (Bank.stream().id(ItemName).isEmpty()) {
            mMain.state = "We ran out of " + ItemName;
            SkillData.setSkillDone();
            Bank.close();
            mMain.taskRunning.set(false); //Skip task on progressive
        } else {
            Bank.withdrawModeQuantity();
            Bank.withdrawAmount(ItemName, Amount);
        }
    }
    public static void depositAndWithdraw(int item, int amount) {
        if (!Bank.opened() && Bank.inViewport()) {
            if (Bank.open()) {
                Condition.wait(Bank::opened, 200, 50);
            }
        }
        if (Bank.stream().id(item).isEmpty()) {
            mMain.state = "We ran out of " + item;
            SkillData.setSkillDone();
            Bank.close();
            mMain.taskRunning.set(false);//Skip task on progressive
        } else {
            Bank.depositInventory();
            Condition.wait(Inventory::isEmpty, 150, 50);
            Bank.withdraw(item, amount);
        }
    }

    public static void combineItems(int RequiredItemID, int CombineWithItemID, int WidgetID, int ComponentID) {
        int timer = 0;
        int initialCount = (int) Inventory.stream().id(CombineWithItemID).count();
        while (!ScriptManager.INSTANCE.isStopping() && Inventory.stream().id(CombineWithItemID).isNotEmpty() && Inventory.stream().id(RequiredItemID).isNotEmpty()) {
            mMain.state = "Combining.. ";
            int currentCount = (int) Inventory.stream().id(CombineWithItemID).count();
            if (currentCount >= initialCount) {
                timer += 1;
                if (timer >= 3) {
                    Item Tool = Inventory.stream().id(RequiredItemID).first();
                    Item CombineWithID = Inventory.stream().id(CombineWithItemID).first();

                    if (Inventory.stream().id(CombineWithItemID).isNotEmpty()) {
                        if (Inventory.selectedItem().id() != RequiredItemID) {
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

    public static void interactWithGameobject(int RequiredItemID, GameObject Gameobject, int WidgetID, int ComponentID, String Action, String Name, int Count) {
        int timer = 0;
        int initialCount = (int) Inventory.stream().id(RequiredItemID).count();
        while (!ScriptManager.INSTANCE.isStopping() && Inventory.stream().id(RequiredItemID).count() >= Count) {
            int currentCount = (int) Inventory.stream().id(RequiredItemID).count();
            if (currentCount >= initialCount) {
                timer += 1;
                if (timer >= 3) {
                    if (Inventory.stream().id(RequiredItemID).count() >= Count) {
                        Gameobject.interact(Action, Name);
                        Condition.wait( () -> Widgets.widget(WidgetID).valid(), 300, 50);
                        if (Widgets.widget(WidgetID).valid()) {
                            Widgets.widget(WidgetID).component(ComponentID).click();
                            Condition.wait( () -> !Widgets.widget(WidgetID).valid(), 300, 100);
                        }
                    }
                    if (Inventory.stream().id(RequiredItemID).count() >= Count) {
                        break;
                    }
                    timer = 0;
                }
            } else {
                initialCount = currentCount;
                timer = 0;
            }
            int randomSleep = Random.nextInt(900, 1000);
            Condition.sleep(randomSleep);
        }
    }

    public static void cameraCheck() {
        if (Game.tab(Game.Tab.SETTINGS) && Players.local().isRendered() && Camera.getZoom() > 4) {
            Camera.moveZoomSlider(Camera.ZOOM_MAX);
            Condition.wait( () -> Camera.getZoom() < 3, 250, 50);
        }
    }



    private static void waitForXpToStopIncreasing(AtomicBoolean doActions, long wait) {
        while (!ScriptManager.INSTANCE.isStopping() && Skills.timeSinceExpGained() < wait) {
            mMain.state = "Time since xp: " + Skills.timeSinceExpGained();
            if (Skills.timeSinceExpGained() > wait) {
                mMain.state = "Done waiting";
                doActions.set(true);
                break;
            }
            int randomSleep = Random.nextInt(300, 700);
            Condition.sleep(randomSleep);
        }
    }

    public static void combineItemsTest(int RequiredItemID, int CombineWithItemID, int WidgetID, int ComponentID, long wait) {
        mMain.state = "Combining..";
        AtomicBoolean doActions = new AtomicBoolean(true);
        if (doActions.get()) {
            Item Tool = Inventory.stream().id(RequiredItemID).first();
            Item CombineWithID = Inventory.stream().id(CombineWithItemID).first();

            if (Inventory.selectedItem().id() != RequiredItemID) {
                Tool.interact("Use");
                Condition.wait(() -> Inventory.selectedItem().id() == RequiredItemID, 150, 20);
            }
            if (Inventory.selectedItem().id() == RequiredItemID) {
                CombineWithID.interact("Use");
                Condition.wait(() -> Widgets.widget(WidgetID).valid(), 500, 20);
            }
            if (Widgets.widget(WidgetID).valid()) {
                Widgets.widget(WidgetID).component(ComponentID).click();
                Condition.wait(() -> !Widgets.widget(WidgetID).valid(), 150, 20);
            }
            doActions.set(false);
        }
        if (!doActions.get()) {
            waitForXpToStopIncreasing(doActions, wait);
        }
    }
    public static void interactWithGameobjectTest(int RequiredItemID, GameObject Gameobject, int WidgetID, int ComponentID, String Action, String Name, long wait) {
        AtomicBoolean doActions = new AtomicBoolean(true);
        if (doActions.get()) {
            Gameobject.interact(Action, Name);
            Condition.wait( () -> Widgets.widget(WidgetID).valid(), 300, 50);
            if (Widgets.widget(WidgetID).valid()) {
                Widgets.widget(WidgetID).component(ComponentID).click();
                Condition.wait( () -> !Widgets.widget(WidgetID).valid(), 300, 100);
            }
            doActions.set(false);
        }
        if (!doActions.get()) {
            waitForXpToStopIncreasing(doActions, wait);
        }
    }
}
