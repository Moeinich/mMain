package Quests.QuestData;

import org.powbot.api.event.InventoryChangeEvent;
import org.powbot.api.event.MessageEvent;
import org.powbot.mobile.drawing.Rendering;

public class BaseQuestStep {
    public BaseQuestStep() {
        init();
    }
    public boolean shouldExecute() {
        return false;
    }

    public boolean execute() {
        if (shouldExecute()) {
            run();
            return true;
        }
        return false;
    }

    public void run() {

    }

    protected void init() {
    }

    public String stepName() {
        return null;
    }

    public void draw(Rendering g) {

    }

    public void messageReceived(MessageEvent me) {

    }

    public void inventoryChanged(InventoryChangeEvent me) {

    }

}
