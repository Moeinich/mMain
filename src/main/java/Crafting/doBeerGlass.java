package Crafting;

import org.powbot.api.Condition;
import org.powbot.api.event.SkillExpGainedEvent;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Components;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Widgets;

import java.util.Date;
import java.util.concurrent.Callable;

import Assets.ItemList;
import Assets.Task;
import script.mMain;

public class doBeerGlass extends Task {
    Component beerGlassWidget = Components.stream().widget(270).action("Make", "Beer glass").viewable().first();
    private static Date lastSkillExpGainedTime = null;

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_CRAFTING) <= 10;
    }

    @Override
    public void execute() {
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
        }
    }
}
