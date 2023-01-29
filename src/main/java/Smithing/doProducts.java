package Smithing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

import Assets.Task;
import Assets.skillData;
import script.mMain;

public class doProducts extends Task {
    @Override
    public boolean activate() {
        return skillData.smithingTileVarrockWest.equals(Players.local().tile()) && Inventory.stream().id(skillData.bronzeBar).count() >= 3;
    }


    @Override
    public void execute() {
        mMain.scriptStatus = "Do products";
        GameObject anvilVarrockWest = Objects.stream().id(2097).first();
        Component anvilWidgetBronzeWarhammer = Widgets.widget(312).component(16);

        //Bronze Warhammers
        System.out.println("is anvil in viewport " + anvilVarrockWest.inViewport());
        System.out.println("is widget valid " + Widgets.widget(312).valid());

        if (anvilVarrockWest.inViewport() && !Widgets.widget(312).valid()) {
            anvilVarrockWest.interact("Smith", "Anvil");
            Condition.wait(() -> Widgets.widget(312).valid(), 150,50);
        }
        if(anvilWidgetBronzeWarhammer.valid() && Inventory.stream().id(skillData.bronzeBar).count() >= 1) {
            anvilWidgetBronzeWarhammer.click();
        }

        //Wait.
        Condition.wait(() -> Players.local().animation() == -1, 2000,50);
    }
}