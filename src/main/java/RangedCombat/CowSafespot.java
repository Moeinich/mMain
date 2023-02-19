package RangedCombat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Combat;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Skills;

import Helpers.combatHelper;
import Helpers.playerHelper;
import Helpers.skillData;
import Helpers.Task;
import script.mMain;

public class CowSafespot extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_RANGE) <= 29 && Skills.realLevel(Constants.SKILLS_DEFENSE) <= 29;
    }
    @Override
    public boolean execute() {
        if (!Combat.style(Combat.Style.DEFENSIVE)) {
            mMain.state = "Setting cb mode";
            playerHelper.setAttackMode(Combat.Style.DEFENSIVE);
        }

        if (combatHelper.needEquipment(RangeData.RangeEquipment())) {
            mMain.state = "Need equipment!";
            combatHelper.gearUp(RangeData.RangeEquipment());
        }

        if (!combatHelper.needEquipment(RangeData.RangeEquipment()) && !playerHelper.withinArea(skillData.CowSafeSpotArea)) {
            mMain.state = "Go safespot";
            playerHelper.walkToTile(skillData.CowSafeSpotArea.getRandomTile());
        }

        if (playerHelper.withinArea(skillData.CowSafeSpotArea)) {
            ShouldFight();
        }
        return false;
    }

    private void ShouldFight() {
        Npc cow = playerHelper.nearestCombatNpc(skillData.CowArea, "Cow", "Cow calf");
        mMain.state = "Attack";
        if (cow.inViewport()) {
            if (cow.interact("Attack")) {
                mMain.state = "Waiting for kill";
                Condition.wait(() -> cow.healthPercent() == 0,900,20);
            }
        }
    }
}
