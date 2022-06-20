package src.PastShadie.scripts.mMain.Smithing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Widgets;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

public class doProducts extends Task {
    @Override
    public boolean activate() {
        return false;
    }


    @Override
    public void execute() {
        mMain.scriptStatus = "Do products";
        Component furnaceWidget = Widgets.widget(270).component(14);

        if (skillData.furnaceEdgeville.inViewport() && !Widgets.widget(270).valid()) {
            skillData.furnaceEdgeville.interact("Smith", "Furnace");
            Condition.wait(() -> Widgets.widget(270).valid(), 150,50);
        }
        if(furnaceWidget.valid()) {
            furnaceWidget.click();
        }
        Condition.wait(() -> Players.local().animation() == -1, 2000,50);
    }
}