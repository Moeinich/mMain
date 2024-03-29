package quests.common

import org.powbot.api.Tile
import org.powbot.api.rt4.Constants
import quests.common.models.ItemRequirement
import quests.common.models.SkillRequirement

object Constants {
    const val ITEM_SPADE = "Spade"
    const val ITEM_HAMMER = "Hammer"
    const val ITEM_COINS = "Coins"
    const val ITEM_GARLIC = "Garlic"
    const val ITEM_ROPE = "Rope"
    const val ITEM_BUCKET = "Bucket"
    const val ITEM_MILK = "Bucket of milk"
    const val ITEM_BUCKET_OF_WATER = "Bucket of water"

    const val ACTION_TALK_TO = "Talk-to"

    val BANK_AL_KHRAID: Tile = Tile(3269, 3167, 0)
    val BANK_BURG = Tile(3495,3211)
    val BANK_CANIFIS = Tile(3512, 3479)
    val BANK_DRAYNOR = Tile(3093, 3243)
    val BANK_EDGEVILLE_NE = Tile(3096, 3496)
    val BANK_EAST_FALADOR = Tile(3013, 3356)
    val BANK_LUMBRIDGE = Tile(3208, 3218, 2)
    val BANK_NORTH_ARDOUGNE = Tile(2615, 3332)
    val BANK_SOUTH_ARDOUGNE = Tile(2654, 3283)
    val BANK_VARROCK_WEST_SOUTH_SIDE = Tile(3185, 3436)
    val BANK_VARROCK_EAST = Tile(3253, 3420)
    val BANK_WEST_FALADOR = Tile(2946, 3368)

    object ItemRequirements {
        const val NAME_MILK = "Bucket of milk"

        const val ITEM_DRAGON_AXE = "Dragon axe"
        const val ITEM_RUNE_AXE = "Rune axe"
        const val ITEM_ADAMANT_AXE = "Adamant axe"
        const val ITEM_MITHRIL_AXE = "Mithril axe"
        const val ITEM_BLACK_AXE = "Black axe"
        const val ITEM_STEEL_AXE = "Steel axe"
        const val ITEM_IRON_AXE = "Iron axe"
        const val ITEM_BRONZE_AXE = "Bronze axe"

        const val ITEM_CRYSTAL_PICKAXE = "Crystal pickaxe"
        const val ITEM_THIRDAGE_PICKAXE = "3rd age pickaxe"
        const val ITEM_INFERNAL_PICKAXE = "Infernal pickaxe"
        const val ITEM_DRAGON_PICKAXE = "Dragon pickaxe"
        const val ITEM_RUNE_PICKAXE = "Rune pickaxe"
        const val ITEM_ADAMANT_PICKAXE = "Adamant pickaxe"
        const val ITEM_MITHRIL_PICKAXE = "Mithril pickaxe"
        const val ITEM_BLACK_PICKAXE = "Black pickaxe"
        const val ITEM_STEEL_PICKAXE = "Steel pickaxe"
        const val ITEM_IRON_PICKAXE = "Iron pickaxe"
        const val ITEM_BRONZE_PICKAXE = "Bronze pickaxe"

        const val ITEM_RUNE_NATURE = "Nature rune"
        const val ITEM_RUNE_COSMIC = "Cosmic rune"
        const val ITEM_RUNE_WATER = "Water rune"
        const val ITEM_RUNE_AIR = "Air rune"
        const val ITEM_RUNE_LAW = "Law rune"
        const val ITEM_RUNE_MIND = "Mind rune"
        const val ITEM_RUNE_CHAOS = "Chaos rune"

        const val ITEM_RUNE_FIRE = "Fire rune"
        const val ITEM_RUNE_EARTH = "Earth rune"

        val REQUIREMENT_ITEM_AXE = arrayOf(
            ItemRequirement(
                ITEM_DRAGON_AXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_WOODCUTTING, 61))
            ),
            ItemRequirement(
                ITEM_RUNE_AXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_WOODCUTTING, 41))
            ),
            ItemRequirement(
                ITEM_ADAMANT_AXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_WOODCUTTING, 31))
            ),
            ItemRequirement(
                ITEM_MITHRIL_AXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_WOODCUTTING, 21))
            ),
            ItemRequirement(
                ITEM_BLACK_AXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_WOODCUTTING, 11))
            ),
            ItemRequirement(
                ITEM_STEEL_AXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_WOODCUTTING, 5))
            ),
            ItemRequirement(
                ITEM_IRON_AXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_WOODCUTTING, 1))
            ),
            ItemRequirement(
                ITEM_BRONZE_AXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_WOODCUTTING, 1))
            ),
        )

        val REQUIREMENT_ITEM_PICKAXE = arrayOf(
            ItemRequirement(
                ITEM_INFERNAL_PICKAXE,
                true,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_MINING, 61))
            ),
            ItemRequirement(
                ITEM_DRAGON_PICKAXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_MINING, 61))
            ),
            ItemRequirement(
                ITEM_RUNE_PICKAXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_MINING, 41))
            ),
            ItemRequirement(
                ITEM_ADAMANT_PICKAXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_MINING, 31))
            ),
            ItemRequirement(
                ITEM_MITHRIL_PICKAXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_MINING, 21))
            ),
            ItemRequirement(
                ITEM_BLACK_PICKAXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_MINING, 11))
            ),
            ItemRequirement(
                ITEM_STEEL_PICKAXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_MINING, 5))
            ),
            ItemRequirement(
                ITEM_IRON_PICKAXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_MINING, 1))
            ),
            ItemRequirement(
                ITEM_BRONZE_PICKAXE,
                false,
                skillRequirements = arrayOf(SkillRequirement(Constants.SKILLS_MINING, 0))
            ),
        )
    }
}