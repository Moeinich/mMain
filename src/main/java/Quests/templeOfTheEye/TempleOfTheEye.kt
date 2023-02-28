package quests.templeOfTheEye

import helpers.extentions.count
import helpers.extentions.nearestGameObject
import org.powbot.api.Point
import org.powbot.api.Tile
import org.powbot.api.rt4.*
import quests.common.*
import quests.common.Constants.BANK_AL_KHRAID
import quests.common.Constants.ITEM_BUCKET_OF_WATER
import quests.common.base.BaseQuest
import quests.common.base.BaseQuestStep
import quests.common.base.SimpleObjectStep
import quests.common.models.ItemRequirementCondition
import quests.common.models.QuestInformation
import quests.common.models.QuestRequirements
import quests.templeOfTheEye.TempleOfTheEyeConstants.ACTION_CELL_TILE
import quests.templeOfTheEye.TempleOfTheEyeConstants.ACTION_ENTER_ALTAR
import quests.templeOfTheEye.TempleOfTheEyeConstants.ACTION_FIRE_RIFT
import quests.templeOfTheEye.TempleOfTheEyeConstants.ACTION_GUARDIAN_REMAINS
import quests.templeOfTheEye.TempleOfTheEyeConstants.ACTION_PILE
import quests.templeOfTheEye.TempleOfTheEyeConstants.ACTION_UNCHARGED_CELLS
import quests.templeOfTheEye.TempleOfTheEyeConstants.ACTION_WEAK_CELL
import quests.templeOfTheEye.TempleOfTheEyeConstants.ACTION_WORKBENCH
import quests.templeOfTheEye.TempleOfTheEyeConstants.AREA_FIRE_ALTAR
import quests.templeOfTheEye.TempleOfTheEyeConstants.AREA_INNER_ABYSS
import quests.templeOfTheEye.TempleOfTheEyeConstants.AREA_OUTSIDE_PORTAL
import quests.templeOfTheEye.TempleOfTheEyeConstants.CONVERSATION_DARK_MAGE
import quests.templeOfTheEye.TempleOfTheEyeConstants.CONVERSATION_MAGE_OF_ZAMORAK
import quests.templeOfTheEye.TempleOfTheEyeConstants.CONVERSATION_MAGE_OF_ZAMORAK2
import quests.templeOfTheEye.TempleOfTheEyeConstants.CONVERSATION_SEDRIDOR
import quests.templeOfTheEye.TempleOfTheEyeConstants.CONVERSATION_SEDRIDOR2
import quests.templeOfTheEye.TempleOfTheEyeConstants.CONVERSATION_TEA_SELLER
import quests.templeOfTheEye.TempleOfTheEyeConstants.CONVERSATION_TRAIBORN
import quests.templeOfTheEye.TempleOfTheEyeConstants.CONVERSATION_WIZARD_PERSTEN
import quests.templeOfTheEye.TempleOfTheEyeConstants.ITEM_BRONZE_PICKAXE
import quests.templeOfTheEye.TempleOfTheEyeConstants.ITEM_CHISEL
import quests.templeOfTheEye.TempleOfTheEyeConstants.ITEM_GUARDIAN_ESSENCE
import quests.templeOfTheEye.TempleOfTheEyeConstants.ITEM_GUARDIAN_FRAGMENTS
import quests.templeOfTheEye.TempleOfTheEyeConstants.ITEM_MEDIUM_CELL
import quests.templeOfTheEye.TempleOfTheEyeConstants.ITEM_STRONG_CUP_OF_TEA
import quests.templeOfTheEye.TempleOfTheEyeConstants.ITEM_UNCHARGED_CELL
import quests.templeOfTheEye.TempleOfTheEyeConstants.ITEM_WEAK_CELL
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_APPRENTICE_CORDELIA
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_APPRENTICE_FELIX
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_APPRENTICE_TAMARA
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_CATALYTIC_PILE
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_CELL_TABLE
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_CELL_TILE
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_DARK_MAGE
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_ELEMENTAL_PILE
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_FIRE_ALTAR_PORTAL
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_FIRE_RIFT
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_GREAT_GUARDIAN
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_GUARDIAN_OF_MIND
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_GUARDIAN_OF_WATER
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_GUARDIAN_REMAINS
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_MAGE_OF_ZAMORAK
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_SEDRIDOR
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_TEA_SELLER
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_UNCHARGED_CELLS
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_WEAK_CELL_TILE
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_WIZARD_PERSTEN
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_WIZARD_TRAIBORN
import quests.templeOfTheEye.TempleOfTheEyeConstants.NAME_WORKBENCH
import quests.templeOfTheEye.TempleOfTheEyeConstants.OFFSET_CORDELIA
import quests.templeOfTheEye.TempleOfTheEyeConstants.OFFSET_FELIX
import quests.templeOfTheEye.TempleOfTheEyeConstants.OFFSET_MIND
import quests.templeOfTheEye.TempleOfTheEyeConstants.OFFSET_MINING
import quests.templeOfTheEye.TempleOfTheEyeConstants.OFFSET_PRESTEN
import quests.templeOfTheEye.TempleOfTheEyeConstants.OFFSET_TAMARA
import quests.templeOfTheEye.TempleOfTheEyeConstants.OFFSET_TAMARA2
import quests.templeOfTheEye.TempleOfTheEyeConstants.OFFSET_WATER
import quests.templeOfTheEye.TempleOfTheEyeConstants.OUTSIDE_PORTAL_ID
import quests.templeOfTheEye.TempleOfTheEyeConstants.TILE_DARK_MAGE
import quests.templeOfTheEye.TempleOfTheEyeConstants.TILE_FIRE_ALTAR_PORTAL
import quests.templeOfTheEye.TempleOfTheEyeConstants.TILE_FIRE_RIFT
import quests.templeOfTheEye.TempleOfTheEyeConstants.TILE_MAGE_OF_ZAMORAK
import quests.templeOfTheEye.TempleOfTheEyeConstants.TILE_PORTAL_OUTSIDE
import quests.templeOfTheEye.TempleOfTheEyeConstants.TILE_SEDRIDOR
import quests.templeOfTheEye.TempleOfTheEyeConstants.TILE_TEA_SELLER
import quests.templeOfTheEye.TempleOfTheEyeConstants.TILE_TRAIBORN
import quests.templeOfTheEye.TempleOfTheEyeConstants.TILE_WIZARD_PERSTEN
import quests.templeOfTheEye.TempleOfTheEyeConstants.VARPBIT_SHIFT_CORDELIA
import quests.templeOfTheEye.TempleOfTheEyeConstants.VARPBIT_SHIFT_CORDELIA_INGAME
import quests.templeOfTheEye.TempleOfTheEyeConstants.VARPBIT_SHIFT_FELIX
import quests.templeOfTheEye.TempleOfTheEyeConstants.VARPBIT_SHIFT_FELIX_INGAME
import quests.templeOfTheEye.TempleOfTheEyeConstants.VARPBIT_SHIFT_TAMARA
import quests.templeOfTheEye.TempleOfTheEyeConstants.VARPBIT_SHIFT_TAMARA_INGAME
import quests.templeOfTheEye.tasks.*

