package quests.clientOfKourend

import com.open.quester.common.CombineItemStep
import com.open.quester.common.InteractWithItem
import com.open.quester.common.InteractWithWidget
import com.open.quester.common.WalkToExactTile
import helpers.extentions.count
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Players
import org.powbot.api.rt4.Widgets
import quests.clientOfKourend.ClientOfKourendConstants.CONVERSATION_FINISH_VEOS
import quests.clientOfKourend.ClientOfKourendConstants.CONVERSATION_START_VEOS
import quests.clientOfKourend.ClientOfKourendConstants.CONVERSATION_STEP2_LEENZ
import quests.clientOfKourend.ClientOfKourendConstants.CONVERSATION_STEP3_REGATH
import quests.clientOfKourend.ClientOfKourendConstants.CONVERSATION_STEP4_MUNTY
import quests.clientOfKourend.ClientOfKourendConstants.CONVERSATION_STEP5_JENNIFER
import quests.clientOfKourend.ClientOfKourendConstants.CONVERSATION_STEP6_HORACE
import quests.clientOfKourend.ClientOfKourendConstants.CONVERSATION_STEP7_VEOS
import quests.clientOfKourend.ClientOfKourendConstants.ITEM_ANTIQUE_LAMP
import quests.clientOfKourend.ClientOfKourendConstants.ITEM_BROKEN_GLASS
import quests.clientOfKourend.ClientOfKourendConstants.ITEM_ENCHANTED_QUILL
import quests.clientOfKourend.ClientOfKourendConstants.ITEM_ENCHANTED_SCROLL
import quests.clientOfKourend.ClientOfKourendConstants.ITEM_FEATHER
import quests.clientOfKourend.ClientOfKourendConstants.ITEM_KOUREND_FAVOUR_CERTIFICATE
import quests.clientOfKourend.ClientOfKourendConstants.ITEM_MYSTERIOUS_ORB
import quests.clientOfKourend.ClientOfKourendConstants.NAME_HORACE
import quests.clientOfKourend.ClientOfKourendConstants.NAME_JENNIFER
import quests.clientOfKourend.ClientOfKourendConstants.NAME_LEENZ
import quests.clientOfKourend.ClientOfKourendConstants.NAME_MUNTY
import quests.clientOfKourend.ClientOfKourendConstants.NAME_REGATH
import quests.clientOfKourend.ClientOfKourendConstants.NAME_VEOS
import quests.clientOfKourend.ClientOfKourendConstants.NAME_VEOS_CLIENT
import quests.clientOfKourend.ClientOfKourendConstants.TILE_DARK_ALTAR
import quests.clientOfKourend.ClientOfKourendConstants.TILE_HORACE
import quests.clientOfKourend.ClientOfKourendConstants.TILE_JENNIFER
import quests.clientOfKourend.ClientOfKourendConstants.TILE_LEENZ
import quests.clientOfKourend.ClientOfKourendConstants.TILE_MUNTY
import quests.clientOfKourend.ClientOfKourendConstants.TILE_REGATH
import quests.clientOfKourend.ClientOfKourendConstants.TILE_VEOS_AND_VEOS_CLIENT
import quests.common.BankStep
import quests.common.CommonMethods
import quests.common.Constants.BANK_DRAYNOR
import quests.common.QuestTaskList
import quests.common.SimpleConversationStep
import quests.common.base.BaseQuest
import quests.common.base.BaseQuestStep
import quests.common.models.ItemRequirementCondition
import quests.common.models.QuestInformation
import quests.common.models.QuestRequirements

class ClientOfKourend(information: QuestInformation) : BaseQuest(information) {

    private val featherRequirement = ItemRequirementCondition(ITEM_FEATHER, false, 1)
    private val enchantedscrollRequirement = ItemRequirementCondition(ITEM_ENCHANTED_SCROLL, true, 1)
    private val enchantedquillRequirement = ItemRequirementCondition(ITEM_ENCHANTED_QUILL, true, 1)
    private val mysteriousorbRequirement = ItemRequirementCondition(ITEM_MYSTERIOUS_ORB, true, 1)
    private val kourendfavourcertificateRequirement = ItemRequirementCondition(ITEM_KOUREND_FAVOUR_CERTIFICATE, true, 1)
    private val antiquelampRequirement = ItemRequirementCondition(ITEM_ANTIQUE_LAMP, true, 1)
    private val brokenglassRequirement = ItemRequirementCondition(ITEM_BROKEN_GLASS, false, 1)


    private val withdrawItems = BankStep(
        listOf(featherRequirement),
        BANK_DRAYNOR, information
    )

