package Cooking;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class CookingDone extends Task {
    public boolean activate() {
        return SkillData.cookingDone;
    }
    @Override
    public boolean execute() {
        mMain.State = "Cooking done!";
        mMain.taskRunning.set(false);
        return false;
    }
}