class TempleOfTheEye(information: QuestInformation) : BaseQuest(information) {
    private val bucketOfWaterCondition = ItemRequirementCondition(ITEM_BUCKET_OF_WATER, false, 1)

    private val startQuest = QuestTaskList(
        BankStep(listOf(bucketOfWaterCondition, ItemRequirementCondition.emptySlots(10)), BANK_AL_KHRAID, information),
        SimpleConversationStep(
            NAME_WIZARD_PERSTEN,
            TILE_WIZARD_PERSTEN,
            CONVERSATION_WIZARD_PERSTEN,
            "Talking to wizard",
            information
        )
    )

    val talkToMageOfZamorakWithoutTea = SimpleConversationStep(
        NAME_MAGE_OF_ZAMORAK, TILE_MAGE_OF_ZAMORAK,
        CONVERSATION_MAGE_OF_ZAMORAK, "Talking to MOZ",
        information
    )

    val giveTea = QuestTaskList(
        SimpleConversationStep(
            NAME_TEA_SELLER, TILE_TEA_SELLER,
            CONVERSATION_TEA_SELLER, "Getting tea", information,
            shouldExecute = { Inventory.count(ITEM_STRONG_CUP_OF_TEA) == 0 }
        ),
        SimpleConversationStep(
            NAME_MAGE_OF_ZAMORAK, TILE_MAGE_OF_ZAMORAK,
            CONVERSATION_MAGE_OF_ZAMORAK2, "Talking to MOZ",
            information,
            shouldExecute = { Inventory.count(ITEM_STRONG_CUP_OF_TEA) > 0 })
    )

