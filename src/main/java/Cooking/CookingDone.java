package Cooking;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class CookingDone extends Task {
    public boolean activate() {
        return SkillData.CookingDone;
    }
    @Override
    public void execute() {
        mMain.State = "Cooking done!";
        mMain.taskRunning.set(false);
    }
}