package helpers.extentions

import org.powbot.api.rt4.Equipment


fun Equipment.itemEquipped(vararg name: String): Boolean {
    return stream().name(*name).isNotEmpty()
}
