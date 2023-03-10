package magicCombat;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import helpers.CombatHelper;
import helpers.PlayerHelper;
import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

public class CowSafespot extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_MAGIC) <= 12;
    }
    @Override
    public boolean execute() {
        if (PlayerHelper.hasItem(MagicData.Runes)
                && !CombatHelper.needEquipment(MagicData.magicEquipment())
                && MagicHelpers.getAutoCastSpell().getSpell() == MagicData.MagicSpell())
        {
            if (!PlayerHelper.withinArea(SkillData.CowSafeSpotArea)) {
                mMain.state = "Go safespot";
                PlayerHelper.walkToTile(SkillData.CowSafeSpotArea.getRandomTile());
            }
            if (PlayerHelper.withinArea(SkillData.CowSafeSpotArea)) {
                ShouldFight();
            }
        }

        return false;
    }

    private void ShouldFight() {
        if (PlayerHelper.withinArea(SkillData.CowSafeSpotArea) && !Players.local().healthBarVisible()) {
            Npc cow = PlayerHelper.nearestCombatNpc(SkillData.CowArea, "Cow", "Cow calf");
            mMain.state = "Attack";
            if (cow.inViewport() && cow.interact("Attack")) {
                mMain.state = "Waiting for kill";
                Condition.wait(() -> cow.healthPercent() == 0,900,100);
                Condition.sleep(Random.nextInt(250, 300));
            }
        }
    }
}
