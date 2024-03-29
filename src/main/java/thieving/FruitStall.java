package thieving;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.World;

import java.util.List;

import helpers.PlayerHelper;
import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

public class FruitStall extends Task {

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_THIEVING) >= 25 && Skills.realLevel(Constants.SKILLS_THIEVING) <= 60;
    }

    public static final String[] badItems = {
            "Cooking apple",
            "Banana",
            "Strawberry",
            "Jangerberries",
            "Lemon",
            "Redberries",
            "Pineapple",
            "Lime",
            "Strange fruit",
            "Golovanova fruit top",
            "Papaya fruit"};
    static final int STALL_ID = 28823;
    public boolean shouldDropItems() {
        List<Item> itemsToDrop = Inventory.stream().name(badItems).list();
        return !itemsToDrop.isEmpty();
    }

    public void dropItems() {
        mMain.state = "Dropping";
        List<Item> itemsToDrop = Inventory.stream().name(badItems).list();
        Inventory.drop(itemsToDrop);
        Condition.wait(itemsToDrop::isEmpty, 20, 50);
    }


    @Override
    public boolean execute() {
        //Check favor
        if (!SkillData.KOUREND_FAVOR.checkedFavor) {
            CheckFavor();
        }
        if (SkillData.KOUREND_FAVOR.hosidiusFavorValue >= 20) {
            //Go to thieving spot
            if (!Players.local().tile().equals(ThievingData.movementThieving())) {
                WalkToSpot();
            }
            //World hop check
            else if (PlayerHelper.withinArea(ThievingData.fruitStallArea) && Players.stream().filter(player -> player.tile().equals(ThievingData.fruitStallTile) && !player.equals(Players.local())).isNotEmpty()) {
                ShouldWorldhop();
            }
            //Thieving loop
            else if (Players.local().tile().equals(ThievingData.movementThieving())) {
                if (shouldDropItems()) {
                    dropItems();
                } else if (!Inventory.isFull()) {
                    ShouldThieve();
                }
            }
        }
        return false;
    }

    private void ShouldThieve() {
        if (!Game.tab(Game.Tab.INVENTORY)) {
            Condition.wait(() -> Game.tab(Game.Tab.INVENTORY), 250, 10);
        }
        GameObject fruitStall = PlayerHelper.nearestGameObject(2, STALL_ID);
        if (fruitStall.valid() && fruitStall.inViewport()) {
            mMain.state = "Stealing fruit";
            fruitStall.interact("Steal-from");
            Condition.wait(() -> !fruitStall.valid(), 30, 45); // Turns into "market stall" (id:27537) after you steal from it
        } else {
            mMain.state = "Awaiting restock";
        }
    }
    private void ShouldWorldhop() {
        mMain.state = "Worldhopping";
        int[] p2p = SkillData.p2p;
        int randomWorld = p2p[Random.nextInt(0, p2p.length - 1)];
        World world = new World(randomWorld, randomWorld, 1, World.Type.MEMBERS, World.Server.RUNE_SCAPE, World.Specialty.NONE);
        world.hop();
    }
    private void WalkToSpot() {
        mMain.state = "Walking to Thieving spot";
        PlayerHelper.walkToTile(ThievingData.movementThieving());
    }
    private void CheckFavor() {
        //We need to check hosidius favor!
        if (!SkillData.KOUREND_FAVOR.checkedFavor) {
            System.out.print("Checking favor");
            SkillData.KOUREND_FAVOR.hosidiusFavorValue = SkillData.KOUREND_FAVOR.HOSIDIUS.getValue();
            mMain.state = "Favor: " + SkillData.KOUREND_FAVOR.hosidiusFavorValue;
        }
        if (SkillData.KOUREND_FAVOR.hosidiusFavorValue <= 19) {
            System.out.println("We dont have enough hosidius favor");
            mMain.state = "Thieving done!";
            SkillData.setSkillDone();
            mMain.skillRunning.set(false);
        } else SkillData.KOUREND_FAVOR.checkedFavor = true;
    }
}
