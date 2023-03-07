package magicCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import helpers.tasks.BankBeforeTask;
import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

public class StartMagic implements mMain.Start{
    public void start() {
        mMain.runningSkill = "magic";
        List<Task> magicTasks = Arrays.asList(
                new BankBeforeTask(),
                new GetMagicEquipment(),
                new SetAutoCastSpell(),
                new CowSafespot(),
                new HobgoblinSafespot()
        );

        for (Task task : magicTasks) {
            if (Skills.realLevel(Constants.SKILLS_MAGIC) >= 55 || SkillData.skillsMap.get("magic")) {
                mMain.state = "Magic done!";
                SkillData.setSkillDone();
                mMain.taskRunning.set(false);
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