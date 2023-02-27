package MagicCombat;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;
import org.powbot.mobile.script.ScriptManager;

import java.util.Arrays;
import java.util.List;

import Helpers.BankBeforeTask;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class StartMagic {
    public void Magic() {
        mMain.runningSkill = "Magic";
        List<Task> magicTasks = Arrays.asList(
                new BankBeforeTask(),
                new GetMagicEquipment(),
                new SetAutoCastSpell(),
                new CowSafespot()
        );

        for (Task task : magicTasks) {
            if (Skills.realLevel(Constants.SKILLS_MAGIC) >= 55 || SkillData.magicCombatDone) {
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