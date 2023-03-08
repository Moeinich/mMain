package quests.clientOfKourend

import org.powbot.api.Tile

object ClientOfKourendConstants {

    const val ITEM_FEATHER = "Feather"
    const val ITEM_ENCHANTED_SCROLL = "Enchanted scroll"
    const val ITEM_ENCHANTED_QUILL = "Enchanted quill"
    const val ITEM_MYSTERIOUS_ORB = "Mysterious orb"
    const val ITEM_KOUREND_FAVOUR_CERTIFICATE = "Kourend favour certificate"
    const val ITEM_ANTIQUE_LAMP = "Antique lamp"
    const val ITEM_BROKEN_GLASS = "Broken glass"

    const val NAME_VEOS = "Veos"
    const val NAME_LEENZ = "Leenz"
    const val NAME_HORACE = "Horace"
    const val NAME_JENNIFER = "Jennier"
    const val NAME_MUNTY = "Munty"
    const val NAME_REGATH = "Regath"
    const val NAME_VEOS_CLIENT = "Veos' Client"

    val TILE_VEOS_AND_VEOS_CLIENT = Tile(1824, 3690, 0)
    val TILE_LEENZ = Tile(1807, 3726, 0)
    val TILE_REGATH = Tile(1720, 3724, 0)
    val TILE_MUNTY = Tile(1551, 3752, 0)
    val TILE_JENNIFER = Tile(1518, 3586, 0)
    val TILE_HORACE = Tile(1774, 3589, 0)
    val TILE_DARK_ALTAR = Tile(1713, 3882, 0)

    val CONVERSATION_START_VEOS = arrayOf("Have you got any quests for me?", "Sounds interesting! How can I help?", "Yes.")
    val CONVERSATION_STEP2_LEENZ = arrayOf("Can I ask you about Port Piscarilius?", "What is it that Port Piscarilius provides for Kourend?", "How do people start gaining favour in Port Piscarilius?", "Why should I gain favour with Port Piscarilius?")
    val CONVERSATION_STEP3_REGATH = arrayOf("Can I ask you about Arceuus?", "So what exactly is it the people Arceuus bring to Great Kourend?", "How do people start gaining favour in Arceuus?", "Why should I gain favour with Arceuus?")
    val CONVERSATION_STEP4_MUNTY = arrayOf("Can I ask you about Lovakengj?", "What is it that Lovakengj provides for Great Kourend?", "How do people start gaining favour in Lovakengj?", "Why should I gain favour with Lovakengj?")
    val CONVERSATION_STEP5_JENNIFER = arrayOf("Can I ask you about Shayzien?", "What is it that Shayzien provides for Great Kourend?", "How do people start gaining favour in Shayzien?", "Why should I gain favour with Shayzien?")
    val CONVERSATION_STEP6_HORACE = arrayOf("Can I ask you about Hosidius?", "What is it that Hosidius provides for Great Kourend?", "How do people start gaining favour in Hosidius?", "Why should I gain favour with Hosidius?")
    val CONVERSATION_STEP7_VEOS = arrayOf("Let's talk about your client...")
    val CONVERSATION_FINISH_VEOS = arrayOf("Let's talk about your client...")
}