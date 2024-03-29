package quests.priestInPeril.steps.temple

import org.powbot.api.Condition
import org.powbot.api.Tile
import org.powbot.api.rt4.*
import org.powbot.mobile.rscache.loader.ItemLoader
import quests.common.base.WalkToInteractiveStep
import helpers.extentions.count
import quests.common.helpers.InteractionsHelper
import quests.common.models.QuestInformation
import quests.priestInPeril.PriestInPerilConstants.ACTION_STUDY
import quests.priestInPeril.PriestInPerilConstants.IDS_MONUMENT
import quests.priestInPeril.PriestInPerilConstants.ITEM_COMPONENT
import quests.priestInPeril.PriestInPerilConstants.ITEM_GOLDEN_KEY
import quests.priestInPeril.PriestInPerilConstants.ITEM_IRON_KEY
import quests.priestInPeril.PriestInPerilConstants.KEY_WIDGET
import quests.priestInPeril.PriestInPerilConstants.TILE_UNDERGROUND

class GrabSilverKey(information: QuestInformation) : WalkToInteractiveStep<GameObject>(TILE_UNDERGROUND, arrayOf(), questInformation = information) {

    private val checkedMonuments = arrayOf(false, false, false, false, false, false, false)
    private var currentMonument = -1
    private var keyFound = false

    override fun shouldExecute(): Boolean {
        return Inventory.stream().name(ITEM_GOLDEN_KEY).count()
            .toInt() == 1
    }

    override fun run() {
        val keyItem = getItemWidget()
        if (keyItem != Component.Nil) {
            val item = ItemLoader.lookup(keyItem.itemId())
            if (currentMonument == -1) {
                if (getCloseWidget().click()) {
                    currentMonument = -1
                    return
                }
            } else {
                val interactive = getInteractive()
                if (interactive != GameObject.Nil) {
                    if (interactive.interact(ACTION_STUDY)) {
                        Condition.wait { getItemWidget().visible() }
                    }
                } else {
                    logger.info("Could not find an interactable monument")
                }
            }
            if (item?.name()?.contains("Golden")!!) {
                logger.info("$currentMonument set to true")
                checkedMonuments[currentMonument] = true
                getCloseWidget().click()
            } else if (item.name().contains("Iron")) {
                logger.info("Found key")
                checkedMonuments.forEachIndexed { index, _ ->
                    if (index != currentMonument) {
                        checkedMonuments[index] = true
                    }
                }
                keyFound = true
                getCloseWidget().click()
            }
        } else {
            if (keyFound) {
                if (InteractionsHelper.useItemOnInteractive(ITEM_GOLDEN_KEY, getInteractive())) {
                    Condition.wait { Inventory.count(ITEM_IRON_KEY) > 0 }
                }
            } else {
                super.run()
            }
        }
    }


    private fun getItemWidget(): Component {
        return Widgets.component(KEY_WIDGET, ITEM_COMPONENT)
    }

    private fun getCloseWidget(): Component {
        // TODO Constant
        return Widgets.component(KEY_WIDGET, 1, 3)
    }

    private fun getGameObject(): IntArray {
        val list = mutableListOf<Int>()

        checkedMonuments.forEachIndexed { idx, bool ->
            if (!bool) {
                list.add(IDS_MONUMENT[idx])
            }
        }
        return list.toIntArray()
    }

    override fun stepName(): String {
        return "Getting silver key"
    }

    override fun getInteractive(): GameObject {
        return Objects.stream().id(*getGameObject()).nearest().first()
    }

    override fun interact(interactive: GameObject): Boolean {
        currentMonument = IDS_MONUMENT.indexOf(interactive.id())
        return interactive.interact(ACTION_STUDY) && Condition.wait  { getItemWidget().visible() }
    }

    override fun getInteractiveTile(interactive: GameObject): Tile {
        return interactive.tile
    }
}