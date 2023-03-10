package helpers.questTasks;

import org.powbot.api.Random;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Varpbits;
import org.powbot.api.rt4.walking.model.Skill;

import helpers.extentions.Task;
import quests.QuestInitializer;
import quests.common.QuestVarpbits;
import script.mMain;

public class DoPriestInPeril extends Task {
    @Override
    public boolean activate() {
        return Varpbits.varpbit(QuestVarpbits.PRIEST_IN_PERIL.getQuestVarbit()) != QuestVarpbits.PRIEST_IN_PERIL.getFinishedValue()
                && Skills.realLevel(Skill.Defence) >= 30
                && Skills.realLevel(Skill.Attack) >= 30
                && Skills.realLevel(Skill.Strength) >= 30;
    }
    @Override
    public boolean execute() {
        mMain.state = "Quest: " + QuestVarpbits.PRIEST_IN_PERIL.getQuestName();
        if (mMain.runtime.timeLeft() <= 30000) {
            System.out.println("Runtime reset due to Priest in Peril");
            mMain.runtime.reset(Random.nextInt(mMain.MIN_TIME_LIMIT, mMain.MAX_TIME_LIMIT));

        }        QuestInitializer.priestInPeril.run();
        return false;
    }
}