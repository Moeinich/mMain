package Herblore;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class HerbloreDone extends Task {
    public boolean activate() {
        return SkillData.herbloreDone;
    }
    @Override
    public boolean execute() {
        mMain.State = "Herblore done!";
        mMain.taskRunning.set(false);
        return false;
    }
}
