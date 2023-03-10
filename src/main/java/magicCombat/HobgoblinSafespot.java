package magicCombat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import helpers.CombatHelper;
import helpers.PlayerHelper;
import helpers.extentions.Task;
import script.mMain;

public class HobgoblinSafespot extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_MAGIC) >= 13 && Skills.realLevel(Constants.SKILLS_MAGIC) <= 54;
    }
    @Override
    public boolean execute() {
        if (PlayerHelper.hasItem(MagicData.Runes)
                && !CombatHelper.needEquipment(MagicData.magicEquipment())
                && MagicHelpers.getAutoCastSpell().getSpell() == MagicData.MagicSpell())
        {
            if (!PlayerHelper.withinArea(MagicData.HobgoblinSafeSpotArea)) {
                mMain.state = "Go safespot";
                PlayerHelper.walkToTile(MagicData.HobgoblinSafeSpotArea.getRandomTile());
            }
            if (PlayerHelper.withinArea(MagicData.HobgoblinSafeSpotArea)) {
                ShouldFight();
            }
        }

        return false;
    }

    private void ShouldFight() {
        if (PlayerHelper.withinArea(MagicData.HobgoblinSafeSpotArea) && !Players.local().healthBarVisible()) {
            Npc hobgoblin = PlayerHelper.nearestCombatNpc(MagicData.HobgoblinArea, "Hobgoblin");
            mMain.state = "Attack";
            if (hobgoblin.inViewport() && hobgoblin.interact("Attack")) {
                mMain.state = "Waiting for kill";
                Condition.wait(() -> hobgoblin.healthPercent() == 0 || Players.local().inCombat() || !MagicData.HobgoblinSafeSpotArea.contains(Players.local()), 700, 100);
            }
        }
    }
}
