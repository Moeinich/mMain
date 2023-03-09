package quests.xMarksTheSpot

import com.open.quester.common.InteractWithItem
import com.open.quester.common.InteractWithWidget
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Varpbits
import org.powbot.api.rt4.Widgets
import quests.clientOfKourend.ClientOfKourendConstants
import quests.common.BankStep
import quests.common.CommonMethods
import quests.common.Constants.BANK_LUMBRIDGE
import quests.common.Constants.ITEM_SPADE
import quests.common.QuestTaskList
import quests.common.SimpleConversationStep
import quests.common.base.BaseQuest
import quests.common.base.BaseQuestStep
import quests.common.models.ItemRequirement
import quests.common.models.ItemRequirementCondition
import quests.common.models.QuestInformation
import quests.common.models.QuestRequirements
import quests.xMarksTheSpot.XMarksTheSpotConstants.NAME_VEOS
import quests.xMarksTheSpot.XMarksTheSpotConstants.TILE_DIG_BOBS
import quests.xMarksTheSpot.XMarksTheSpotConstants.TILE_DIG_CASTLE
import quests.xMarksTheSpot.XMarksTheSpotConstants.TILE_DIG_DRAYNOR
import quests.xMarksTheSpot.XMarksTheSpotConstants.TILE_DIG_PIG_PEN
import quests.xMarksTheSpot.XMarksTheSpotConstants.TILE_LUMB_VEOS
import quests.xMarksTheSpot.XMarksTheSpotConstants.TILE_PORT_VEOS
import quests.xMarksTheSpot.XMarksTheSpotConstants.VARPBIT_STATE_MASK
import quests.xMarksTheSpot.XMarksTheSpotConstants.VEOS_START_QUEST
import quests.xMarksTheSpot.steps.DigStep

class XMarksTheSpot(information: QuestInformation) : BaseQuest(information) {

    private val spadeRequirement = ItemRequirement(ITEM_SPADE, false)
    private val spadeCondition = ItemRequirementCondition(spadeRequirement)
    private var startQuestStep = SimpleConversationStep(
            NAME_VEOS, TILE_LUMB_VEOS, VEOS_START_QUEST,
            "Starting Quest", information
    )
    private val spadeBank = BankStep(listOf(spadeCondition), BANK_LUMBRIDGE, information, {
        Inventory.stream().name(ITEM_SPADE).count() < 1
    })
    private val startQuestWithSpade = QuestTaskList(spadeBank, startQuestStep)
    private val digAtBobs = DigStep(TILE_DIG_BOBS, "Digging at Bob's")
    private val digAtCastle = DigStep(TILE_DIG_CASTLE, "Digging at castle")
    private val digAtDraynor = DigStep(TILE_DIG_DRAYNOR, "Digging at draynor")
    private val digAtPigPen = DigStep(TILE_DIG_PIG_PEN, "Digging at pig pen")
    private val finishQuest = SimpleConversationStep(NAME_VEOS, TILE_PORT_VEOS, arrayOf(), "Finishing Quest", information)

    private fun useLamp(): QuestTaskList {
        return QuestTaskList(
                InteractWithItem(ClientOfKourendConstants.ITEM_ANTIQUE_LAMP,
                        "Rub",
                        { Inventory.stream().name(ClientOfKourendConstants.ITEM_ANTIQUE_LAMP).count().toInt() > 0 },
                        { Widgets.widget(240).valid() }
                ),
                InteractWithWidget(240, 14, "Slayer") { Widgets.component(229, 26).valid() },
                InteractWithWidget(240, 26, "Confirm"),
        )
    }


    override fun addRequirements(): QuestRequirements {
        return QuestRequirements(listOf(spadeCondition), listOf())
    }

    override fun getQuestStep(stepPosition: Int): BaseQuestStep? {
        val questStateId: Int = information.questVarbits.questVarbit
        when (Varpbits.varpbit(questStateId, VARPBIT_STATE_MASK)) {
            0, 1 -> return startQuestWithSpade.processStep()
            2 -> return digAtBobs
            3 -> return digAtCastle
            4 -> return digAtDraynor
            5 -> return digAtPigPen
            6, 7 -> return finishQuest
            8 -> return useLamp().processStep()
            else -> {
                information.complete = true
                CommonMethods.closeQuestComplete()
            }
        }
        return null
    }
}