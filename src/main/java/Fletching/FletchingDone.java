package Fletching;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class FletchingDone extends Task {
    public boolean activate() {
        return SkillData.FletchingDone;
    }
    @Override
    public void execute() {
        mMain.State = "Fletching done!";
        mMain.taskRunning.set(false);
    }
}