    val talkToMage = SimpleConversationStep(
        NAME_DARK_MAGE, TILE_DARK_MAGE, CONVERSATION_DARK_MAGE, "Talking to dark mage",
        information
    )

    val solvePuzzle = FinishAbyssMinigame()

    val talkToWizard = QuestTaskList(
        SimpleObjectStep(
            TILE_FIRE_RIFT,
            arrayOf(),
            NAME_FIRE_RIFT,
            ACTION_FIRE_RIFT,
            { AREA_FIRE_ALTAR.contains(Players.local()) },
            "Exiting through fire rift",
            information,
            { AREA_INNER_ABYSS.contains(Players.local()) }),
        SimpleObjectStep(
            TILE_FIRE_ALTAR_PORTAL,
            null,
            NAME_FIRE_ALTAR_PORTAL,
            "Use",
            { !AREA_FIRE_ALTAR.contains(Players.local()) },
            "Exiting fire altar",
            information,
            { AREA_FIRE_ALTAR.contains(Players.local()) }
        ),
        SimpleConversationStep(
            NAME_WIZARD_PERSTEN,
            TILE_WIZARD_PERSTEN,
            CONVERSATION_WIZARD_PERSTEN,
            "Talking to wizard",
            information
        )
    )

    val talkToSedridor = SimpleConversationStep(
        NAME_SEDRIDOR, TILE_SEDRIDOR,
        CONVERSATION_SEDRIDOR, "Talking to Sedridor",
        information
    )

    val talkToTraiborn = SimpleConversationStep(
        NAME_WIZARD_TRAIBORN, TILE_TRAIBORN, CONVERSATION_TRAIBORN, "Talking to Traiborn",
        information
    )


    val talkToApprentices = QuestTaskList(
        CloseWidget(291, 3),
        SimpleConversationStep(
            NAME_APPRENTICE_TAMARA, TILE_TRAIBORN, arrayOf(), "Talking to Tamara", information,
            shouldExecute = { Varpbits.varpbit(getQuestVarpbit(), VARPBIT_SHIFT_TAMARA, 1) == 0 }
        ),
        SimpleConversationStep(
            NAME_APPRENTICE_FELIX, TILE_TRAIBORN, arrayOf(), "Talking to Felix", information,
            shouldExecute = { Varpbits.varpbit(getQuestVarpbit(), VARPBIT_SHIFT_FELIX, 1) == 0 }
        ),
        SimpleConversationStep(
            NAME_APPRENTICE_CORDELIA, TILE_TRAIBORN, arrayOf(), "Talking to Cordelia", information,
            shouldExecute = { Varpbits.varpbit(getQuestVarpbit(), VARPBIT_SHIFT_CORDELIA, 1) == 0 }
        ),
    )

    val talkToTraibornAgain = QuestTaskList(
        CloseWidget(291, 3),
        AnswerQuestion(),
        talkToTraiborn
    )

    val talkToSedridor2 = SimpleConversationStep(
        NAME_SEDRIDOR, TILE_SEDRIDOR,
        CONVERSATION_SEDRIDOR2, "Talking to Sedridor/Cutscene",
        information,
    )

    private val enterPortal = SimpleObjectStep(
        TILE_PORTAL_OUTSIDE,
        arrayOf(),
        { Objects.nearestGameObject(OUTSIDE_PORTAL_ID) },
        { go: GameObject -> go.interact("Enter") },
        { go: GameObject -> !go.valid() }, "Re-entering minigame", information,
        shouldExecute = { AREA_OUTSIDE_PORTAL.contains(Players.local()) &&
                Objects.stream().id(OUTSIDE_PORTAL_ID).action("Enter").first() != GameObject.Nil }
    )

    val finishCutscene = QuestTaskList(
        enterPortal,
        talkToSedridor2
    )

    private val speakToFelixIngame = TalkToNpcIngame(
        NAME_APPRENTICE_FELIX, Tile.Nil, arrayOf(),
        "Talking to Felix", information,
        shouldExecute = { Varpbits.varpbit(getQuestVarpbit(), VARPBIT_SHIFT_FELIX_INGAME, 0x1) == 0 },
        offset = OFFSET_FELIX,
    )
    private val speakToTamaraIngame =
        TalkToNpcIngame(
            NAME_APPRENTICE_TAMARA, Tile.Nil, arrayOf(),
            "Talking to Tamara", information,
            shouldExecute = { Varpbits.varpbit(getQuestVarpbit(), VARPBIT_SHIFT_TAMARA_INGAME, 0x1) == 0 },
            offset = OFFSET_TAMARA,
        )

