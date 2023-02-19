package Crafting;

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

public class StartCrafting {
    public void Crafting() {
        mMain.runningSkill = "Crafting";
        if (Skills.realLevel(Constants.SKILLS_CRAFTING) >= 70 || skillData.craftingDone) {
            mMain.state = "Crafting done!";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }

        List<Task> craftingTasks = Arrays.asList(
                new goToBank(),
                new BeerGlass(),
                new EmptyOilLamp(),
                new Vial(),
                new EmptyFishBowl(),
                new UnpoweredOrb()
        );

        for (Task task : craftingTasks) {
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
