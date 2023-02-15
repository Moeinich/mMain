package Woodcutting;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class WoodcuttingDone extends Task {
    public boolean activate() {
        return SkillData.woodcuttingDone;
    }
    @Override
    public boolean execute() {
        mMain.state = "Woodcutting done!";
        mMain.taskRunning.set(false);
        return false;
    }
}
