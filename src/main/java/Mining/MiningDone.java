package Mining;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class MiningDone extends Task {
    public boolean activate() {
        return SkillData.MiningDone;
    }
    @Override
    public void execute() {
        mMain.State = "Mining done!";
        mMain.taskRunning.set(false);
    }
}
