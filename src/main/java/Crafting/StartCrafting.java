package Crafting;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.goToBank;
import Helpers.skillData;
import Helpers.Task;
import script.mMain;

public class StartCrafting {
    private ArrayList<Task> craftingTasks = new ArrayList<>();
    public void Crafting() {
        mMain.runningSkill = "Crafting";
        if (Skills.realLevel(Constants.SKILLS_CRAFTING) >= 70) {
            mMain.state = "Crafting done!";
            skillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        if (craftingTasks.isEmpty()) {
            craftingTasks.add(new goToBank());
            craftingTasks.add(new BeerGlass());
            craftingTasks.add(new EmptyOilLamp());
            craftingTasks.add(new Vial());
            craftingTasks.add(new EmptyFishBowl());
            craftingTasks.add(new UnpoweredOrb());
        }

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
