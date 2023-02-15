package Crafting;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class CraftingDone extends Task {
    public boolean activate() {
        return SkillData.craftingDone;
    }
    @Override
    public boolean execute() {
        mMain.state = "Crafting done!";
        mMain.taskRunning.set(false);
        return false;
    }
}
