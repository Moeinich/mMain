package Woodcutting;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class WoodcuttingDone extends Task {
    public boolean activate() {
        return SkillData.WoodcuttingDone;
    }
    @Override
    public void execute() {
        mMain.State = "Woodcutting done!";
        mMain.taskRunning.set(false);
    }
}
