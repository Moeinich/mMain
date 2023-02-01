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

import java.util.concurrent.Callable;

import Assets.ItemList;
import Assets.Task;
import Assets.skillData;
import script.mMain;

public class doBeerGlass extends Task {
    Component beerGlassWidget = Components.stream().widget(270).action("Make", "Beer glass").viewable().first();


    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_CRAFTING) <= 10;
    }

    @Override
    public void execute() {
        Item glassblowingPipe = Inventory.stream().name("Glassblowing pipe").first();
        Item moltenGlass = Inventory.stream().name("Molten glass").first();

        if (Bank.opened()) {
            Bank.depositAllExcept(ItemList.GLASSBLOWING_PIPE_1785, ItemList.MOLTEN_GLASS_1775);
            if (Inventory.stream().id(ItemList.GLASSBLOWING_PIPE_1785).count() == 0) {
                Bank.withdraw(ItemList.GLASSBLOWING_PIPE_1785, 1);
            }
            if (Bank.stream().id(ItemList.MOLTEN_GLASS_1775).first().stackSize() < 27) {
                mMain.State = "We ran out of MG";
                //Stop script, maybe??
            }
            if (Inventory.stream().id(ItemList.MOLTEN_GLASS_1775).count() == 0) {
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
            int initialCount = (int) Inventory.stream().id(ItemList.MOLTEN_GLASS_1775).count();
            while (true) {
                int currentCount = (int) Inventory.stream().id(ItemList.MOLTEN_GLASS_1775).count();
                if (currentCount >= initialCount) {
                    mMain.State = "We stopped crafting";
                    break;
                } else {
                    mMain.State = "We're still going";
                    initialCount = currentCount;
                    Condition.sleep(6000);
                }
                if (Inventory.stream().id(ItemList.MOLTEN_GLASS_1775).count() > 0) {
                    mMain.State = "MG count 0";
                    break;
                }
            }
        }
        else {
            if (Inventory.stream().id(ItemList.MOLTEN_GLASS_1775).count() >= 1) {
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
                } else {
                    mMain.State = "Open inventory";
                    Game.tab(Game.Tab.INVENTORY);
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