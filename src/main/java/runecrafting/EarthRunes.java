package runecrafting;

import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Equipment;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

import helpers.CombatHelper;
import helpers.InteractionsHelper;
import helpers.PlayerHelper;
import helpers.extentions.ItemList;
import helpers.extentions.Task;
import script.mMain;

public class EarthRunes extends Task {
    Area outsideAltar = new Area(new Tile(3302, 3478, 0), new Tile(3309, 3472, 0));
    Area insideAltar = new Area(new Tile(2627, 4862, 0), new Tile(2685, 4814, 0));
    Area varrockEastBank = new Area(new Tile(3250, 3423, 0), new Tile(3257, 3419, 0));

    @Override
    public boolean activate() {
        return Skills.realLevel(Skill.Runecrafting) <= 9;
    }
    @Override
    public boolean execute() {
        mMain.state = "Make earth runes";
        if (!PlayerHelper.hasItem("Pure essence") || Equipment.stream().name("Earth Tiara").isEmpty()) {
            mMain.state = "Bank for essence";
            Bank();
        }
        if (PlayerHelper.hasItem("Pure essence") && Equipment.stream().name("Earth Tiara").isNotEmpty()) {
            if (insideAltar.contains(Players.local())) {
                mMain.state = "inside altar!";
                InteractWithAltar();
            } else {
                mMain.state = "outside altar!";
                RunToAltar();
            }
        }
        return false;
    }

    public void Bank() {
        Movement.moveTo(varrockEastBank.getRandomTile());
        if (CombatHelper.needEquipment(new int[]{ItemList.EARTH_TIARA_5535})) {
            System.out.println("Getting earth tiara");
            CombatHelper.gearUp(new int[]{ItemList.EARTH_TIARA_5535});
        }
        if (!PlayerHelper.hasItem("Pure essence")) {
            InteractionsHelper.depositAndWithdraw(ItemList.VARROCK_TELEPORT_8007, 5);
            InteractionsHelper.withdrawItem(ItemList.PURE_ESSENCE_7936, 27);
        }
    }

    public void RunToAltar() {
        GameObject mysteriesRuins = Objects.stream().action("Enter", "Mysteries ruins").nearest().first();
        if (mysteriesRuins.inViewport()) {
            if (mysteriesRuins.click()) {
                Condition.wait(() ->PlayerHelper.withinArea(insideAltar), 1000, 50);
            }
        }
        if (!outsideAltar.contains(Players.local())) {
            System.out.println("Moving to altar ruins");
            Movement.moveTo(outsideAltar.getRandomTile());
        }
    }
    public void InteractWithAltar() {
        GameObject altar = PlayerHelper.nearestGameObject("Altar");
        System.out.print("Interacting with altar");
        if (altar.interact("Craft-rune")) {
            Condition.wait(() -> Inventory.stream().id(ItemList.PURE_ESSENCE_7936).isEmpty(), 250, 50);
        }
    }
}
