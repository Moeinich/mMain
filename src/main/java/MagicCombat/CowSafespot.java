package MagicCombat;

import static MagicCombat.MagicHelpers.isAutoCastOpen;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.CombatHelper;
import Helpers.InteractionsHelper;
import Helpers.ItemList;
import Helpers.PlayerHelper;
import Helpers.SkillData;
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
        if (CombatHelper.needEquipment(MagicData.MagicEquipment())) {
            mMain.state = "Need equipment";
            CombatHelper.gearUp(MagicData.MagicEquipment());
        } else if (!CombatHelper.needEquipment(MagicData.MagicEquipment()) && !PlayerHelper.hasItem(MagicData.Runes)) {
            mMain.state = "Getting runes";
            if (!Bank.opened()) {
                Bank.open();
                Condition.wait(Bank::open, 250, 5);
            }
            InteractionsHelper.depositAndWithdraw(ItemList.AIR_RUNE_556, 2000);
            Condition.wait(() -> PlayerHelper.hasItem(ItemList.AIR_RUNE_556), 150, 50);
            InteractionsHelper.withdrawItem(ItemList.MIND_RUNE_558, 2000);
            Condition.wait(() -> PlayerHelper.hasItem(ItemList.MIND_RUNE_558), 150, 50);
            Bank.close();
        }

        //Set autocast spell
        if (MagicHelpers.getAutoCastSpell().getSpell() != MagicData.MagicSpell() && !MagicHelpers.isAutoCasting() && PlayerHelper.hasItem(MagicData.Runes)) {
            mMain.state = "Setting spell";
            Game.tab(Game.Tab.ATTACK);
            if (isAutoCastOpen() || MagicHelpers.openAutocastTab()) {
                MagicHelpers.AutoCastSpell[] spellValues = MagicHelpers.AutoCastSpell.values();
                for (MagicHelpers.AutoCastSpell spell : spellValues) {
                    if (spell.getSpell() != null && spell.getSpell().equals(MagicData.MagicSpell())) {
                        System.out.println("Set autocast spell");
                        MagicHelpers.setAutoCast(spell);
                        break; // exit loop once spell is set
                    }
                }
            }
        }

        if (PlayerHelper.hasItem(MagicData.Runes) && !CombatHelper.needEquipment(MagicData.MagicEquipment()) && MagicHelpers.getAutoCastSpell().getSpell() == MagicData.MagicSpell()) {
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
                Condition.wait(() -> cow.healthPercent() == 0,900,20);
            }
        }
    }
}
