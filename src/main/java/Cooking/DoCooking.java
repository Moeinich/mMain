package Cooking;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class DoCooking extends Task {
    @Override
    public boolean activate() {
        return (Inventory.stream().id(SkillData.rawFish).count() >= 1) && SkillData.cookingAreaEdgeville.contains(Players.local());
    }

    @Override
    public void execute() {
        mMain.State = "Do cooking";
        Component cookWidget = Widgets.widget(270).component(14);

        if (SkillData.cookingStove.inViewport() && !Widgets.widget(270).valid()) {
            SkillData.cookingStove.interact("Cook", "Stove");
            Condition.wait(() -> Widgets.widget(270).valid(), 150,50);
        }
        if(cookWidget.valid()) {
            cookWidget.click();
        }
        Condition.wait(() -> Players.local().animation() == -1, 2000,50);
    }
}