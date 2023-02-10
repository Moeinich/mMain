package Fishing;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class FishingDone extends Task {
    public boolean activate() {
        return SkillData.FishingDone;
    }
    @Override
    public void execute() {
        mMain.State = "Fishing done!";
        mMain.taskRunning.set(false);
    }
}
