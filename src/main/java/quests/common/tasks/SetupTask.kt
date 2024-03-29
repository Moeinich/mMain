package quests.common.tasks

import org.powbot.api.Tile
import org.powbot.api.rt4.Bank
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Skills
import org.powbot.dax.api.DaxWalker
import org.powbot.mobile.script.ScriptManager
import quests.common.models.ItemRequirementCondition
import quests.common.models.SetupResult
import quests.common.models.SkillRequirement
import java.util.logging.Level
import java.util.logging.Logger

open class SetupTask(
    private val itemRequirements: List<ItemRequirementCondition>,
    private val skillRequirement: List<SkillRequirement>
) {
    private var logger: Logger = Logger.getLogger(this.javaClass.simpleName)
    private var checkedInventory: Boolean = false
    private var checkedBank: Boolean = false
    private var checkedSkills: Boolean = false

    fun complete(): SetupResult {
        if (!checkedSkills) {
            val result = checkSkillRequirements()

            if (!result) {
                logger.info("Stopping because does not have skill requirements")
                ScriptManager.stop()
                return SetupResult.FAILURE
            }
        } else {
            logger.info("Passed skill requirements")
        }

        var complete = SetupResult.UNKNOWN
        val remainingItems = getRemainingItems()

        if (remainingItems.isEmpty()) {
            logger.info("No remaining items to check")
            complete = SetupResult.COMPLETE
        } else if (!checkedInventory) {
            logger.info("Checking inventory for items")
            checkInventoryForItems(remainingItems)
        } else if (!checkedBank) {
            if (bankOpened()) {
                logger.info("Checking bank for items")
                checkBankItems(remainingItems)
            } else {
                logger.info("Opening bank")
                openBank()
            }
        } else {
            val untradableItems = remainingItems.filter { ir -> ir.itemRequirements.none { !it.isUntradable } }.toList()
            complete = when {
                remainingItems.isEmpty() -> {
                    logger.info("Successfully marked items")
                    SetupResult.COMPLETE
                }
                untradableItems.isNotEmpty() -> {
                    logger.info("Untradable item detected missing ${untradableItems.joinToString { it.itemRequirements[0].name }}")
                    SetupResult.FAILURE
                }
                else -> {
                    SetupResult.INCOMPLETE
                }
            }
        }

        return if (getRemainingItems().isEmpty() || complete == SetupResult.COMPLETE) {
            if (bankOpened()) {
                closeBank()
            }
            SetupResult.COMPLETE
        } else complete
    }

    private fun getRemainingItems(): List<ItemRequirementCondition> {
        return itemRequirements.filter { it.chosenRequirement == null }.toList()
    }

    private fun checkBankItems(itemsToRetrieve: List<ItemRequirementCondition>) {
        logger.log(Level.INFO, "---- Checking bank, ${itemsToRetrieve.size} to check. ----")
        val bankMap = getBankItems()
        itemsToRetrieve.forEach { ir ->
            for (req in ir.itemRequirements) {
                val count = bankMap.getOrDefault(req.name, -1)
                if (req.name.isEmpty()) {
                    logger.log(Level.INFO, "Empty slot detected")
                    ir.chosenRequirement = req
                    req.hasRequirement = true
                    break
                } else if (count >= req.countRequired && req.hasItemRequirements) {
                    logger.log(Level.INFO, "ST: Has required items for " + req.name)
                    ir.chosenRequirement = req
                    req.hasRequirement = true
                    break
                } else if (count == -1) {
                    logger.log(Level.INFO, "No item found matching " + req.name)
                }
            }
        }
        checkedBank = true
        logger.log(Level.INFO, "---- Done checking bank. ----")
    }

    private fun openBank() {
        val nearestBankObject = Bank.nearest()
        if (Tile.Nil === nearestBankObject || !nearestBankObject.tile().matrix().onMap()) {
            logger.log(Level.INFO, "Moving to bank.")
            DaxWalker.walkToBank()
        } else {
            logger.log(Level.INFO, "Opening bank")
            Bank.open()
        }
    }

    private fun checkInventoryForItems(itemsToRetrieve: List<ItemRequirementCondition>) {
        logger.log(Level.INFO, "---- Checking inventory, ${itemsToRetrieve.size} to check. ----")
        for (ir in itemsToRetrieve) {
            for (requirement in ir.itemRequirements) {
                logger.info("Checking ${requirement.name}")
                val count = getInventoryItemCount(requirement.name)

                if (count >= requirement.countRequired && requirement.hasItemRequirements) {
                    ir.chosenRequirement = requirement
                    requirement.hasRequirement = true
                    logger.info("Requirement met for ${requirement.name}")
                    break
                } else {
                    logger.info("Requirement not met for ${requirement.name}")
                }
            }
        }
        checkedInventory = true
        logger.log(Level.INFO, "---- Done checking inventory requirements. ----")
    }

    protected fun checkSkillRequirements(): Boolean {
        var result = true
        skillRequirement.forEach {
            val playerLevel = getSkillLevel(it.skillId)
            if (playerLevel < it.skillLevel) {
                logger.info("Does not have required skill level ${it.skillLevel}")
                result = false
            }
        }
        checkedSkills = true
        return result
    }

    /**
     *  Gets the inventory count given an item exact name
     *  @param name exact item name
     *  @return Count of items with that exact name
     */
    protected open fun getInventoryItemCount(name: String): Int {
        return Inventory.stream().name(name).count(true).toInt()
    }

    /**
     *  Gets a list of all the items from the bank
     *  @return A map if items mapped from name to count
     */
    protected open fun getBankItems(): Map<String, Int> {
        val bankMap = mutableMapOf<String, Int>()
        for (item in Bank.get()) {
            bankMap[item.name()] = item.stackSize()
        }
        return bankMap.toMap()
    }

    /**
     *  Gets the skill level for an ID, wrapped for unit testing.
     */
    protected open fun getSkillLevel(skillId: Int): Int {
        return Skills.realLevel(skillId)
    }

    /**
     *  Checks if bank opened, wrapped for unit testing.
     */
    protected open fun bankOpened(): Boolean {
        return Bank.opened()
    }

    /**
     *  Closes bank, wrapped for unit testing.
     */
    protected open fun closeBank(): Boolean {
        return Bank.close()
    }
}