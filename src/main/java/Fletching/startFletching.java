package Fletching;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Helpers.goToBank;
import Helpers.skillData;
import Helpers.Task;
import Herblore.AttackPotions;
import script.mMain;

public class startFletching {
    public void Fletching() {
        mMain.runningSkill = "Fletching";
        if (Skills.realLevel(Constants.SKILLS_FLETCHING) >= 70 || skillData.fletchingDone) {
            mMain.state = "Fletching done!";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> fletchingTasks = Arrays.asList(
                new goToBank(),
                new ArrowShafts(),
                new Longbow(),
                new OakShortbow(),
                new OakLongbow(),
                new WillowShortbow(),
                new WillowLongbow(),
                new MapleShortbow(),
                new MapleLongbow()
        );

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
