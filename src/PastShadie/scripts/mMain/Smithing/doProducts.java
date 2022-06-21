package src.PastShadie.scripts.mMain.Smithing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

public class doProducts extends Task {
    @Override
    public boolean activate() {
        if (skillData.smithingAreaEdgeville.contains(Players.local().tile()) && Inventory.stream().id(skillData.smithingOres).count() >= 1) {
            return true;
        }
        if (skillData.smithingTileVarrockWest.equals(Players.local().tile()) && Inventory.stream().id(skillData.bronzeBar).count() >= 3) {
            return true;
        }
        return false;
    }


    @Override
    public void execute() {
        mMain.scriptStatus = "Do products";
        Component furnaceWidgetCopper = Widgets.widget(270).component(14);
        Component furnaceWidgetIron = Widgets.widget(270).component(15);
        GameObject furnaceEdgeville = Objects.stream().id(16469).first();
        GameObject anvilVarrockWest = Objects.stream().id(2097).nearest().first();
        Component anvilWidgetBronzeBar = Widgets.widget(312).component(16);
        //Do bars!
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

        System.out.println("is anvil in viewport " + anvilVarrockWest.inViewport());
        System.out.println("is widget valid " + Widgets.widget(312).valid());
        //Do Bronze Warhammers
        if (anvilVarrockWest.inViewport() && !Widgets.widget(312).valid()) {
            anvilVarrockWest.interact("Smith", "Anvil");
            Condition.wait(() -> Widgets.widget(312).valid(), 150,50);
        }
        if(anvilWidgetBronzeBar.valid() && Inventory.stream().id(skillData.bronzeBar).count() >= 1) {
            anvilWidgetBronzeBar.click();
        }


        //Wait.
        Condition.wait(() -> Players.local().animation() == -1, 2000,50);
    }
}