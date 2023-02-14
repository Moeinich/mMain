package Agility;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class AgilityDone extends Task {
    public boolean activate() {
        return SkillData.agilityDone;
    }
    @Override
    public boolean execute() {
            mMain.State = "Agility done!";
            mMain.taskRunning.set(false);
        return false;
    }
}
