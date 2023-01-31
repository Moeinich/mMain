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
    Component beerGlassWidgetID = Widgets.widget(270).component(14);
    Component beerGlassWidget = Components.stream().widget(270).action("Make", "Beer glass").viewable().first();

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_CRAFTING) <= 4;
    }

    @Override
    public void execute() {
        mMain.State = "using pipe on glass";
        if (Inventory.stream().id(ItemList.MOLTEN_GLASS_1775).count() == 0) {
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

        if (Game.tab(Game.Tab.INVENTORY) && !Widgets.widget(312).valid()) {
            Item glassblowingPipe = Inventory.stream().name("Glassblowing pipe").first();
            Item moltenGlass = Inventory.stream().name("Molten glass").first();
            if (Inventory.selectedItem().id() == -1) {
                glassblowingPipe.interact("Use");
                Condition.wait((Callable<Boolean>) () -> Inventory.selectedItem().id() == glassblowingPipe.id(), 150, 20);
            } else if (Inventory.selectedItem().id() == glassblowingPipe.id()) {
                moltenGlass.interact("Use");
                Condition.wait(() -> Widgets.widget(270).valid(), 150,50);
            }
        }
        if (Widgets.widget(270).valid()) {
            mMain.State = "click widget for beer";
            beerGlassWidget.click();
            mMain.State = "clicked widget";
        }
        //wait!
        Condition.wait(() -> Players.local().animation() == -1, 2000,50);
    }
}
