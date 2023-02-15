package MagicCombat;

import static Helpers.CombatHelper.missingEquipment;
import static MagicCombat.MagicHelpers.isAutoCastOpen;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.dax.api.DaxWalker;
import org.powbot.dax.teleports.Teleport;
import org.powbot.mobile.script.ScriptManager;

import Helpers.CombatHelper;
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
        if (MagicHelpers.getAutoCastSpell().getSpell() != MagicData.MagicSpell() && !MagicHelpers.isAutoCasting()) {
            mMain.state = "Setting spell";
            Game.tab(Game.Tab.ATTACK);
            if (isAutoCastOpen() || MagicHelpers.openAutocastTab()) {
                MagicHelpers.AutoCastSpell[] spellValues = MagicHelpers.AutoCastSpell.values();
                for (MagicHelpers.AutoCastSpell spell : spellValues) {
                    if (spell.getSpell().equals(MagicData.MagicSpell())) {
                        MagicHelpers.setAutoCast(spell);
                    }
                }
            }
        }

        if (CombatHelper.needEquipment(MagicData.MagicEquipment())) {
            mMain.state = "Need equipment!";
            GetEquipment();
        }

        if (!CombatHelper.needEquipment(MagicData.MagicEquipment()) && !PlayerHelper.withinArea(SkillData.CowSafeSpotArea)) {
            mMain.state = "Go safespot";
            PlayerHelper.walkToTile(SkillData.CowSafeSpotArea.getRandomTile());
        }

        if (PlayerHelper.withinArea(SkillData.CowSafeSpotArea)) {
            mMain.state = "Fighting..";
            ShouldFight();
        }
        return false;
    }

    private void ShouldFight() {
        if (PlayerHelper.withinArea(SkillData.CowSafeSpotArea) && !Players.local().healthBarVisible()) {
            Npc cow = PlayerHelper.nearestNpc(SkillData.CowArea, "Cow");
            if (cow.inViewport() && cow.interact("Attack", "Cow")) {
                Condition.wait(() -> !cow.isRendered(),900,20);
            }
            Npc calf = PlayerHelper.nearestNpc(SkillData.CowArea, "Cow calf");
            if (!cow.inViewport() && calf.inViewport() && calf.interact("Attack", "Cow calf")) {
                Condition.wait(() -> !calf.isRendered(),900,20);
            }
        }
    }

    private void GetEquipment() {
        if (Bank.nearest().tile().distanceTo(Players.local()) > 5) {
            mMain.state = "Walking to bank";
            DaxWalker.blacklistTeleports(Teleport.CASTLE_WARS_MINIGAME, Teleport.SOUL_WARS_MINIGAME, Teleport.CLAN_WARS_MINIGAME);
            DaxWalker.walkToBank();
        }
        if (!CombatHelper.gotItems(missingEquipment(MagicData.MagicEquipment()))) {
            mMain.state = "Withdraw equipment";
            if (Bank.nearest().tile().distanceTo(Players.local()) <= 5 && Bank.inViewport()) {
                if (!Bank.opened()) {
                    Bank.open();
                }
                if (Bank.open()) {
                    for (var itemId : missingEquipment(MagicData.MagicEquipment())) {
                        if (Bank.stream().id(itemId).isEmpty()) {
                            if (mMain.runningSkill.equals("Progressive")) {
                                mMain.state = "We ran out of " + itemId;
                                SkillData.setSkillDone();
                                Bank.close();
                                mMain.taskRunning.set(false); //Skip task on progressive
                            } else ScriptManager.INSTANCE.stop();
                        }
                        Bank.withdraw(itemId, Bank.Amount.ALL);
                        Condition.wait(() -> CombatHelper.gotItems(itemId), 100,10);
                    }
                }
                if (CombatHelper.gotItems(missingEquipment(MagicData.MagicEquipment())))
                {
                    Bank.close();
                }
            }
        }
        if (CombatHelper.gotItems(missingEquipment(MagicData.MagicEquipment()))) {
            mMain.state = "Equip items";
            for (var item : missingEquipment(MagicData.MagicEquipment())) {
                var itemToEquip = Inventory.stream().id(item).first();
                if (itemToEquip != null){
                    if (itemToEquip.interact("Wield", itemToEquip.name()))
                    {
                        Condition.wait(() -> CombatHelper.hasEquipped(item), 100, 10);
                    }
                    else
                    {
                        if (itemToEquip.interact("Wear", itemToEquip.name()))
                        {
                            Condition.wait(() -> CombatHelper.hasEquipped(item), 100, 10);
                        }
                    }
                }
            }
        }
    }
}
