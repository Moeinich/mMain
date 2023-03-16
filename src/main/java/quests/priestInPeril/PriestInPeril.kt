package quests.priestInPeril

import org.powbot.api.Point
import org.powbot.api.Tile
import org.powbot.api.rt4.*
import quests.common.*
import quests.common.base.BaseQuest
import quests.common.base.BaseQuestStep
import quests.common.base.SimpleObjectStep
import quests.common.Constants.BANK_VARROCK_EAST
import quests.common.models.ItemRequirement
import quests.common.models.ItemRequirementCondition
import quests.common.models.QuestInformation
import quests.common.models.QuestRequirements
import helpers.extentions.count
import helpers.extentions.nearestGameObject
import helpers.extentions.nearestNpc
import quests.common.helpers.InteractionsHelper
import quests.priestInPeril.PriestInPerilConstants.AREA_SECOND_UNDERGROUND
import quests.priestInPeril.PriestInPerilConstants.CONVERSATION_KILL_NPC
import quests.priestInPeril.PriestInPerilConstants.CONVERSATION_START_QUEST
import quests.priestInPeril.PriestInPerilConstants.CONVERSATION_TALK_TO_DREZEL
import quests.priestInPeril.PriestInPerilConstants.ITEM_BUCKET
import quests.priestInPeril.PriestInPerilConstants.ITEM_GOLDEN_KEY
import quests.priestInPeril.PriestInPerilConstants.ITEM_IRON_KEY
import quests.priestInPeril.PriestInPerilConstants.ITEM_PURE_ESSENCE
import quests.priestInPeril.PriestInPerilConstants.ITEM_RUNE_ESSENCE
import quests.priestInPeril.PriestInPerilConstants.NAME_BLESSED_WATER
import quests.priestInPeril.PriestInPerilConstants.NAME_COFFIN
import quests.priestInPeril.PriestInPerilConstants.NAME_DREZEL
import quests.priestInPeril.PriestInPerilConstants.NAME_KING_RONALD
import quests.priestInPeril.PriestInPerilConstants.NAME_MURKY_WATER
import quests.priestInPeril.PriestInPerilConstants.NAME_TEMPLE_GUARDIAN
import quests.priestInPeril.PriestInPerilConstants.NAME_TRAP_DOOR
import quests.priestInPeril.PriestInPerilConstants.TILE_CENTER_BOTTOM_FLOOR
import quests.priestInPeril.PriestInPerilConstants.TILE_DREZEL_THIRD_FLOOR
import quests.priestInPeril.PriestInPerilConstants.TILE_DREZEL_UNDERGROUND
import quests.priestInPeril.PriestInPerilConstants.TILE_KING_RONALD
import quests.priestInPeril.PriestInPerilConstants.TILE_NEAR_TEMPLE_DOOR
import quests.priestInPeril.PriestInPerilConstants.TILE_SECOND_FLOOR
import quests.priestInPeril.PriestInPerilConstants.TILE_TRAPDOOR
import quests.priestInPeril.steps.GoUpLadder
import quests.priestInPeril.steps.temple.BankForEssence
import quests.priestInPeril.steps.temple.GrabSilverKey

class PriestInPeril(information: QuestInformation) : BaseQuest(information) {
        val runeEssence = ItemRequirement(ITEM_RUNE_ESSENCE, false, 50)
        val pureEssence = ItemRequirement(ITEM_PURE_ESSENCE, false, 50)
        private val bucketRequirement = ItemRequirementCondition(ITEM_BUCKET, false, 1)
        private val essenceCondition = ItemRequirementCondition(runeEssence, pureEssence)

        private val talktoRonald = SimpleConversationStep(
            NAME_KING_RONALD, TILE_KING_RONALD, CONVERSATION_START_QUEST,
            "Talking to King Roald", information
        )

        private val talkToDrezelUnderground = SimpleConversationStep(
            NAME_DREZEL,
            TILE_DREZEL_UNDERGROUND, arrayOf(), "Giving essence", information
        )

        private val talkToDrezelInPrisonAgain = SimpleConversationStep(NAME_DREZEL, TILE_DREZEL_THIRD_FLOOR,
            CONVERSATION_TALK_TO_DREZEL, "Talking to Drezel in prison", information,
            shouldExecute = { Inventory.count(NAME_BLESSED_WATER) == 0 })
            .also { it.pointVariance = Point(-4, 0) }

        private val talkToDrezelInsidePrison = SimpleConversationStep(NAME_DREZEL, TILE_DREZEL_THIRD_FLOOR,
            CONVERSATION_TALK_TO_DREZEL, "Talking to Drezel inside prison", information,
            shouldExecute = { Inventory.count(NAME_BLESSED_WATER) == 0 })

        private val talkToMonk = SimpleObjectStep(
            TILE_NEAR_TEMPLE_DOOR,
            CONVERSATION_KILL_NPC,
            { Objects.stream(TILE_NEAR_TEMPLE_DOOR, 5, GameObject.Type.BOUNDARY).name("Large door").first() },
            { go: GameObject -> go.interact("Open") },
            { Chat.chatting() },
            "Getting tricked",
            information
        )
        private val killDogSteps: QuestTaskList = QuestTaskList(
            BankStep(listOf(), BANK_VARROCK_EAST, information, combat = true, foodRequired = true),
            SetupWeaponStep(information),
            talkToMonk,
        )

