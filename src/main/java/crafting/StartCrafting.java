package crafting;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import helpers.tasks.GoToBank;
import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

public class StartCrafting implements mMain.Start{
    public void run() {
        mMain.runningSkill = "crafting";
        List<Task> craftingTasks = Arrays.asList(
                new GoToBank(),
                new BeerGlass(),
                new EmptyOilLamp(),
                new Vial(),
                new EmptyFishBowl(),
                new UnpoweredOrb()
        );

        for (Task task : craftingTasks) {
            if (Skills.realLevel(Constants.SKILLS_CRAFTING) >= 70 || SkillData.skillsMap.get("crafting")) {
                mMain.state = "Crafting done!";
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
