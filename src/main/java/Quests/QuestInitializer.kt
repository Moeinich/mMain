package quests

import quests.clientOfKourend.ClientOfKourend
import quests.common.QuestVarpbits
import quests.common.models.QuestInformation
import quests.druidicRitual.DruidicRitual
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
            arrayOf("Lobster"),
            null,
            null
        )
    )

    @JvmField
    var runeMysteries = RuneMysteries(
        QuestInformation(
            QuestVarpbits.RUNE_MYSTERIES,
            arrayOf("Lobster"),
            null,
            null
        )
    )

    @JvmField
    var enterTheAbyss = EnterTheAbyss(
        QuestInformation(
            QuestVarpbits.ENTER_THE_ABYSS,
            arrayOf("Lobster"),
            null,
            null
        )
    )

    @JvmField
    var templeOfTheEye = TempleOfTheEye(
        QuestInformation(
            QuestVarpbits.TEMPLE_OF_THE_EYE,
            arrayOf("Lobster"),
            null,
            null
        )
    )

    @JvmField
    var druidicRitual = DruidicRitual(
        QuestInformation(
            QuestVarpbits.DRUIDIC_RITUAL,
            arrayOf("Lobster"),
            null,
            null
        )
    )

    @JvmField
    var clientOfKourend = ClientOfKourend(
        QuestInformation(
            QuestVarpbits.CLIENT_OF_KOUREND,
            arrayOf("Lobster"),
            null,
            null
        )
    )
}