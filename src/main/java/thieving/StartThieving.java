package thieving;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import helpers.SkillData;
import helpers.extentions.Task;
import helpers.tasks.BankBeforeTask;
import script.mMain;

public class StartThieving implements mMain.Start{
    public void run() {
        mMain.runningSkill = "thieving";
        List<Task> thievingTasks = Arrays.asList(
                new BankBeforeTask(),
                new ThievingMen(),
                new TeaStall(),
                new FruitStall()
        );

        for (Task task : thievingTasks) {
            if (Skills.realLevel(Constants.SKILLS_THIEVING) >= 60 || SkillData.skillsMap.get("thieving")) {
                mMain.state = "Thieving done!";
                SkillData.setSkillDone();
                mMain.skillRunning.set(false);
            }
            else if (task.activate()) {
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
