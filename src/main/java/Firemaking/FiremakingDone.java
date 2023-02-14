package Firemaking;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class FiremakingDone extends Task {
    public boolean activate() {
        return SkillData.firemakingDone;
    }
    @Override
    public boolean execute() {
        mMain.State = "Firemaking done!";
        mMain.taskRunning.set(false);
        return false;
    }
}