        private val goDownAndKillDog = QuestTaskList(
            SimpleObjectStep(
                TILE_TRAPDOOR,
                arrayOf("Yes."),
                { Objects.nearestGameObject(NAME_TRAP_DOOR) },
                { go: GameObject ->
                    val action = if (go.actions().contains("Climb-down")) "Climb-down" else "Open"
                    go.interact(action)
                },
                { go: GameObject -> !go.valid() }, "Climbing down trapdoor",
                information, { Npcs.nearestNpc(NAME_TEMPLE_GUARDIAN) == Npc.Nil }
            ),
            KillNpcStep(
                Tile.Nil, { Npcs.nearestNpc(NAME_TEMPLE_GUARDIAN) }, null, null,
                { Npcs.nearestNpc(NAME_TEMPLE_GUARDIAN) != Npc.Nil }, information, "Killing dodge"
            )
        )

        private val reportBackToRoland = QuestTaskList(GoUpLadder(information), talktoRonald)
        private val talkingToDrezel = talkingToDrezel()
        private val burnTheVampire = QuestTaskList(
            talkToDrezelInsidePrison,
            SimpleObjectStep(TILE_SECOND_FLOOR, arrayOf(),
                { Objects.nearestGameObject(NAME_COFFIN) },
                { go: GameObject -> InteractionsHelper.useItemOnInteractive(NAME_BLESSED_WATER, go) },
                { Inventory.count(NAME_BLESSED_WATER) == 0 }, "Making the vampire wet", information,
                shouldExecute = { Inventory.count(NAME_BLESSED_WATER) == 1 }),
        )
        private val getKeys: QuestTaskList = getKeys()

        private val giveDrezelEssence = QuestTaskList(BankForEssence(essenceCondition), talkToDrezelUnderground)

        private val talkToDrezelUndergroundAfterQuest = SimpleConversationStep(
            NAME_DREZEL,
            TILE_DREZEL_UNDERGROUND, arrayOf(), "Unlocking Canifis", information
        )


        override fun addRequirements(): QuestRequirements {
            val itemRequirements: MutableList<ItemRequirementCondition> = mutableListOf()

            itemRequirements.add(bucketRequirement)
            itemRequirements.add(essenceCondition)
            return QuestRequirements(itemRequirements, listOf())
        }

        override fun getQuestStep(stepPosition: Int): BaseQuestStep? {
            return when (stepPosition) {
                0 -> talktoRonald
                1 -> {
                    killDogSteps.processStep()
                }
                2 -> {
                    logger.info("Going down and killing dog")
                    goDownAndKillDog.processStep()
                }
                3 -> reportBackToRoland.processStep()
                4 -> talkingToDrezel.processStep()
                5 -> getKeys.processStep()
                6 -> burnTheVampire.processStep()
                7 -> talkToDrezelInsidePrison
                in 8..59 -> giveDrezelEssence.processStep()
                60 -> talkToDrezelUndergroundAfterQuest
                61 -> {
                    information.complete = true
                    CommonMethods.closeQuestComplete()
                    null
                }
                else -> {
                    null
                }
            }
        }

        private fun getKeys(): QuestTaskList {
            return QuestTaskList(
                GrabSilverKey(information),
                SimpleObjectStep(Tile.Nil, arrayOf(), { Objects.nearestGameObject("Well") },
                    { go: GameObject -> InteractionsHelper.useItemOnInteractive(ITEM_BUCKET, go) },
                    { Inventory.count(NAME_MURKY_WATER) > 0 }, "Grabbing holy water", information,
                    shouldExecute = {
                        Inventory.count(NAME_MURKY_WATER, NAME_BLESSED_WATER) == 0 &&
                                AREA_SECOND_UNDERGROUND.contains(Players.local()) && Inventory.count(ITEM_IRON_KEY) == 1
                    }
                ),
                SimpleObjectStep(TILE_SECOND_FLOOR, arrayOf(),
                    { Objects.nearestGameObject(NAME_COFFIN) },
                    { go: GameObject -> InteractionsHelper.useItemOnInteractive(NAME_BLESSED_WATER, go) },
                    { Inventory.count(NAME_BLESSED_WATER) == 0 }, "Making the vampire wet", information,
                    shouldExecute = { Inventory.count(NAME_BLESSED_WATER) == 1 }),
                SimpleObjectStep(TILE_DREZEL_THIRD_FLOOR, arrayOf(), "Cell door","Open", {Inventory.count(ITEM_IRON_KEY) == 0},
                    "Opening door with key", information,
                    {Inventory.count(ITEM_IRON_KEY) == 1}).also { it.pointVariance = Point(-4, 0) },
                talkToDrezelInsidePrison
            )
        }

        private fun talkingToDrezel(): QuestTaskList {
            return QuestTaskList(
                BankStep(
                    listOf(bucketRequirement),
                    BANK_VARROCK_EAST,
                    information,
                    combat = true,
                    foodRequired = true,
                    shouldExecute = {
                        Inventory.count(ITEM_GOLDEN_KEY) == 0 && (Inventory.count(ITEM_BUCKET) == 0 ||
                                Inventory.count(*information.foodName) == 0)
                    }),
                SetupWeaponStep(information),
                KillNpcStep(TILE_CENTER_BOTTOM_FLOOR,
                    {
                        Npcs.stream().name(PriestInPerilConstants.NAME_MONK).filtered { it.combatLevel == 30 }.nearest()
                            .first()
                    }, arrayOf(ITEM_GOLDEN_KEY), { GroundItems.stream().name(ITEM_GOLDEN_KEY).nearest().toList() },
                    { Inventory.count(ITEM_GOLDEN_KEY) == 0 },
                    information,
                    "Getting golden key"
                ),
                talkToDrezelInPrisonAgain
            )
        }
}