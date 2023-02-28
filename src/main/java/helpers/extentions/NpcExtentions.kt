package helpers.extentions

import org.powbot.api.Area
import org.powbot.api.rt4.Npc
import org.powbot.api.rt4.Npcs
import org.powbot.api.rt4.Npcs.stream
import java.util.function.Predicate

fun Npcs.nearestNpc(vararg names: String): Npc {
    return stream().name(*names).nearest().first()
}

fun Npcs.nearestNpc(vararg ids: Int): Npc {
    return stream().id(*ids).nearest().first()
}

fun Npcs.nearestNpcWithinArea(withinArea: Area?, vararg npcName: String): Npc {
    return stream().within(withinArea!!).name(*npcName).nearest().first()
}

fun Npcs.nearestCombatNpc(withinArea: Area?, vararg npcName: String): Npc {
    return stream().within(withinArea!!).name(*npcName)
        .filter(Predicate { n: Npc -> n.healthPercent() == 100 }).nearest().first()
}