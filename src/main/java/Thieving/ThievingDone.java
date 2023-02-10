package Thieving;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class ThievingDone extends Task {
    public boolean activate() {
        return SkillData.ThievingDone;
    }
    @Override
    public void execute() {
        mMain.State = "Thieving done!";
        mMain.taskRunning.set(false);
    }
}