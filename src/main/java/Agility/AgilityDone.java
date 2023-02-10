package Agility;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class AgilityDone extends Task {
    public boolean activate() {
        return SkillData.AgilityDone;
    }
    @Override
    public void execute() {
            mMain.State = "Agility done!";
            mMain.taskRunning.set(false);
    }
}
