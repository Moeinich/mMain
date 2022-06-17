package src.PastShadie.scripts.mMain.Cooking;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class doCooking extends Task {
    @Override
    public boolean activate() {
        System.out.print("Activate do cooking");
        return (Inventory.stream().id(skillData.rawFish).count() >= 1) && skillData.cookingAreaEdgeville.contains(Players.local());
    }

    @Override
    public void execute() {
        System.out.println("We are doing some cooking.");
        Component cookWidget = Widgets.widget(270).component(14);

        if (!skillData.cookingStove.inViewport()) {
            Camera.turnTo(skillData.cookingStove);
        }
        if (skillData.cookingStove.inViewport()) {
            System.out.println("Click stove");
            skillData.cookingStove.interact("Cook", "Stove");
        }
        if (Condition.wait(Widgets.widget(270).valid())) {
            System.out.println("Click widget");
            cookWidget.click();
        }
        Condition.wait(() -> Players.local().animation() == -1, 500,150);
    }
}