    private val speakToCordeliaIngame =
        TalkToNpcIngame(
            NAME_APPRENTICE_CORDELIA, Tile.Nil, arrayOf(),
            "Talking to Cordelia", information,
            shouldExecute = { Varpbits.varpbit(getQuestVarpbit(), VARPBIT_SHIFT_CORDELIA_INGAME, 0x1) == 0 },
            offset = OFFSET_CORDELIA,
        )

    private val speakToFelixIngame2 = talkToPersonIngame(NAME_APPRENTICE_FELIX, offset = OFFSET_FELIX)

    private val speakToPrestenIngame = talkToPersonIngameWithPortal(NAME_WIZARD_PERSTEN, offset = OFFSET_PRESTEN)

    private val speakToTamaraWithPortal = talkToPersonIngameWithPortal(NAME_APPRENTICE_TAMARA, offset = OFFSET_TAMARA)

    private val talkToApprenticesInsideArena = QuestTaskList(
        enterPortal, speakToFelixIngame, speakToTamaraIngame, speakToCordeliaIngame
    )

    private val remains =
        InteractWithObjectIngame(
            Tile.Nil, arrayOf(), NAME_GUARDIAN_REMAINS, ACTION_GUARDIAN_REMAINS,
            { Inventory.count(ITEM_GUARDIAN_FRAGMENTS) == 5 }, "Mining fragments", information,
            offset = OFFSET_MINING,
            shouldExecute = {
                if (Inventory.count(ITEM_GUARDIAN_FRAGMENTS) == 5) {
                    false
                } else {
                    val hintTile = Tile(HintArrow.x(), HintArrow.y())
                    val tile = Objects.stream().name(NAME_GUARDIAN_REMAINS).nearest(hintTile).first().tile
                    hintTile.distanceTo(tile) <= 4
                }
            }).also { it.pointVariance = Point(2, 2) }

