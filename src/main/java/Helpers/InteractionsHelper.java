package Helpers;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Camera;
import org.powbot.api.rt4.Chat;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Components;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Widgets;
import org.powbot.mobile.script.ScriptManager;

import java.util.concurrent.atomic.AtomicBoolean;

import script.mMain;

public class InteractionsHelper {

    public static void withdrawItem(int ItemName, int Amount) {
        if (!Bank.opened() && Bank.inViewport()) {
            if (Bank.open()) {
                Condition.wait(Bank::opened, 150, 10);
            }
        }
        if (Bank.stream().id(ItemName).isEmpty()) {
            mMain.state = "We ran out of " + ItemName;
            SkillData.setSkillDone();
            Bank.close();
            mMain.taskRunning.set(false); //Skip task on progressive
        } else {
            Bank.withdrawAmount(ItemName, Amount);
            Condition.wait( () -> Inventory.stream().id(ItemName).isNotEmpty(), 150, 10);
        }
    }
    public static void depositAndWithdraw(int item, int amount) {
        if (!Bank.opened() && Bank.inViewport()) {
            if (Bank.open()) {
                Condition.wait(Bank::opened, 150, 10);
            }
        }
        if (Bank.stream().id(item).isEmpty()) {
            mMain.state = "We ran out of " + item;
            SkillData.setSkillDone();
            Bank.close();
            mMain.taskRunning.set(false);//Skip task on progressive
        } else {
            Bank.depositInventory();
            Condition.wait(Inventory::isEmpty, 150, 10);
            Bank.withdraw(item, amount);
            Condition.wait( () -> Inventory.stream().id(item).isNotEmpty(), 150, 10);
        }
    }

    public static void cameraCheck() {
        if (Game.tab(Game.Tab.SETTINGS) && Players.local().isRendered() && Camera.getZoom() > 4) {
            Camera.moveZoomSlider(Camera.ZOOM_MAX);
            Condition.wait( () -> Camera.getZoom() < 3, 250, 50);
        }
    }

    public static void combineItems(int RequiredItemID, int CombineWithItemID, int WidgetID, int ComponentID) {
        mMain.state = "Combining..";
        if (!ScriptManager.INSTANCE.isStopping()) {
            Item Tool = Inventory.stream().id(RequiredItemID).first();
            Item CombineWithID = Inventory.stream().id(CombineWithItemID).first();

            if (Inventory.selectedItem().id() != RequiredItemID && !Widgets.widget(WidgetID).valid()) {
                System.out.println("Use tool");
                Tool.interact("Use");
                Condition.wait(() -> Inventory.selectedItem().id() == RequiredItemID, 150, 20);
            }
            if (Inventory.selectedItem().id() == RequiredItemID) {
                System.out.println("Use tool on combine item");
                CombineWithID.interact("Use");
                Condition.wait(() -> Widgets.widget(WidgetID).valid(), 500, 20);
                System.out.println("Widget valid!");
            }
            if (Widgets.widget(WidgetID).valid()) {
                System.out.println("Click component");
                Widgets.widget(WidgetID).component(ComponentID).click();
                if(Condition.wait(() -> !Widgets.widget(WidgetID).valid(), 150, 20)){
                    System.out.println("Widget no longer valid");
                    Condition.wait(()-> Inventory.stream().id(CombineWithItemID).isEmpty() || Chat.canContinue(), 1000, 80);
                    System.out.println("CombineWithItem is empty or leveled up");
                }
            }
        }
    }
    public static void interactWithGameobject(int RequiredItemID, GameObject Gameobject, int WidgetID, int ComponentID, String Action, String Name, int Count) {
        if (!ScriptManager.INSTANCE.isStopping()) {
            System.out.println("Interact with gameobject");
            Gameobject.interact(Action, Name);
            Condition.wait( () -> Widgets.widget(WidgetID).valid(), 300, 50);
            if (Widgets.widget(WidgetID).valid()) {
                System.out.println("Click widget");
                Widgets.widget(WidgetID).component(ComponentID).click();
                if(Condition.wait(() -> !Widgets.widget(WidgetID).valid(), 150, 20)){
                    System.out.println("Widget no longer valid");
                    Condition.wait(()-> Inventory.stream().id(RequiredItemID).count() < Count || Chat.canContinue(), 1000, 80);
                    System.out.println("CombineWithItem is empty or leveled up");
                }
            }
        }
    }
}
