package quests.Common.Models

import quests.Common.Base.BaseQuestStep
import quests.Common.QuestVarpbits
import org.powbot.api.rt4.Item
import org.powbot.api.rt4.Magic


class QuestInformation(
    val questVarbits: QuestVarpbits.Quest,
    val foodName: Array<String>,
    val weaponName: Item? = null,
    var spell: Magic.Spell? = null
) {

    var lowerHpToEatAt: Int = 40
    var higherHpToEatAt: Int = 50

    var currentQuest: BaseQuestStep? = null
    var currentQuestStatus: String? = null
    var complete: Boolean = false

}