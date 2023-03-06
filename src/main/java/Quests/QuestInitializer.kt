package quests

import quests.common.QuestVarpbits
import quests.common.models.QuestInformation
import quests.enterTheAbyss.EnterTheAbyss
import quests.naturalHistory.NaturalHistory
import quests.priestInPeril.PriestInPeril
import quests.runeMysteries.RuneMysteries
import quests.templeOfTheEye.TempleOfTheEye

object QuestInitializer {
    @JvmField
    var priestInPeril = PriestInPeril(
        QuestInformation(
            QuestVarpbits.PRIEST_IN_PERIL,
            arrayOf("Lobster"),
            "Adamant scimitar",
            null
        )
    )
    @JvmField
    var naturalHistory = NaturalHistory(
        QuestInformation(
            QuestVarpbits.NATURAL_HISTORY,
            arrayOf("Cake"),
            null,
            null
        )
    )
    @JvmField
    var runeMysteries = RuneMysteries(
        QuestInformation(
            QuestVarpbits.RUNE_MYSTERIES,
            arrayOf("Cake"),
            null,
            null
        )
    )
    @JvmField
    var enterTheAbyss = EnterTheAbyss(
        QuestInformation(
            QuestVarpbits.ENTER_THE_ABYSS,
            arrayOf("Cake"),
            null,
            null
        )
    )
    @JvmField
    var templeOfTheEye = TempleOfTheEye(
        QuestInformation(
            QuestVarpbits.ENTER_THE_ABYSS,
            arrayOf("Cake"),
            null,
            null
        )
    )
}