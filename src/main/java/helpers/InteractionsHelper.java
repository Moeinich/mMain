package helpers;

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
    public static void withdrawItem(int itemId, int specifiedAmount) {
        if (!Bank.opened() && Bank.inViewport()) {
            if (Bank.open()) {
                System.out.println("Open bank to withdraw item: " + itemId + " amount: " + specifiedAmount);
                Condition.wait(Bank::opened, 150, 10);
            }
        }
        if (Bank.stream().id(itemId).first().stackSize() < specifiedAmount || Bank.stream().id(itemId).isEmpty()) {
            System.out.println("We ran out of item: " + itemId);
            SkillData.setSkillDone();
            System.out.println("Skill was set to done");
            mMain.skillRunning.set(false); //Skip task on progressive
            System.out.println("taskRunning was set false");
        } else {
            System.out.println("Withdrawing " + itemId + " amount:" + specifiedAmount);
            if (specifiedAmount == -1) {
                Bank.withdraw(itemId, Bank.Amount.ALL);
                Condition.wait(() -> Inventory.stream().id(itemId).count() == specifiedAmount, 150, 10);
            } else {
                Bank.withdraw(itemId, specifiedAmount);
                Condition.wait(() -> Inventory.stream().id(itemId).isNotEmpty(), 150, 10);
            }
        }
    }

    public static void depositAndWithdraw(int itemId, int specifiedAmount) {
        if (!Bank.opened() && Bank.inViewport()) {
            if (Bank.open()) {
                System.out.println("Open bank to withdraw item: " + itemId + " amount: " + specifiedAmount);
                Condition.wait(Bank::opened, 150, 10);
            }
        }
        if (Bank.stream().id(itemId).first().stackSize() < specifiedAmount) {
            System.out.println("We ran out of: " + itemId);
            SkillData.setSkillDone();
            System.out.println("Running skill was set to done");
            mMain.skillRunning.set(false); //Skip task on progressive
            System.out.println("taskRunning was set false");
        } else {
            Bank.depositInventory();
            System.out.println("Withdrawing " + itemId + " amount:" + specifiedAmount);
            if (specifiedAmount == -1) {
                Bank.withdraw(itemId, Bank.Amount.ALL);
                Condition.wait(() -> Inventory.stream().id(itemId).isNotEmpty(), 150, 10);
            } else {
                Bank.withdraw(itemId, specifiedAmount);
                Condition.wait(() -> Inventory.stream().id(itemId).isNotEmpty(), 150, 10);
            }
        }
    }

    public static void cameraCheck() {
        if (Game.tab(Game.Tab.SETTINGS) && Players.local().inViewport() && Camera.getZoom() > 4) {
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
                    System.out.println("Widget no longer valid, sleeping");
                    Condition.wait(()-> Inventory.stream().id(combineWithItemID).isEmpty() || Chat.canContinue(), 1000, 80);
                    System.out.println("CombineWithItem is empty or leveled up");
                }
            }
        }
    }
    public static void interactWithGameobject(int requiredItemID, GameObject gameObject, int widgetID, int componentID, String action, int itemCountToReset) {
        if (!ScriptManager.INSTANCE.isStopping()) {
            System.out.println("Interact with gameobject");
            gameObject.interact(action);
            Condition.wait( () -> Widgets.widget(widgetID).valid(), 300, 50);
            if (Widgets.widget(widgetID).valid()) {
                System.out.println("Click widget");
                Widgets.widget(widgetID).component(componentID).click();
                if(Condition.wait(() -> !Widgets.widget(widgetID).valid(), 500, 20)){
                    System.out.println("Widget no longer valid, sleeping");
                    Condition.wait(()-> Inventory.stream().id(requiredItemID).count() < itemCountToReset || Chat.canContinue(), 1000, 80);
                    System.out.println("CombineWithItem is empty or leveled up");
                }
            }
        }
    }
}
