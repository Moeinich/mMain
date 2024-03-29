package quests.templeOfTheEye.tasks

import helpers.extentions.nearestGameObject
import org.powbot.api.Condition
import org.powbot.api.Tile
import org.powbot.api.rt4.Game
import org.powbot.api.rt4.GroundItem
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Objects
import org.powbot.api.rt4.walking.FailureReason
import org.powbot.api.rt4.walking.WebWalkingResult
import quests.common.PickupItemStep
import quests.common.models.QuestInformation
import quests.templeOfTheEye.TempleOfTheEyeConstants.MASSIVE_PORTAL_ID

class PickupItemIngame(
    destinationLocation: Tile,
    getGroundItem: () -> GroundItem,
    shouldExecute: () -> Boolean,
    interaction: String,
    stepText: String,
    val offset: Tile,
    information: QuestInformation,
) : PickupItemStep(destinationLocation, getGroundItem, shouldExecute, interaction, stepText, information) {

    override val destinationLocation: Tile
        get() {
            if (TalkToNpcIngame.portalTile == Tile.Nil) {
                TalkToNpcIngame.portalTile = Objects.nearestGameObject(MASSIVE_PORTAL_ID).tile
            }

            return if (TalkToNpcIngame.portalTile == Tile.Nil) Game.mapOffset()
                .derive(offset.x, offset.y) else TalkToNpcIngame.portalTile.derive(offset.x, offset.y)
        }

    override fun walkToDestination(): WebWalkingResult {
        val superResult = super.walkToDestination()
        if (superResult.failureReason == FailureReason.NoPath) {
            if (Movement.step(destinationLocation)) {
                val result =
                    Condition.wait { Movement.destination() == Tile.Nil || Movement.destination().distance() <= 4 }
                return WebWalkingResult(false, result, FailureReason.Unknown)
            }
        }
        return superResult
    }
}