    private val completeMinigame = QuestTaskList(
        enterPortal,
        PickupItemIngame(
            Tile.Nil, { GroundItems.stream().name(ITEM_BRONZE_PICKAXE).first() },
            { Inventory.count(ITEM_BRONZE_PICKAXE) == 0 }, "Take", "Picking up pickaxe",
            OFFSET_FELIX, information
        ),
        PickupItemIngame(
            Tile.Nil, { GroundItems.stream().name(ITEM_CHISEL).first() },
            { Inventory.count(ITEM_CHISEL) == 0 }, "Take", "Picking up pickaxe",
            OFFSET_FELIX, information
        ),

        AlterInteraction(
            Tile.Nil,
            arrayOf(),
            "Portal",
            "Use",
            { Objects.stream().name("Altar").action("Craft-rune").first() == GameObject.Nil },
            "Exiting altar",
            information,
            shouldExecute = {
                if (Inventory.count(ITEM_GUARDIAN_ESSENCE) == 5) {
                    false
                } else {
                    Objects.stream().name("Altar").action("Craft-rune").first() != GameObject.Nil
                }
            }
        ),
        AlterInteraction(
            Tile.Nil, arrayOf(), "Altar", "Craft-rune",
            { Inventory.count(ITEM_GUARDIAN_ESSENCE) == 0 }, "Making runes", information,
            shouldExecute = {
                if (HintArrow.type() != 2 || Inventory.count(ITEM_GUARDIAN_ESSENCE) == 0) {
                    false
                } else {
                    Objects.stream().name("Altar").action("Craft-rune").first() != GameObject.Nil
                }
            }
        ).also { it.pointVariance = Point(2, 2) },
        InteractWithObjectIngame(
            Tile.Nil, arrayOf(), NAME_UNCHARGED_CELLS, ACTION_UNCHARGED_CELLS,
            { Inventory.count(ITEM_UNCHARGED_CELL) == 1 }, "Making ess", information,
            offset = OFFSET_FELIX,
            shouldExecute = {
                if (Inventory.count(ITEM_UNCHARGED_CELL) == 1) {
                    false
                } else {
                    val tile = Objects.nearestGameObject(NAME_UNCHARGED_CELLS).tile
                    (HintArrow.x() == tile.x - 1 || HintArrow.x() == tile.x || HintArrow.x() == tile.x + 1) && HintArrow.y() == tile.y
                }
            }
        ),
        InteractWithObjectIngame(
            Tile.Nil, arrayOf(), NAME_WEAK_CELL_TILE, ACTION_WEAK_CELL,
            { Inventory.count(ITEM_WEAK_CELL, ITEM_MEDIUM_CELL) == 0 }, "Making ess", information,
            offset = OFFSET_FELIX,
            shouldExecute = {
                if (Inventory.count(ITEM_WEAK_CELL, ITEM_MEDIUM_CELL) == 0) {
                    false
                } else {
                    val tile = Objects.nearestGameObject(NAME_WEAK_CELL_TILE).tile
                    HintArrow.x() == tile.x && HintArrow.y() == tile.y
                }
            }
        ),
        InteractWithObjectIngame(
            Tile.Nil, arrayOf(), NAME_CELL_TABLE, "Take",
            { Inventory.count(ITEM_WEAK_CELL) == 1 }, "Taking weak cell", information,
            offset = OFFSET_FELIX,
            shouldExecute = {
                if (Inventory.count(ITEM_WEAK_CELL) > 0) {
                    false
                } else {
                    val tile = Objects.nearestGameObject(NAME_CELL_TABLE).tile
                    val hintTile = Tile(HintArrow.x(), HintArrow.y())
                    hintTile.distanceTo(tile) <= 1.5
                }
            }
        ),
        InteractWithObjectIngame(
            Tile.Nil, arrayOf(), NAME_CELL_TILE, ACTION_CELL_TILE,
            { Inventory.count(ITEM_WEAK_CELL) == 0 }, "Placing cell", information,
            offset = OFFSET_FELIX,
            shouldExecute = {
                if (Inventory.count(ITEM_WEAK_CELL) == 0) {
                    false
                } else {
                    val tile = Objects.nearestGameObject(NAME_CELL_TILE).tile
                    HintArrow.x() == tile.x && HintArrow.y() == tile.y
                }
            }
        ),
        InteractWithObjectIngame(
            Tile.Nil, arrayOf(), NAME_ELEMENTAL_PILE, ACTION_PILE,
            { Inventory.count(ITEM_WEAK_CELL, ITEM_MEDIUM_CELL) == 0 }, "Making guardian", information,
            offset = OFFSET_FELIX,
            shouldExecute = {
                if (Inventory.count(ITEM_WEAK_CELL, ITEM_MEDIUM_CELL) == 0) {
                    false
                } else {
                    val tile = Objects.nearestGameObject(NAME_ELEMENTAL_PILE).tile
                    val hintTile = Tile(HintArrow.x(), HintArrow.y())
                    hintTile.distanceTo(tile) <= 1.5
                }
            }
        ),
        InteractWithObjectIngame(
            Tile.Nil, arrayOf(), NAME_CATALYTIC_PILE, ACTION_PILE,
            { Inventory.count(ITEM_WEAK_CELL) == 0 }, "Making guardian", information,
            offset = OFFSET_FELIX,
            shouldExecute = {
                if (HintArrow.type() != 2 || Inventory.count(ITEM_WEAK_CELL) == 0) {
                    false
                } else {
                    val tile = Objects.nearestGameObject(NAME_CATALYTIC_PILE).tile
                    val hintTile = Tile(HintArrow.x(), HintArrow.y())
                    hintTile.distanceTo(tile) <= 1.5
                }
            }
        ),
        remains,
        InteractWithObjectIngame(
            Tile.Nil, arrayOf(), NAME_WORKBENCH, ACTION_WORKBENCH,
            { Inventory.count(ITEM_GUARDIAN_ESSENCE) == 5 }, "Making ess", information,
            offset = OFFSET_FELIX,
            shouldExecute =
            {
                if (Inventory.count(ITEM_GUARDIAN_ESSENCE) == 5) {
                    false
                } else {
                    val tile = Objects.nearestGameObject(NAME_WORKBENCH).tile
                    val hintTile = Tile(HintArrow.x(), HintArrow.y())
                    hintTile.distanceTo(tile) <= 1.5
                }
            }
        ),
        InteractWithObjectIngame(
            Tile.Nil, arrayOf(), NAME_WORKBENCH, ACTION_WORKBENCH,
            { Inventory.count(ITEM_GUARDIAN_ESSENCE) == 5 }, "Making ess", information,
            offset = OFFSET_FELIX,
            shouldExecute =
            {
                if (Inventory.count(ITEM_GUARDIAN_ESSENCE) == 5) {
                    false
                } else {
                    val tile = Objects.nearestGameObject(NAME_WORKBENCH).tile
                    val hintTile = Tile(HintArrow.x(), HintArrow.y())
                    hintTile.distanceTo(tile) <= 1.5
                }
            }
        ),
        InteractWithObjectIngame(
            Tile.Nil,
            arrayOf(),
            NAME_GUARDIAN_OF_MIND,
            ACTION_ENTER_ALTAR,
            { Objects.stream().name("Altar").action("Craft-Rune").first() != GameObject.Nil },
            "Crafting mind runes",
            information,
            offset = OFFSET_MIND,
            shouldExecute =
            {
                val tile = Objects.nearestGameObject(NAME_GUARDIAN_OF_MIND).tile
                val hintTile = Tile(HintArrow.x(), HintArrow.y())
                hintTile.distanceTo(tile) <= 1.5
            }
        ),
        InteractWithObjectIngame(
            Tile.Nil,
            arrayOf(),
            NAME_GUARDIAN_OF_WATER,
            ACTION_ENTER_ALTAR,
            { Objects.stream().name("Altar").action("Craft-Rune").first() != GameObject.Nil },
            "Crafting water runes",
            information,
            offset = OFFSET_WATER,
            shouldExecute =
            {
                val tile = Objects.nearestGameObject(NAME_GUARDIAN_OF_WATER).tile
                val hintTile = Tile(HintArrow.x(), HintArrow.y())
                hintTile.distanceTo(tile) <= 1.5
            }
        ),
        talkToPersonIngameHint(NAME_APPRENTICE_TAMARA, OFFSET_TAMARA2),
        talkToPersonIngameHint(NAME_APPRENTICE_FELIX, OFFSET_FELIX),
        talkToPersonIngameHint(NAME_APPRENTICE_CORDELIA, OFFSET_CORDELIA),
        talkToPersonIngameHint(NAME_GREAT_GUARDIAN, OFFSET_PRESTEN).also { it.pointVariance = Point(3, 3) },
        talkToPersonIngame(NAME_APPRENTICE_TAMARA, {true}, OFFSET_TAMARA2),
    )

