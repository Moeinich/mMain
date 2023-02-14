package Fletching;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class FletchingDone extends Task {
    public boolean activate() {
        return SkillData.fletchingDone;
    }
    @Override
    public boolean execute() {
        mMain.State = "Fletching done!";
        mMain.taskRunning.set(false);
        return false;
    }
}
