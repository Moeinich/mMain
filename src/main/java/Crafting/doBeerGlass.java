package Crafting;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Components;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Widgets;
import org.powbot.mobile.script.ScriptManager;

import java.util.concurrent.Callable;

import Assets.ItemList;
import Assets.Task;
import Assets.skillData;
import script.mMain;

public class doBeerGlass extends Task {
    Component beerGlassWidget = Components.stream().widget(270).action("Make", "Beer glass").viewable().first();
    Item glassblowingPipe = Inventory.stream().name("Glassblowing pipe").first();
    Item moltenGlass = Inventory.stream().name("Molten glass").first();
    int moltenGlassCount = (int) Inventory.stream().id(ItemList.MOLTEN_GLASS_1775).count();


    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_CRAFTING) <= 10;
    }

    @Override
    public void execute() {
        if (Bank.opened()) {
            Bank.depositAllExcept("Glassblowing pipe", "Molten glass");
            if (Inventory.stream().name("Glassblowing pipe").count() == 0) {
                Bank.withdraw("Glassblowing pipe", 1);
            }
            if (Bank.stream().name("Molten glass").first().stackSize() < 27) {
                mMain.State = "We ran out of MG";
                mMain.taskRunning.set(false); //Skip task on progressive
            } else {
                ScriptManager.INSTANCE.stop(); //Stop script
            }
            if (moltenGlassCount == 0) {
                mMain.State = "Withdraw 27 MG";
                Bank.withdraw(ItemList.MOLTEN_GLASS_1775, 27);
                Condition.wait(() -> Bank.close(), 50, 10);
            }
        } else if (Game.tab(Game.Tab.INVENTORY)) {
            if (Widgets.widget(270).valid()) {
                mMain.State = "Click widget";
                beerGlassWidget.click();
                Condition.wait((Callable<Boolean>) () -> !Widgets.widget(270).valid());
            }
            int initialCount = (int) Inventory.stream().name("Molten glass").count();
            while (true) {
                int currentCount = (int) Inventory.stream().name("Molten glass").count();
                if (currentCount >= initialCount) {
                    mMain.State = "We stopped crafting";
                    break;
                } else {
                    mMain.State = "We're still going";
                    initialCount = currentCount;
                    Condition.sleep(6000);
                }
                if (moltenGlassCount > 0) {
                    mMain.State = "MG count 0";
                    break;
                }
            }
        }
        else {
            if (moltenGlassCount >= 1 && Game.tab(Game.Tab.INVENTORY)) {
                if (Inventory.selectedItem().id() != glassblowingPipe.id() && !Widgets.widget(270).valid()) {
                    mMain.State = "Use GBP";
                    glassblowingPipe.interact("Use");
                    Condition.wait((Callable<Boolean>) () -> Inventory.selectedItem().id() == glassblowingPipe.id(), 150, 20);
                    mMain.State = "Use MG";
                    moltenGlass.interact("Use");
                }
            } else {
                if (Bank.inViewport()) {
                    mMain.State = "Open bank";
                    Bank.open();
                }
            }
        }
}


        /* if (Inventory.stream().id(ItemList.MOLTEN_GLASS_1775).count() == 0) {
            mMain.State = "Get molten glass";
            if (!Bank.opened() && Bank.inViewport()) {
                Bank.open();
            }
            if (Bank.open()) {
                Bank.depositAllExcept("Glassblowing pipe");
                Bank.withdraw(ItemList.MOLTEN_GLASS_1775, 27);
                Bank.close();
            }
        }

        if (Game.tab(Game.Tab.INVENTORY)) {
            mMain.State = "Use BP on glass";
            Item glassblowingPipe = Inventory.stream().name("Glassblowing pipe").first();
            Item moltenGlass = Inventory.stream().name("Molten glass").first();
            if (Inventory.selectedItem().id() != glassblowingPipe.id() && !Widgets.widget(270).valid()) {
                glassblowingPipe.interact("Use");
                Condition.wait((Callable<Boolean>) () -> Inventory.selectedItem().id() == glassblowingPipe.id(), 150, 20);
            }
            if (Inventory.selectedItem().id() == glassblowingPipe.id() && !Widgets.widget(270).valid()) {
                moltenGlass.interact("Use");
                Condition.wait((Callable<Boolean>) () -> Widgets.widget(270).valid(), 150, 50);
            }
        }

        if (Widgets.widget(270).valid()) {
            mMain.State = "Click widget";
            beerGlassWidget.click();
            Condition.wait(() -> Players.local().animation() == -1, 2000,50);
        }*/
}