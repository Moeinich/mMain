package quests.common.models

import quests.common.base.BaseQuestStep
import quests.common.QuestVarpbits
import org.powbot.api.rt4.Magic


class QuestInformation(
    val questVarbits: QuestVarpbits.Quest,
    val foodName: Array<String>,
    val weaponName: String? = null,
    var spell: Magic.Spell? = null
) {

    var lowerHpToEatAt: Int = 40
    var higherHpToEatAt: Int = 50

    var currentQuest: BaseQuestStep? = null
    var currentQuestStatus: String? = null
    var complete: Boolean = false

}