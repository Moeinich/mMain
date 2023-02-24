package Helpers;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Camera;
import org.powbot.api.rt4.Chat;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Widgets;
import org.powbot.mobile.script.ScriptManager;

import script.mMain;

public class InteractionsHelper {
    public static void withdrawItem(int itemID, int amount) {
        if (!Bank.opened() && Bank.inViewport()) {
            if (Bank.open()) {
                Condition.wait(Bank::opened, 150, 10);
            }
        }
        if (Bank.stream().id(itemID).first().stackSize() < amount) {
            mMain.state = "We ran out of " + itemID;
            System.out.println("We ran out of items");
            SkillData.setSkillDone();
            System.out.println("Skill was set to done");
            mMain.taskRunning.set(false); //Skip task on progressive
            System.out.println("taskRunning was set false");
        } else {
            System.out.println("Withdrawing " + itemID + " amount:" + amount);
            Bank.withdraw(itemID, amount);
            Condition.wait( () -> Inventory.stream().id(itemID).isNotEmpty(), 150, 10);
        }
    }
    public static void depositAndWithdraw(int itemID, int amount) {
        if (!Bank.opened() && Bank.inViewport()) {
            if (Bank.open()) {
                Condition.wait(Bank::opened, 150, 10);
            }
        }
        if (Bank.stream().id(itemID).first().stackSize() < amount) {
            mMain.state = "We ran out of " + itemID;
            System.out.println("We ran out of items");
            SkillData.setSkillDone();
            System.out.println("Skill was set to done");
            mMain.taskRunning.set(false); //Skip task on progressive
            System.out.println("taskRunning was set false");
        } else {
            Bank.depositInventory();
            Condition.wait(Inventory::isEmpty, 150, 10);
            System.out.println("Withdrawing " + itemID + " amount:" + amount);
            Bank.withdraw(itemID, amount);
            Condition.wait( () -> Inventory.stream().id(itemID).isNotEmpty(), 150, 10);
        }
    }

    public static void cameraCheck() {
        if (Game.tab(Game.Tab.SETTINGS) && Players.local().isRendered() && Camera.getZoom() > 4) {
            System.out.println("Moving camera slider");
                Camera.moveZoomSlider(Camera.ZOOM_MAX);
                Condition.wait( () -> Camera.getZoom() < 3, 250, 50);
        }
    }

    public static void combineItems(int requiredItemID, int combineWithItemID, int widgetID, int componentID) {
        mMain.state = "Combining..";
        if (!ScriptManager.INSTANCE.isStopping()) {
            Item Tool = Inventory.stream().id(requiredItemID).first();
            Item CombineWithID = Inventory.stream().id(combineWithItemID).first();

            if (Inventory.selectedItem().id() != requiredItemID && !Widgets.widget(widgetID).valid()) {
                System.out.println("Use tool");
                Tool.interact("Use");
                Condition.wait(() -> Inventory.selectedItem().id() == requiredItemID, 150, 20);
            }
            if (Inventory.selectedItem().id() == requiredItemID) {
                System.out.println("Use tool on combine item");
                CombineWithID.interact("Use");
                Condition.wait(() -> Widgets.widget(widgetID).valid(), 500, 20);
                System.out.println("Widget valid!");
            }
            if (Widgets.widget(widgetID).valid()) {
                System.out.println("Click component");
                Widgets.widget(widgetID).component(componentID).click();
                if(Condition.wait(() -> !Widgets.widget(widgetID).valid(), 150, 20)){
                    System.out.println("Widget no longer valid");
                    Condition.wait(()-> Inventory.stream().id(combineWithItemID).isEmpty() || Chat.canContinue(), 1000, 80);
                    System.out.println("CombineWithItem is empty or leveled up");
                }
            }
        }
    }
    public static void interactWithGameobject(int requiredItemID, GameObject gameObject, int widgetID, int componentID, String action, int count) {
        if (!ScriptManager.INSTANCE.isStopping()) {
            System.out.println("Interact with gameobject");
            gameObject.interact(action);
            Condition.wait( () -> Widgets.widget(widgetID).valid(), 300, 50);
            if (Widgets.widget(widgetID).valid()) {
                System.out.println("Click widget");
                Widgets.widget(widgetID).component(componentID).click();
                if(Condition.wait(() -> !Widgets.widget(widgetID).valid(), 500, 20)){
                    System.out.println("Widget no longer valid");
                    Condition.wait(()-> Inventory.stream().id(requiredItemID).count() < count || Chat.canContinue(), 1000, 80);
                    System.out.println("CombineWithItem is empty or leveled up");
                }
            }
        }
    }
}
