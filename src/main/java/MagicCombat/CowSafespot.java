package MagicCombat;

import static MagicCombat.MagicHelpers.isAutoCastOpen;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.combatHelper;
import Helpers.interactionHelper;
import Helpers.ItemList;
import Helpers.playerHelper;
import Helpers.skillData;
import Helpers.Task;
import script.mMain;

public class CowSafespot extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_MAGIC) <= 29 && Skills.realLevel(Constants.SKILLS_DEFENSE) <= 29;
    }
    @Override
    public boolean execute() {
        //Get equipment
        if (combatHelper.needEquipment(MagicData.MagicEquipment())) {
                mMain.state = "Need equipment";
                combatHelper.gearUp(MagicData.MagicEquipment());
        }
        if (!combatHelper.needEquipment(MagicData.MagicEquipment()) && !playerHelper.hasItem(MagicData.Runes)) {
            mMain.state = "Getting runes";
            if (!Bank.opened()) {
                Bank.open();
                Condition.wait(Bank::open, 250, 5);
            }
            interactionHelper.depositAndWithdraw(ItemList.AIR_RUNE_556, 2000);
            interactionHelper.withdrawItem(ItemList.MIND_RUNE_558, 2000);
            Condition.wait( () -> playerHelper.hasItem(ItemList.MIND_RUNE_558, ItemList.AIR_RUNE_556), 150, 50);
            Bank.close();
        }

        //Set autocast spell
        if (MagicHelpers.getAutoCastSpell().getSpell() != MagicData.MagicSpell() && !MagicHelpers.isAutoCasting() && playerHelper.hasItem(MagicData.Runes)) {
            mMain.state = "Setting spell";
            Game.tab(Game.Tab.ATTACK);
            if (isAutoCastOpen() || MagicHelpers.openAutocastTab()) {
                MagicHelpers.AutoCastSpell[] spellValues = MagicHelpers.AutoCastSpell.values();
                for (MagicHelpers.AutoCastSpell spell : spellValues) {
                    if (spell.getSpell() != null && spell.getSpell().equals(MagicData.MagicSpell())) {
                        System.out.println("Set autocast spell");
                        MagicHelpers.setAutoCast(spell);
                    }
                }
            }
        }

        if (playerHelper.hasItem(ItemList.MIND_RUNE_558, ItemList.AIR_RUNE_556) && !combatHelper.needEquipment(MagicData.MagicEquipment()) && !playerHelper.withinArea(skillData.CowSafeSpotArea)) {
            mMain.state = "Go safespot";
            playerHelper.walkToTile(skillData.CowSafeSpotArea.getRandomTile());
        }

        if (playerHelper.withinArea(skillData.CowSafeSpotArea) && MagicHelpers.getAutoCastSpell().getSpell() == MagicData.MagicSpell()) {
            ShouldFight();
        }
        return false;
    }

    private void ShouldFight() {
        if (playerHelper.withinArea(skillData.CowSafeSpotArea) && !Players.local().healthBarVisible()) {
            Npc cow = playerHelper.nearestCombatNpc(skillData.CowArea, "Cow", "Cow calf");
            mMain.state = "Attack";
            if (cow.inViewport() && cow.interact("Attack")) {
                mMain.state = "Waiting for kill";
                Condition.wait(() -> cow.healthPercent() == 0,900,20);
            }
        }
    }
}