    private fun talkToPersonIngameWithPortal(
        name: String,
        condition: () -> Boolean = { true },
        offset: Tile
    ): QuestTaskList {
        return QuestTaskList(
            enterPortal,
            talkToPersonIngame(name, condition, offset)
        )
    }

    private fun talkToPersonIngameHint(
        name: String,
        offset: Tile
    ): TalkToNpcIngame {
        return talkToPersonIngame(name, { HintArrow.type() == 1 && HintArrow.npc().name == name }, offset)
    }

    private fun talkToPersonIngame(
        name: String,
        condition: () -> Boolean = { true },
        offset: Tile
    ): TalkToNpcIngame {
        return TalkToNpcIngame(name, Tile.Nil, arrayOf(), "Talking to $name", information, condition, offset)
    }

    override fun addRequirements(): QuestRequirements {
        return QuestRequirements(listOf(bucketOfWaterCondition), listOf())
    }

    override fun getQuestStep(stepPosition: Int): BaseQuestStep? {
        val actualPos = stepPosition and 127
        logger.info("AP: $actualPos, SP: $stepPosition")
        return when (actualPos) {
            0, 5 -> startQuest.processStep()
            10 -> talkToMageOfZamorakWithoutTea
            15 -> giveTea.processStep()
            20 -> talkToMageOfZamorakWithoutTea
            25, 30 -> talkToMage
            35 -> solvePuzzle
            40 -> talkToMage
            45, 50, 55 -> talkToWizard.processStep()
            60, 65 -> talkToSedridor
            70 -> talkToTraiborn
            75 -> talkToApprentices.processStep()
            80 -> talkToTraibornAgain.processStep()
            85, 90, 95 -> finishCutscene.processStep()
            100 -> talkToApprenticesInsideArena.processStep()
            105 -> speakToPrestenIngame.processStep()
            110 -> speakToTamaraWithPortal.processStep()
            115 -> completeMinigame.processStep()
            120,125 -> talkToSedridor
            2 -> {
                information.complete = true
                CommonMethods.closeQuestComplete()
                null
            }
            else -> TODO("Missing ID $actualPos with varp $stepPosition")
        }
    }
}