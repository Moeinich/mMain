package Thieving;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import Helpers.SkillData;
import Helpers.Task;
import Helpers.BankBeforeTask;
import script.mMain;

public class StartThieving {
    public void Thieving() {
        mMain.runningSkill = "Thieving";
        if (Skills.realLevel(Constants.SKILLS_THIEVING) >= 60 || SkillData.thievingDone) {
            mMain.state = "Thieving done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> thievingTasks = Arrays.asList(
                new BankBeforeTask(),
                new ThievingMen(),
                new TeaStall(),
                new FruitStall()
        );

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
