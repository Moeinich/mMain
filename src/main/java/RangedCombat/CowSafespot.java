package RangedCombat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Combat;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.PlayerHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class CowSafespot extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_RANGE) <= 29;
    }
    @Override
    public void execute() {
        if (!Combat.style(Combat.Style.AGGRESSIVE)) {
            mMain.State = "Setting cb mode";
            PlayerHelper helper = new PlayerHelper();
            helper.SetAttackMode(Combat.Style.AGGRESSIVE);
        }
        if (Skills.level(Constants.SKILLS_HITPOINTS) < 5) {
            mMain.State = "Eating..";
            PlayerHelper helper = new PlayerHelper();
            helper.ShouldEat();
        }
        if (!SkillData.RangeSafespot.contains(Players.local())) {
            PlayerHelper.WalkToTile(SkillData.RangeSafespot.getRandomTile());
        }
        if (SkillData.RangeSafespot.contains(Players.local())) {
            ShouldFight();
        }
    }

    private void ShouldFight() {
        Npc cow = Npcs.stream().name("Cow").within(SkillData.CowArea).nearest().first();
        Npc calf = Npcs.stream().name("Cow calf").within(SkillData.CowArea).nearest().first();

        if (SkillData.RangeSafespot.contains(Players.local()) && !Players.local().healthBarVisible()) {
            if (cow.inViewport() && cow.interact("Attack", "Cow")) {
                Condition.wait(() -> !cow.valid(),900,20);
            }
            if (!cow.inViewport() && calf.inViewport() && calf.interact("Attack", "Cow")) {
                Condition.wait(() -> !calf.valid(),900,20);
            }
        }
    }
}
