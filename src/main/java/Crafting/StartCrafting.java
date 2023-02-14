package Crafting;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.ArrayList;

import Helpers.GoToBank;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class StartCrafting {
    private ArrayList<Task> craftingTasks = new ArrayList<>();
    public void Crafting() {
        mMain.RunningSkill = "Crafting";
        if (Skills.realLevel(Constants.SKILLS_CRAFTING) >= 70) {
            mMain.State = "Crafting done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        if (craftingTasks.isEmpty()) {
            craftingTasks.add(new CraftingDone());
            craftingTasks.add(new GoToBank());
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
