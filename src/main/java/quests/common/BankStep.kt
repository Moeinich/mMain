package quests.common

import helpers.extentions.count
import helpers.extentions.itemEquipped
import org.powbot.api.Notifications
import org.powbot.api.Tile
import org.powbot.api.requirement.RunePowerRequirement
import org.powbot.api.rt4.*
import org.powbot.mobile.script.ScriptManager
import quests.common.base.BaseQuestStep
import quests.common.helpers.CombatHelper
import quests.common.models.ItemRequirement
import quests.common.models.ItemRequirementCondition
import quests.common.models.QuestInformation
import quests.common.models.SetupResult
import quests.common.tasks.SetupTask
import java.util.concurrent.Callable

class BankStep(
    var conditions: List<ItemRequirementCondition>,
    private val bankTile: Tile,
    val questInformation: QuestInformation,
    val shouldExecute: Callable<Boolean>? = null,
    private val combat: Boolean = false,
    private val foodRequired: Boolean = false
) : BaseQuestStep() {

    private var setup = false
    private val setupTask: SetupTask by lazy {
        logger.info("Creating setup task")
        SetupTask(conditions, listOf())
    }
    private var itemsToKeep = arrayOf<String>()

    var hasBeenSetup = false

    private fun calculateInventory() {
        val updatedConditions = conditions.toMutableList()
        val calculatedItemsToKeep = conditions
            .filter {
                it.chosenRequirement!!.name.isNotEmpty()
            }
            .map { it.chosenRequirement!!.name }.toMutableList()

        if (combat && questInformation.spell != null) {
            addMagicReq(updatedConditions, calculatedItemsToKeep)
        }
        if (combat && questInformation.weaponName != null && !Equipment.itemEquipped(questInformation.weaponName)) {
            logger.info("Weapon is required ${questInformation.weaponName}")
            calculatedItemsToKeep.add(questInformation.weaponName)
            val requirement = ItemRequirement(questInformation.weaponName, false, 1)
            val itemRequirementCondition = ItemRequirementCondition(requirement)
            itemRequirementCondition.chosenRequirement = requirement
            updatedConditions.add(itemRequirementCondition)
        }
        if (foodRequired) {
            val foodName = questInformation.foodName.first()
            logger.info("Food is required $foodName")
            calculatedItemsToKeep.add(foodName)

            val usedSlots = updatedConditions.filter {
                it.chosenRequirement != null
            }.sumOf { if (it.chosenRequirement!!.stackable) 1 else it.chosenRequirement!!.countRequired }
            val foodToWithdraw = 20 - usedSlots
            logger.info("Food required $foodToWithdraw")
            val requirement = ItemRequirement(foodName, true, foodToWithdraw)
            val itemRequirementCondition = ItemRequirementCondition(requirement)
            itemRequirementCondition.chosenRequirement = requirement
            updatedConditions.add(itemRequirementCondition)
        }
        itemsToKeep = calculatedItemsToKeep.toList().toTypedArray()
        conditions = updatedConditions.filter { it.chosenRequirement!!.name.isNotEmpty() }.toList()

        itemsToKeep.forEach {
            logger.info("Items to keep $it")
        }
        conditions.forEach {
            logger.info("Condition ${it.chosenRequirement!!.name}")
        }
        logger.info("Updated calculated inventory")
        logger.info("Missing items ${conditions.filter { it.chosenRequirement == null }.joinToString(",")}")
    }

    private fun addMagicReq(
        conditions: MutableList<ItemRequirementCondition>,
        itemsToKeep: MutableList<String>
    ): MutableList<ItemRequirementCondition> {
        questInformation.spell!!.requirements.forEach {
            if (it is RunePowerRequirement) {
                val runeName = it.power.getFirstRune().name.lowercase() + " rune"
                logger.info("Checking rune ${runeName}.")
                itemsToKeep.add(runeName)
                val itemRequirement = ItemRequirement(
                    runeName, false, it.amount * 100,
                    arrayOf(), true
                )
                val condition = ItemRequirementCondition(itemRequirement)
                condition.chosenRequirement = itemRequirement
                conditions.add(condition)
                logger.info("Added requirement for ${it.amount * 100} ${runeName}.")
            }
        }
        return conditions
    }

    override fun shouldExecute(): Boolean {
        return !hasBeenSetup && (shouldExecute == null || shouldExecute.call())
    }

    override fun run() {
        if (GrandExchange.opened() && !GrandExchange.close()) {
            return
        }

        if (!setup && !setupConditions()) {
            return
        }

        if (hasRequirements()) {
            logger.info("Inventory has been setup properly.")
            hasBeenSetup = true
            return
        }

        if (Bank.opened()) {
            setupInventory()
        } else if (bankTile.distanceTo(Players.local()) < 3) {
            Bank.open()
        } else {
            Movement.builder(bankTile)
                .setRunMax(10)
                .setRunMax(60)
                .setWalkUntil {
                    if (CombatHelper.shouldEat(*questInformation.foodName)) {
                        CombatHelper.eatFood(*questInformation.foodName)
                    }
                    // TODO Check if needs to eat here when doing combat quests.
                    bankTile.distanceTo(Players.local()) < 3
                }
                .move()
        }
    }

    private fun setupInventory() {
        if (Bank.depositAllExcept(*itemsToKeep)) {
            val requirements = conditions.map { it.chosenRequirement!! }.toList()
            requirements.forEach { r ->
                val chosenItem = Inventory.stream().name(r.name).count(true)
                if (chosenItem >= r.countRequired) {
                    logger.info("BS: Has required item ${r.name}")
                    return@forEach
                }
                val missingCount = r.countRequired - chosenItem.toInt()
                if (Bank.stream().name(r.name).count(true) < missingCount) {
                    logger.info("Missing $missingCount ${r.name}")
                    Notifications.showNotification("Missing $missingCount ${r.name}")
                    ScriptManager.stop()
                }
                Bank.withdraw(r.name, missingCount)
            }
        }
    }

    /**
     *   Checks if it has all the requirements already
     */
    private fun hasRequirements(): Boolean {
        if (!setup) {
            return false
        }

        // TODO Find out why all wasn't working??
        conditions.forEach {
            if (it.chosenRequirement == null) {
                return false
            }
            val requirement = it.chosenRequirement!!

            logger.info("Checking requirement ${requirement.name}")
            if (Inventory.count(requirement.name) < requirement.countRequired) {
                return false
            }
        }
        return true
    }

    /**
     *  Sets up the bank conditions so we know what to withdraw for the specific conditions,
     *  @return true if we successfully setup
     */
    private fun setupConditions(): Boolean {
        var canCalculate = conditions.all { it.chosenRequirement != null }
        if (canCalculate) {
            calculateInventory()
        }
        return when (setupTask.complete()) {
            SetupResult.FAILURE, SetupResult.INCOMPLETE -> {
                ScriptManager.stop()
                logger.info("Unable to resume with the given items")
                false
            }
            SetupResult.UNKNOWN -> false
            SetupResult.COMPLETE -> {
                if (!canCalculate) {
                    calculateInventory()
                }
                setup = true
                true
            }
        }
    }

    override fun stepName(): String {
        return "Banking for items"
    }
}