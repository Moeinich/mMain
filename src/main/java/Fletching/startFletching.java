package Fletching;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.GoToBank;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class startFletching {
    private ArrayList<Task> fletchingTasks = new ArrayList<>();
    public void Fletching() {
        mMain.RunningSkill = "Fletching";
        if (Skills.realLevel(Constants.SKILLS_FLETCHING) >= 70) {
            mMain.State = "Fletching done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        if (fletchingTasks.isEmpty()) {
            fletchingTasks.add(new FletchingDone());
            fletchingTasks.add(new GoToBank());
            fletchingTasks.add(new ArrowShafts());
            fletchingTasks.add(new Longbow());
            fletchingTasks.add(new OakShortbow());
            fletchingTasks.add(new OakLongbow());
            fletchingTasks.add(new WillowShortbow());
            fletchingTasks.add(new WillowLongbow());
            fletchingTasks.add(new MapleShortbow());
            fletchingTasks.add(new MapleLongbow());
        }

        for (Task task : fletchingTasks) {
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
