package Fishing;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class FishingDone extends Task {
    public boolean activate() {
        return SkillData.fishingDone;
    }
    @Override
    public boolean execute() {
        mMain.State = "Fishing done!";
        mMain.taskRunning.set(false);
        return false;
    }
}
