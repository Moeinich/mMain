package Crafting;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class CraftingDone extends Task {
    public boolean activate() {
        return SkillData.CraftingDone;
    }
    @Override
    public boolean execute() {
        mMain.State = "Crafting done!";
        mMain.taskRunning.set(false);
        return false;
    }
}
