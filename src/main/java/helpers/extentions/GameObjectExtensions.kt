package helpers.extentions

import org.powbot.api.Area
import org.powbot.api.rt4.GameObject
import org.powbot.api.rt4.Objects

fun Objects.nearestGameObject(vararg name: String): GameObject {
    return stream().name(*name).nearest().first()
}

fun Objects.nearestGameObject(name: String, type: GameObject.Type): GameObject {
    return stream().type(type).name(name).nearest().first()
}

fun Objects.nearestGameObject(vararg ids: Int): GameObject {
    return stream().id(*ids).nearest().first()
}

fun Objects.nearestGameObjectWithinArea(withinArea: Area, vararg names: String): GameObject {
    return stream().within(withinArea).name(*names).nearest().first()
}

fun Objects.nearestGameObjectWithinArea(withinArea: Area, vararg ids: Int): GameObject {
    return stream().within(withinArea).id(*ids).nearest().first()
}

fun Objects.nearestGameObjectWithinRadius(radius: Int, vararg ids: Int): GameObject {
    return stream().within(radius).id(*ids).nearest().first()
}
