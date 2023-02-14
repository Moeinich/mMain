package Thieving;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.SkillData;
import Helpers.Task;
import Helpers.BankBeforeTask;
import script.mMain;

public class StartThieving {
    private ArrayList<Task> thievingTasks = new ArrayList<>();
    public void Thieving() {
        mMain.RunningSkill = "Thieving";
        if (Skills.realLevel(Constants.SKILLS_THIEVING) >= 60) {
            mMain.State = "Thieving done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        if (thievingTasks.isEmpty()) {
            thievingTasks.add(new ThievingDone());
            thievingTasks.add(new BankBeforeTask());
            thievingTasks.add(new ThievingMen());
            thievingTasks.add(new TeaStall());
            thievingTasks.add(new FruitStall());
            thievingTasks.add(new ThievingDone());
        }

        for (Task task : thievingTasks) {
            if (task.activate()) {
                task.execute();
                if (ScriptManager.INSTANCE.isStopping()) {
                    ScriptManager.INSTANCE.stop();
                    break;
                }
                return;
            }
        }
    }
}
