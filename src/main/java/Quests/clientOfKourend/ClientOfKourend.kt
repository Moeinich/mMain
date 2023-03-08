package quests.clientOfKourend

import quests.clientOfKourend.ClientOfKourendConstants.CONVERSATION_FINISH_VEOS
import quests.common.CommonMethods
import quests.common.SimpleConversationStep
import quests.common.base.BaseQuest
import quests.common.base.BaseQuestStep
import quests.common.models.ItemRequirementCondition
import quests.common.models.QuestInformation
import quests.common.models.QuestRequirements
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
import quests.clientOfKourend.ClientOfKourendConstants.TILE_HORACE
import quests.clientOfKourend.ClientOfKourendConstants.TILE_JENNIFER
import quests.clientOfKourend.ClientOfKourendConstants.TILE_LEENZ
import quests.clientOfKourend.ClientOfKourendConstants.TILE_MUNTY
import quests.clientOfKourend.ClientOfKourendConstants.TILE_REGATH
import quests.clientOfKourend.ClientOfKourendConstants.TILE_VEOS_AND_VEOS_CLIENT

class ClientOfKourend(information: QuestInformation) : BaseQuest(information) {

    private val featherRequirement = ItemRequirementCondition(ITEM_FEATHER, false, 1)
    private val enchantedscrollRequirement = ItemRequirementCondition(ITEM_ENCHANTED_SCROLL, false, 1)
    private val enchantedquillRequirement = ItemRequirementCondition(ITEM_ENCHANTED_QUILL, false, 1)
    private val mysteriousorbRequirement = ItemRequirementCondition(ITEM_MYSTERIOUS_ORB, false, 1)
    private val kourendfavourcertificateRequirement = ItemRequirementCondition(ITEM_KOUREND_FAVOUR_CERTIFICATE, false, 1)
    private val antiquelampRequirement = ItemRequirementCondition(ITEM_ANTIQUE_LAMP, false, 1)
    private val brokenglassRequirement = ItemRequirementCondition(ITEM_BROKEN_GLASS, false, 1)


    private val startQuestStep = SimpleConversationStep(
        NAME_VEOS, TILE_VEOS_AND_VEOS_CLIENT, CONVERSATION_START_VEOS,
        "Talking to Veos.", information
    )

    //Will give you enchantedscrollRequirement

    //Use enchantedscrollRequirement on featherRequirement


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

    //Recieves mysteriousorbRequirement

    //Walks to TILE_DARK_ALTAR and interacts with mysteriousorbRequirement. When used at altar it will become brokenglassRequirement

    private val talkToVeosFinishQuest = SimpleConversationStep(
        NAME_VEOS_CLIENT, TILE_VEOS_AND_VEOS_CLIENT, CONVERSATION_FINISH_VEOS,
        "Finishing Quest", information
    )

    //After Quest complete it will have to use the 2 xp lamps (I suggest slayer) and the 20% favour (hosidus)


    override fun addRequirements(): QuestRequirements {
        val itemRequirements: List<ItemRequirementCondition> = ArrayList()
        return QuestRequirements(itemRequirements, listOf())
    }

    override fun getQuestStep(stepPosition: Int): BaseQuestStep? {
        when (stepPosition) {

            //THIS IS NOT CORRECT (NOT SURE IF THIS IS ALSO WHERE IT CHECKS VARBITS?)
            0 -> return startQuestStep
            1 -> return talkToLeenz
            2 -> return talkToRegath
            3 -> return talkToMunty
            4 -> return talkToJennifer
            5 -> return talkToHorace
            6 -> return talkToVeosAgain
            7 -> return talkToVeosFinishQuest
            8 -> {
                information.complete = true
                CommonMethods.closeQuestComplete()
            }
        }
        return null
    }
}