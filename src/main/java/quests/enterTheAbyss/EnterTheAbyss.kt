package quests.enterTheAbyss

import helpers.extentions.count
import helpers.extentions.nearestNpc
import org.powbot.api.Tile
import org.powbot.api.rt4.*
import quests.common.BankStep
import quests.common.CommonMethods
import quests.common.Constants.BANK_DRAYNOR
import quests.common.Constants.BANK_EDGEVILLE_NE
import quests.common.Constants.ITEM_COINS
import quests.common.QuestTaskList
import quests.common.SimpleConversationStep
import quests.common.base.BaseQuest
import quests.common.base.BaseQuestStep
import quests.common.base.SimpleNpcStep
import quests.common.models.ItemRequirementCondition
import quests.common.models.QuestInformation
import quests.common.models.QuestRequirements
import quests.enterTheAbyss.EnterTheAbyssConstants.CONVERSATION_VARROCK
import quests.enterTheAbyss.EnterTheAbyssConstants.CONVERSATION_WILDERNESS
import quests.enterTheAbyss.EnterTheAbyssConstants.ITEM_SCRYING_ORB
import quests.enterTheAbyss.EnterTheAbyssConstants.NAME_AUBURY
import quests.enterTheAbyss.EnterTheAbyssConstants.NAME_COMPERTY
import quests.enterTheAbyss.EnterTheAbyssConstants.NAME_MAGE_OF_ZAMORAK
import quests.enterTheAbyss.EnterTheAbyssConstants.NAME_SEDRIDOR
import quests.enterTheAbyss.EnterTheAbyssConstants.TILE_AUBURY
import quests.enterTheAbyss.EnterTheAbyssConstants.TILE_CROMPERTY
import quests.enterTheAbyss.EnterTheAbyssConstants.TILE_MAGE_IN_VARROCK
import quests.enterTheAbyss.EnterTheAbyssConstants.TILE_MAGE_IN_WILDY
import quests.enterTheAbyss.EnterTheAbyssConstants.TILE_SEDRIDOR
import quests.enterTheAbyss.EnterTheAbyssConstants.VARPBIT_MASK_TELEPORT
import quests.enterTheAbyss.EnterTheAbyssConstants.VARPBIT_SHIFT_ARDOUGNE
import quests.enterTheAbyss.EnterTheAbyssConstants.VARPBIT_SHIFT_VARROCK
import quests.enterTheAbyss.EnterTheAbyssConstants.VARPBIT_SHIFT_WIZ_TOWER
import quests.enterTheAbyss.EnterTheAbyssConstants.VARPBIT_TELEPORTS
import quests.enterTheAbyss.tasks.LeaveEssense

class EnterTheAbyss(information: QuestInformation) : BaseQuest(information) {

    private val boatCoinsRequirement = ItemRequirementCondition(ITEM_COINS, false, 60)
    private val scryingOrbRequirement = ItemRequirementCondition(ITEM_SCRYING_ORB, false, 1)

    private val startQuest = QuestTaskList(
        BankStep(listOf(ItemRequirementCondition.emptySlots(2)), BANK_EDGEVILLE_NE, information, foodRequired = true),
        SimpleConversationStep(
            NAME_MAGE_OF_ZAMORAK,
            TILE_MAGE_IN_WILDY,
            CONVERSATION_WILDERNESS,
            "Talking to mage in wildy",
            information
        )
    )
    private val talkToMageInVarrock =
        SimpleNpcStep(
            TILE_MAGE_IN_VARROCK,
            CONVERSATION_VARROCK,
            { Npcs.stream().name(NAME_MAGE_OF_ZAMORAK).within(TILE_MAGE_IN_VARROCK, 20).first() },
            { npc: Npc -> npc.interact("Talk") },
            { Chat.chatting() },
            "Talking to mage in varrock",
            questInformation = information
        )
    private val chargeOrbs = chargeOrb()

    private fun chargeOrb(): QuestTaskList {
        return QuestTaskList(
            LeaveEssense(),
            teleportNpc(TILE_AUBURY, NAME_AUBURY, VARPBIT_SHIFT_VARROCK),
            teleportNpc(TILE_SEDRIDOR, NAME_SEDRIDOR, VARPBIT_SHIFT_WIZ_TOWER),
            BankStep(
                listOf(boatCoinsRequirement, scryingOrbRequirement),
                BANK_DRAYNOR,
                information,
                { Inventory.count(ITEM_COINS) == 0 }),
            teleportNpc(TILE_CROMPERTY, NAME_COMPERTY, VARPBIT_SHIFT_ARDOUGNE),
            talkToMageInVarrock
        )
    }

    private fun teleportNpc(tile: Tile, name: String, shift: Int): SimpleNpcStep {
        return SimpleNpcStep(
            tile,
            null,
            { Npcs.nearestNpc(name) },
            { npc: Npc -> npc.interact("Teleport") },
            { npc: Npc -> !npc.valid() },
            "Teleporting from $name",
            shouldExecute = { Varpbits.varpbit(VARPBIT_TELEPORTS, shift, VARPBIT_MASK_TELEPORT) == 0 },
            questInformation = information
        )
    }

    override fun addRequirements(): QuestRequirements {
        return QuestRequirements(listOf(boatCoinsRequirement), listOf())
    }

    override fun getQuestStep(stepPosition: Int): BaseQuestStep? {
        return when (stepPosition) {
            0 -> startQuest.processStep()
            1 -> talkToMageInVarrock
            2 -> chargeOrbs.processStep()
            3 -> talkToMageInVarrock
            4 -> {
                information.complete = true
                CommonMethods.closeQuestComplete()
                null
            }
            else -> TODO("Missing ID $stepPosition")
        }
    }
}