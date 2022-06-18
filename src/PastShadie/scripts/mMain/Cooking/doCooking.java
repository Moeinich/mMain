package src.PastShadie.scripts.mMain.Cooking;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class doCooking extends Task {
    @Override
    public boolean activate() {
        return (Inventory.stream().id(skillData.rawFish).count() >= 1) && skillData.cookingAreaEdgeville.contains(Players.local());
    }

    @Override
    public void execute() {
        System.out.println("We are doing some cooking.");
        Component cookWidget = Widgets.widget(270).component(14);

        if (skillData.cookingStove.inViewport()) {
            skillData.cookingStove.interact("Cook", "Stove");
        }
        Condition.wait(() -> Widgets.widget(270).valid(), 150,50);
        cookWidget.click();
        Condition.wait(() -> Players.local().animation() == -1, 300,150);
    }
}