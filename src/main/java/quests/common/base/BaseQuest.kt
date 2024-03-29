package quests.common.base

import quests.common.models.QuestInformation
import quests.common.models.QuestRequirements
import org.powbot.api.event.InventoryChangeEvent
import org.powbot.api.event.MessageEvent
import org.powbot.api.rt4.Varpbits
import org.powbot.mobile.drawing.Rendering
import java.util.logging.Level
import java.util.logging.Logger

abstract class BaseQuest(val information: QuestInformation) {
    protected var logger: Logger = Logger.getLogger(information.questVarbits.toString())
    lateinit var questRequirements: QuestRequirements
    private var currentQuestStep: BaseQuestStep? = null

    /**
     *  Setup of this quest
     */
    fun setup() {
        questRequirements = addRequirements()
    }

    /**
     * Initializes and runs the quest while getting quest information from the varbits.
     */
    open fun run() {
        val questStateId: Int = information.questVarbits.questVarbit
            val value = Varpbits.varpbit(questStateId)
        try {
            val questStep = getQuestStep(value)
            if (questStep != null) {
                val currentQuestStatus = information.currentQuestStatus
                if (currentQuestStatus == null || currentQuestStatus != questStep.stepName()) {
                    information.currentQuestStatus = questStep.stepName()
                }
                currentQuestStep = questStep
                questStep.execute()
            } else {
                information.complete = true
                logger.info("No steps found, quest complete? :)")
                return
            }
        } catch (e: Exception) {
            logger.log(Level.SEVERE, e.stackTraceToString(), e)
        }
    }

    protected fun getQuestVarpbit(): Int {
        return information.questVarbits.questVarbit
    }

    abstract fun addRequirements(): QuestRequirements

    /**
     * Main method of the quest which determines the logic
     *
     * @param stepPosition current position in varbits
     */
    abstract fun getQuestStep(stepPosition: Int): BaseQuestStep?

    open fun draw(g: Rendering) {
        currentQuestStep?.draw(g)
    }

    open fun handleMessage(me: MessageEvent) {
        currentQuestStep?.messageRecieved(me)
    }

    open fun inventoryChange(event: InventoryChangeEvent) {
        currentQuestStep?.inventoryChanged(event)
    }
}