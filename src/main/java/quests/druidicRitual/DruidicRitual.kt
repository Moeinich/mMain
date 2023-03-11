package quests.druidicRitual

import helpers.extentions.Conditions
import helpers.extentions.nearestGameObject
import org.powbot.api.Tile
import org.powbot.api.rt4.Chat
import org.powbot.api.rt4.GameObject
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Objects
import quests.common.BankStep
import quests.common.CommonMethods
import quests.common.Constants.BANK_WEST_FALADOR
import quests.common.QuestTaskList
import quests.common.SimpleConversationStep
import quests.common.base.BaseQuest
import quests.common.base.BaseQuestStep
import quests.common.base.SimpleObjectStep
import quests.common.helpers.InteractionsHelper
import quests.common.models.ItemRequirementCondition
import quests.common.models.QuestInformation
import quests.common.models.QuestRequirements
import quests.druidicRitual.DruidicRitualConstants.FIRST_CONVERSATION
import quests.druidicRitual.DruidicRitualConstants.ITEM_BEAR_MEAT
import quests.druidicRitual.DruidicRitualConstants.ITEM_ENCHANTED_BEAR
import quests.druidicRitual.DruidicRitualConstants.ITEM_ENCHANTED_BEEF
import quests.druidicRitual.DruidicRitualConstants.ITEM_ENCHANTED_CHICKEN
import quests.druidicRitual.DruidicRitualConstants.ITEM_ENCHANTED_RAT
import quests.druidicRitual.DruidicRitualConstants.ITEM_RAT_MEAT
import quests.druidicRitual.DruidicRitualConstants.ITEM_RAW_BEEF
import quests.druidicRitual.DruidicRitualConstants.ITEM_RAW_CHICKEN
import quests.druidicRitual.DruidicRitualConstants.NAME_CAULDRON_DOOR
import quests.druidicRitual.DruidicRitualConstants.NPC_KAQEMEEX
import quests.druidicRitual.DruidicRitualConstants.NPC_SANFEW
import quests.druidicRitual.DruidicRitualConstants.SANFEW_CONVERESATION
import quests.druidicRitual.DruidicRitualConstants.TILE_KAQEMEEX
import quests.druidicRitual.DruidicRitualConstants.TILE_SANFER_UPSTAIRS

class DruidicRitual(information: QuestInformation) : BaseQuest(information) {

    private val itemRequirements = listOf(
        ItemRequirementCondition(ITEM_RAW_CHICKEN, false, 1),
        ItemRequirementCondition(ITEM_RAT_MEAT, false, 1),
        ItemRequirementCondition(ITEM_RAW_BEEF, false, 1),
        ItemRequirementCondition(ITEM_BEAR_MEAT, false, 1),
    )

    private val talkToKaqemeex = SimpleConversationStep(
        NPC_KAQEMEEX, TILE_KAQEMEEX, FIRST_CONVERSATION,
        "Talking to Kaqemeex", information
    )

    private val startQuest = startQuest()

    private val talkToSanfew = SimpleConversationStep(
        NPC_SANFEW, TILE_SANFER_UPSTAIRS, SANFEW_CONVERESATION,
        "Talking to Sanfew", information
    )
    private val talkToSanfewAgain = SimpleConversationStep(
        NPC_SANFEW, TILE_SANFER_UPSTAIRS, arrayOf(),
        "Talking to Sanfew", information, false
    ) {
        Inventory.stream()
            .name(ITEM_ENCHANTED_BEEF, ITEM_ENCHANTED_BEAR, ITEM_ENCHANTED_CHICKEN, ITEM_ENCHANTED_RAT)
            .count().toInt() == 4
    }


    private val convertItems = convertItems()

    override fun addRequirements(): QuestRequirements {
        return QuestRequirements(itemRequirements, listOf())
    }

    override fun getQuestStep(stepPosition: Int): BaseQuestStep? {
        return when (stepPosition) {
            0 -> startQuest.processStep()
            1 -> talkToSanfew
            2 -> convertItems.processStep()
            3 -> talkToKaqemeex
            else -> {
                Chat.completeChat()
                information.complete = true
                CommonMethods.closeQuestComplete()
                null
            }
        }
    }

    private fun convertItems(): QuestTaskList {
        return QuestTaskList(
            useItemOnCauldron(ITEM_BEAR_MEAT),
            useItemOnCauldron(ITEM_RAW_BEEF),
            useItemOnCauldron(ITEM_RAW_CHICKEN),
            useItemOnCauldron(ITEM_RAT_MEAT),
            talkToSanfewAgain,
        )
    }

    private fun useItemOnCauldron(itemName: String): SimpleObjectStep {
        return SimpleObjectStep(
            Tile(2891, 9831), arrayOf(),
            { Objects.nearestGameObject(NAME_CAULDRON_DOOR) },
            { go: GameObject ->
                val item = Inventory.stream().name(itemName).first()
                InteractionsHelper.useItemOnInteractive(item, go)
            },
            { Conditions.waitUntilItemLeavesInventory(itemName, 1); true },
            "Converting $itemName",
            information,
            { Inventory.stream().name(itemName).count() > 0 }
        )
    }


    private fun startQuest(): QuestTaskList {
        return QuestTaskList(
            BankStep(itemRequirements, BANK_WEST_FALADOR, information, foodRequired = true),
            talkToKaqemeex,
        )
    }


}