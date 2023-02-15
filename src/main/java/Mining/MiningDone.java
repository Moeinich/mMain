package Mining;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class MiningDone extends Task {
    public boolean activate() {
        return SkillData.miningDone;
    }
    @Override
    public boolean execute() {
        mMain.state = "Mining done!";
        mMain.taskRunning.set(false);
        return false;
    }
}
