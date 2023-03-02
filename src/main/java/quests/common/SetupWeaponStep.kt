package quests.common

import magicCombat.MagicHelpers
import org.powbot.api.Condition
import org.powbot.api.rt4.*
import quests.common.base.BaseQuestStep
import helpers.extentions.Conditions
import quests.common.models.QuestInformation
import java.util.concurrent.Callable

class SetupWeaponStep(val information: QuestInformation, val shouldExecute: Callable<Boolean>? = null) : BaseQuestStep() {

    private fun setupWeapon(): Any {
        if (Bank.opened() && !Bank.close()) {
            return false
        }
        val currentWeapon = Equipment.itemAt(Equipment.Slot.MAIN_HAND)
        if (currentWeapon.name() != information.weaponName!!) {
            Game.tab(Game.Tab.INVENTORY)
            val weapon = Inventory.stream().name(information.weaponName).first()
            logger.info("SetupWeaponStep: Wielding weapon ${information.weaponName}")
            if (weapon.interact("Wield")) {
                Condition.wait(Conditions.waitUntilItemLeavesInventory(information.weaponName, 1))
            } else {
                return false
            }
        }


        if (information.spell != null && !MagicHelpers.isAutoCasting()) {
            Game.tab(Game.Tab.ATTACK)
            logger.info("Setting spell ${information.spell!!.name}")
            if (MagicHelpers.isAutoCastOpen() || MagicHelpers.openAutocastTab()) {
                val spell = MagicHelpers.AutoCastSpell.values().first { it.spell == information.spell }
                return MagicHelpers.setAutoCast(spell)
            } else {
                return false
            }
        }

        return true
    }

    override fun shouldExecute(): Boolean {
        return !alreadySetup() && (shouldExecute == null || shouldExecute.call())
    }

    private fun alreadySetup() : Boolean{
        return information.weaponName == Equipment.itemAt(Equipment.Slot.MAIN_HAND).name() && (information.spell == null || MagicHelpers.isAutoCasting())
    }

    override fun run() {
        setupWeapon()
    }

    override fun stepName(): String {
        return "Setting up for combat"
    }
}