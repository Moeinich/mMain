package Thieving;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class ThievingDone extends Task {
    public boolean activate() {
        return SkillData.thievingDone;
    }
    @Override
    public boolean execute() {
        mMain.State = "Thieving done!";
        mMain.taskRunning.set(false);
        return false;
    }
}