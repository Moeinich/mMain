package src.PastShadie.scripts.mMain.Cooking;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class doCooking extends Task {
    GameObject Stove = Objects.stream().id(12269).first();
    @Override
    public boolean activate() {
        return (Inventory.stream().id(skillData.rawFish).count() >= 1) &&
                Stove.inViewport() && skillData.cookingAreaEdgeville.contains(Players.local());
    }

    @Override
    public void execute() {
        System.out.println("Start cooking!");
        Component cookWidget =

                //Widgets.stream().id(1794734);

        if (Players.local().animation() == -1 && !cookWidget.inViewport()) {
            Stove.interact("Cook", "Stove");
            Condition.wait(() -> Players.local().animation() == -1, 350, 50);
        }
        if (cookWidget.inViewport()) {
            cookWidget.interact("Cook");
        }
    }
}