package RangedCombat;

import static Helpers.CombatHelper.missingEquipment;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Combat;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
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
        return Skills.realLevel(Constants.SKILLS_RANGE) <= 29 && Skills.realLevel(Constants.SKILLS_DEFENSE) <= 29;
    }
    @Override
    public boolean execute() {
        if (!Combat.style(Combat.Style.DEFENSIVE)) {
            mMain.State = "Setting cb mode";
            PlayerHelper helper = new PlayerHelper();
            helper.setAttackMode(Combat.Style.DEFENSIVE);
        }

        if (CombatHelper.needEquipment(RangeData.RangeEquipment())) {
            mMain.State = "Need equipment!";
            GetEquipment();
        }

        if (!CombatHelper.needEquipment(RangeData.RangeEquipment()) && !PlayerHelper.withinArea(SkillData.CowSafeSpotArea)) {
            mMain.State = "Go safespot";
            PlayerHelper.walkToTile(SkillData.CowSafeSpotArea.getRandomTile());
        }

        if (PlayerHelper.withinArea(SkillData.CowSafeSpotArea)) {
            mMain.State = "Fighting..";
            ShouldFight();
        }
        return false;
    }

    private void ShouldFight() {
        if (PlayerHelper.withinArea(SkillData.CowSafeSpotArea) && !Players.local().healthBarVisible()) {
            Npc cow = Npcs.stream().name("Cow").within(SkillData.CowArea).nearest().first();
            if (cow.inViewport() && cow.interact("Attack", "Cow")) {
                Condition.wait(() -> !cow.isRendered(),900,20);
            }
            Npc calf = Npcs.stream().name("Cow calf").within(SkillData.CowArea).nearest().first();
            if (!cow.inViewport() && calf.inViewport() && calf.interact("Attack", "Cow")) {
                Condition.wait(() -> !calf.isRendered(),900,20);
            }
        }
    }

    private void GetEquipment() {
        if (Bank.nearest().tile().distanceTo(Players.local()) > 5) {
            mMain.State = "Walking to bank";
            DaxWalker.blacklistTeleports(Teleport.CASTLE_WARS_MINIGAME, Teleport.SOUL_WARS_MINIGAME, Teleport.CLAN_WARS_MINIGAME);
            DaxWalker.walkToBank();
        }
        if (!CombatHelper.gotItems(missingEquipment(RangeData.RangeEquipment()))) {
            mMain.State = "Withdraw equipment";
            if (Bank.nearest().tile().distanceTo(Players.local()) <= 5 && Bank.inViewport()) {
                if (!Bank.opened()) {
                    Bank.open();
                }
                if (Bank.open()) {
                    for (var itemId : missingEquipment(RangeData.RangeEquipment())) {
                        if (Bank.stream().id(itemId).isEmpty()) {
                            if (mMain.RunningSkill.equals("Progressive")) {
                                mMain.State = "We ran out of " + itemId;
                                SkillData.setSkillDone();
                                Bank.close();
                                mMain.taskRunning.set(false); //Skip task on progressive
                            } else ScriptManager.INSTANCE.stop();
                        }
                        Bank.withdraw(itemId, Bank.Amount.ALL);
                        Condition.wait(() -> CombatHelper.gotItems(itemId), 100,10);
                    }
                }
                if (CombatHelper.gotItems(missingEquipment(RangeData.RangeEquipment())))
                {
                    Bank.close();
                }
            }
        }
        if (CombatHelper.gotItems(missingEquipment(RangeData.RangeEquipment()))) {
            mMain.State = "Equip items";
            for (var item : missingEquipment(RangeData.RangeEquipment())) {
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
