package runecrafting;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import helpers.SkillData;
import helpers.extentions.Task;
import helpers.questTasks.doAbyssMiniquest;
import helpers.questTasks.doRuneMysteries;
import helpers.questTasks.doTempleOfTheEye;
import script.mMain;

public class startRunecrafting implements mMain.Start{
    public void run() {
        mMain.runningSkill = "runecrafting";
        List<Task> runecraftingTasks = Arrays.asList(
                new doRuneMysteries(),
                new doAbyssMiniquest(),
                new EarthRunes(),
                new doTempleOfTheEye()
        );

        for (Task task : runecraftingTasks) {
            if (Players.local().getCombatLevel() < 20) {
                mMain.state = "Skipping RC, too low combat level for quests";
                mMain.skillRunning.set(false);
            }
            if (Skills.realLevel(Constants.SKILLS_RUNECRAFTING) >= 27 || SkillData.skillsMap.get("runecrafting")) {
                mMain.state = "Runecrafting done!";
                SkillData.setSkillDone();
                mMain.skillRunning.set(false);
            }
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
