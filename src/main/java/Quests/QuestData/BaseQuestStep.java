package Quests.QuestData;

import org.powbot.api.event.InventoryChangeEvent;
import org.powbot.api.event.MessageEvent;
import org.powbot.mobile.drawing.Rendering;

public class BaseQuestStep {
    public BaseQuestStep() {
        init();
    }

    /**
     * @return Returns whether a step should execute or not
     */
    public boolean shouldExecute() {
        return false;
    }

    /**
     * Executes the step if possible
     *
     * @return true when the task was executed
     */
    public boolean execute() {
        if (shouldExecute()) {
            run();
            return true;
        }
        return false;
    }

    /**
     * Main method to run for the script
     *
     */
    public void run() {

    }

    /**
     * Do things required to be done on initialization
     */
    protected void init() {
    }

    /**
     * @return Text used to describe step you wish to display on the paint.
     */
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
