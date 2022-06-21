package src.PastShadie.scripts.mMain.Smithing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Widgets;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

public class doProducts extends Task {
    @Override
    public boolean activate() {
        return skillData.smithingAreaEdgeville.contains(Players.local().tile()) && Inventory.stream().id(skillData.smithingOres).count() >= 1;
    }


    @Override
    public void execute() {
        mMain.scriptStatus = "Do products";
        Component furnaceWidgetCopper = Widgets.widget(270).component(14);
        Component furnaceWidgetIron = Widgets.widget(270).component(15);


        if (skillData.furnaceEdgeville.inViewport() && !Widgets.widget(270).valid()) {
            skillData.furnaceEdgeville.interact("Smelt", "Furnace");
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