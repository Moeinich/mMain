package helpers.extentions

import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Inventory.stream

fun Inventory.count(vararg name: String): Int {
    return stream().name(*name).count(true).toInt()
}

fun Inventory.count(vararg id: Int): Int {
    return stream().id(*id).count(true).toInt()
}

fun Inventory.hasItem(vararg itemID: Int): Boolean {
    return stream().id(*itemID).isNotEmpty()
}

fun Inventory.hasItem(vararg itemName: String): Boolean {
    return stream().name(*itemName).isNotEmpty()
}