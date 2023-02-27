package Quests.QuestData;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Component;
import org.powbot.api.rt4.Widget;
import org.powbot.api.rt4.Widgets;

public class CommonMethods {
    public static boolean isInCutscene() {
        Widget i = Widgets.widget(601);
        for (Component c : i.components()) {
            if (c.textureId() == 900 && c.visible()) {
                return false;
            }
        }
        return true;
    }
    public static boolean closeQuestComplete() {
        Component closeComponent = Widgets.component(153, 17);
        if (!closeComponent.valid()) {
            return true;
        }
        if (closeComponent.textureId() == 537 && closeComponent.click()) {
            Condition.wait(() -> !Widgets.component(537, 17).valid(), Random.nextInt(2500, 3500), 100);
        }
        return false;
    }
}
