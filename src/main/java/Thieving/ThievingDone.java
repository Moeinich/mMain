package Thieving;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;

import Helpers.Task;
import script.mMain;

public class ThievingDone extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_THIEVING) >= 60;
    }

    @Override
    public void execute() {
        mMain.taskRunning.set(false);
    }
}