    private val startQuestStep = SimpleConversationStep(
        NAME_VEOS, TILE_VEOS_AND_VEOS_CLIENT, CONVERSATION_START_VEOS,
        "Talking to Veos.", information
    )

    private val useFeatherOnScroll = CombineItemStep(
        ITEM_FEATHER, ITEM_ENCHANTED_SCROLL, "Making enchanted quill",
        { Inventory.count(ITEM_FEATHER) == 1 && Inventory.count(ITEM_ENCHANTED_SCROLL) == 1 }, false
    )

    private val startQuestStepList = QuestTaskList(withdrawItems, startQuestStep, useFeatherOnScroll)


    private val talkToLeenz = SimpleConversationStep(
        NAME_LEENZ, TILE_LEENZ, CONVERSATION_STEP2_LEENZ,
        "Talking to Leenz", information
    )

    private val talkToRegath = SimpleConversationStep(
        NAME_REGATH, TILE_REGATH, CONVERSATION_STEP3_REGATH,
        "Talking to Regath", information
    )

    private val talkToMunty = SimpleConversationStep(
        NAME_MUNTY, TILE_MUNTY, CONVERSATION_STEP4_MUNTY,
        "Talking to Munty", information
    )

    private val talkToJennifer = SimpleConversationStep(
        NAME_JENNIFER, TILE_JENNIFER, CONVERSATION_STEP5_JENNIFER,
        "Talking to Jennifer", information
    )

    private val talkToHorace = SimpleConversationStep(
        NAME_HORACE, TILE_HORACE, CONVERSATION_STEP6_HORACE,
        "Talking to Horace", information
    )

    private val talkToVeosAgain = SimpleConversationStep(
        NAME_VEOS, TILE_VEOS_AND_VEOS_CLIENT, CONVERSATION_STEP7_VEOS,
        "Talking to Veos", information
    )

    private fun talkToPeople(): QuestTaskList {
        return QuestTaskList(
            talkToLeenz,
            talkToRegath,
            talkToMunty,
            talkToJennifer,
            talkToHorace
        )
    }
    // Recieves mysteriousorbRequirement
    // Walks to TILE_DARK_ALTAR and interacts with mysteriousorbRequirement. When used at altar it will become brokenglassRequirement
    private fun goToAltar(): QuestTaskList {
        return QuestTaskList(
            WalkToExactTile(
                TILE_DARK_ALTAR, "Walking to dark altar", { Players.local().tile() != TILE_DARK_ALTAR }, information
            ),
            InteractWithItem(ITEM_MYSTERIOUS_ORB, "Activate", { Inventory.count(ITEM_BROKEN_GLASS) > 0 },
                { Inventory.count(ITEM_BROKEN_GLASS) == 1 })
        )
    }


    private val talkToVeosFinishQuest = SimpleConversationStep(
        NAME_VEOS_CLIENT, TILE_VEOS_AND_VEOS_CLIENT, CONVERSATION_FINISH_VEOS,
        "Finishing Quest", information
    )

    //After Quest complete it will have to use the 2 xp lamps (I suggest slayer) and the 20% favour (hosidus)
    private fun useLampAndCertificate(): QuestTaskList {
        return QuestTaskList(
            InteractWithItem(ITEM_ANTIQUE_LAMP, "Rub", { Inventory.stream().name(ITEM_ANTIQUE_LAMP).count().toInt() == 1 }, { Widgets.widget(12).valid() }),
            InteractWithWidget(240, 14, "Slayer"),
            InteractWithWidget(240, 26, "Confirm"),

            InteractWithItem(ITEM_KOUREND_FAVOUR_CERTIFICATE, "Use", { Inventory.stream().name(ITEM_ANTIQUE_LAMP).count().toInt() == 1 }, { Widgets.widget(12).valid() }),
            //Gør et eller andet mere og ret ovenstående.
            )
    }


    override fun addRequirements(): QuestRequirements {
        val itemRequirements: List<ItemRequirementCondition> = ArrayList()
        return QuestRequirements(itemRequirements, listOf())
    }

    override fun getQuestStep(stepPosition: Int): BaseQuestStep? {
        when (stepPosition) {


            0 -> return startQuestStepList.processStep()
            1 -> return talkToPeople().processStep()
            2, 3 -> return talkToVeosAgain
            4 -> return goToAltar().processStep()
            5, 6 -> return talkToVeosFinishQuest
            7,8 -> return useLampAndCertificate().processStep()
            9 -> {
                information.complete = true
                CommonMethods.closeQuestComplete()
            }
        }
        return null
    }
}