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
        BANK_DRAYNOR, information, foodRequired = true
    )

    private val startQuestStep = SimpleConversationStep(
        NAME_VEOS, TILE_VEOS_AND_VEOS_CLIENT, CONVERSATION_START_VEOS,
        "Talking to Veos.", information
    )

    private val useFeatherOnScroll = CombineItemStep(
        ITEM_FEATHER, ITEM_ENCHANTED_SCROLL, "Making enchanted quill",
        { Inventory.count(ITEM_FEATHER) == 1 && Inventory.count(ITEM_ENCHANTED_SCROLL) == 1 }, false
        // Needs to have inventory open!!
    )

    private val startQuestStepList = QuestTaskList(withdrawItems, startQuestStep, useFeatherOnScroll)


    private val talkToLeenz = SimpleConversationStep(
        NAME_LEENZ, TILE_LEENZ, CONVERSATION_STEP2_LEENZ,
        "Talking to Leenz", information
        // Varp = 1
    )

    private val talkToRegath = SimpleConversationStep(
        NAME_REGATH, TILE_REGATH, CONVERSATION_STEP3_REGATH,
        "Talking to Regath", information
        // Varp = 65
    )

    private val talkToMunty = SimpleConversationStep(
        NAME_MUNTY, TILE_MUNTY, CONVERSATION_STEP4_MUNTY,
        "Talking to Munty", information
        // Varp = 193
    )

    private val talkToJennifer = SimpleConversationStep(
        NAME_JENNIFER, TILE_JENNIFER, CONVERSATION_STEP5_JENNIFER,
        "Talking to Jennifer", information

        // Varp = 449
    )

    private val talkToHorace = SimpleConversationStep(
        NAME_HORACE, TILE_HORACE, CONVERSATION_STEP6_HORACE,
        "Talking to Horace", information

        // Varp = 961
    )

    private val talkToVeosAgain = SimpleConversationStep(
        NAME_VEOS, TILE_VEOS_AND_VEOS_CLIENT, CONVERSATION_STEP7_VEOS,
        "Talking to Veos", information
        // Recieves mysteriousorbRequirement
        // Varp = 1986
    )


    // Walks to TILE_DARK_ALTAR and interacts with mysteriousorbRequirement. When used at altar it will become brokenglassRequirement.
    private fun goToAltar(): QuestTaskList {
        return QuestTaskList(
            WalkToExactTile(
                TILE_DARK_ALTAR, "Walking to dark altar", { Players.local().tile() != TILE_DARK_ALTAR }, information
            ),
            InteractWithItem(ITEM_MYSTERIOUS_ORB, "Activate", { Inventory.count(ITEM_MYSTERIOUS_ORB) > 0 },
                { Widgets.widget(12).valid() })
            // Needs to have inventory open!!
        )
    }


    private val talkToVeosFinishQuest = SimpleConversationStep(
        NAME_VEOS, TILE_VEOS_AND_VEOS_CLIENT, CONVERSATION_FINISH_VEOS,
        "Finishing Quest", information
    )


    //After Quest complete it will have to use the 2 xp lamps (I suggest slayer) and the 20% favour (hosidus)
    private fun useLamp(): QuestTaskList {
        return QuestTaskList(
            InteractWithItem(ITEM_ANTIQUE_LAMP,
                "Rub",
                { Inventory.stream().name(ITEM_ANTIQUE_LAMP).count().toInt() > 0 },
                { Widgets.widget(240).valid() }
            ),
            InteractWithWidget(240, 14, "Slayer"),
            InteractWithWidget(240, 26, "Confirm"),
            )
    }


    private fun useCertificate(): QuestTaskList {
        return QuestTaskList(
            InteractWithItem(ITEM_KOUREND_FAVOUR_CERTIFICATE, "Read", { Inventory.stream().name(ITEM_KOUREND_FAVOUR_CERTIFICATE).count().toInt() == 1 }, { Widgets.widget(219).valid() }),
            InteractWithWidget(219, 1,  "Hosidius"), //Needs to use keyboard "2" here
            InteractWithWidget(229, 2, "Click here to continue"),
            InteractWithWidget(240, 26, "Confirm"),
            InteractWithWidget(219, 1, "Yes"),

            )
    }


    override fun addRequirements(): QuestRequirements {
        val itemRequirements: List<ItemRequirementCondition> = ArrayList()
        return QuestRequirements(itemRequirements, listOf())
    }

    override fun getQuestStep(stepPosition: Int): BaseQuestStep? {
        when (stepPosition) {
            0 -> return startQuestStepList.processStep()
            1 -> return talkToLeenz
            65 -> return talkToMunty
            193 -> return talkToRegath
            449 -> return talkToHorace
            961 -> return talkToJennifer
            1986 -> return talkToVeosAgain
            1988 -> return goToAltar().processStep()
            1989 -> return talkToVeosFinishQuest
            1991 -> return useLamp().processStep()
            1992 -> return useLamp().processStep()
            1993 -> return useCertificate().processStep()
            1994 -> { //Might be wrong, needs to check after RS update
                information.complete = true
                CommonMethods.closeQuestComplete()
            }
        }
        return null
    }
}