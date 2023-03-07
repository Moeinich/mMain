package rangedCombat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Combat;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

import helpers.PlayerHelper;
import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

public class CowSafespot extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_RANGE) <= 29 || Skills.realLevel(Constants.SKILLS_DEFENSE) <= 29;
    }
    @Override
    public boolean execute() {
        if (Skills.realLevel(Skill.Defence) < 30) {
            if (!Combat.style(Combat.Style.DEFENSIVE)) {
                mMain.state = "Setting cb mode";
                PlayerHelper.setAttackMode(Combat.Style.DEFENSIVE);
            }
        } else if (Skills.realLevel(Skill.Defence) >= 30) {
            Combat.style(Combat.Style.AGGRESSIVE);
        }
        if (!PlayerHelper.withinArea(SkillData.CowSafeSpotArea)) {
            mMain.state = "Go safespot";
            PlayerHelper.walkToTile(SkillData.CowSafeSpotArea.getRandomTile());
        }
        if (PlayerHelper.withinArea(SkillData.CowSafeSpotArea)) {
            ShouldFight();
        }
        return false;
    }

    private void ShouldFight() {
        Npc cow = PlayerHelper.nearestCombatNpc(SkillData.CowArea, "Cow", "Cow calf");
        mMain.state = "Attack";
        if (cow.inViewport()) {
            if (cow.interact("Attack")) {
                mMain.state = "Waiting for kill";
                Condition.wait(() -> cow.healthPercent() == 0,900,100);
            }
        }
    }
}
