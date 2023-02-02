package Smithing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

import Helpers.Task;
import Helpers.skillData;
import script.mMain;

public class doBars extends Task {
    @Override
    public boolean activate() {
        return skillData.smithingAreaEdgeville.contains(Players.local().tile()) && Inventory.stream().id(skillData.smithingOres).count() >= 1;
    }

    @Override
    public void execute() {
        mMain.State = "Do bars";
        Component furnaceWidgetCopper = Widgets.widget(270).component(14);
        Component furnaceWidgetIron = Widgets.widget(270).component(15);
        GameObject furnaceEdgeville = Objects.stream().id(16469).first();

        if (furnaceEdgeville.inViewport() && !Widgets.widget(270).valid()) {
            furnaceEdgeville.interact("Smelt", "Furnace");
            Condition.wait(() -> Widgets.widget(270).valid(), 150,50);
        }
        if(furnaceWidgetCopper.valid() && Inventory.stream().id(skillData.copperBarOres).count() >= 1) {
            furnaceWidgetCopper.click();
        }
        if(furnaceWidgetIron.valid() && Inventory.stream().id(skillData.ironBarOres).count() >= 1) {
            furnaceWidgetIron.click();
        }
        Condition.wait(() -> Players.local().animation() == -1, 2000,50);
    }
}