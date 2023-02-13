package Thieving;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Camera;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Varpbits;
import org.powbot.api.rt4.World;

import java.util.List;

import Helpers.PlayerHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class FruitStall extends Task {
    private enum KOUREND_FAVOR {
        HOSIDIUS(4895);
        final int var;
        KOUREND_FAVOR(int var){
            this.var = var;
        }
        public int getValue(){
            return (int) (Varpbits.value(var) * .1);
        }
        public static boolean checkedFavor = false;
        public static int hosidiusFavorValue = 0;
    }

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
        mMain.State = "Dropping Tea!";
        List<Item> itemsToDrop = Inventory.stream().name(badItems).list();
        if (Inventory.drop(itemsToDrop)) {
            Condition.wait(itemsToDrop::isEmpty, 20, 50);
        }
    }


    @Override
    public boolean execute() {
        //Stop when thieving is done!
        if (Skills.realLevel(Constants.SKILLS_THIEVING) >= 60) {
            mMain.State = "Thieving done!";
            SkillData.SetSkillDone();
            mMain.taskRunning.set(false);
        }
        //We need to check hosidius favor!
        if (!KOUREND_FAVOR.checkedFavor) {
            mMain.State = "Checking favor!" + KOUREND_FAVOR.hosidiusFavorValue;
            KOUREND_FAVOR.hosidiusFavorValue = KOUREND_FAVOR.HOSIDIUS.getValue();
            KOUREND_FAVOR.checkedFavor = true;
        }
        if (KOUREND_FAVOR.hosidiusFavorValue <= 19) {
            mMain.taskRunning.set(false);
        }
        //Go to thieving spot
        if (!Players.local().tile().equals(SkillData.movementThieving()) && !(SkillData.movementThieving().tile().distanceTo(Players.local()) < 3)) {
            WalkToSpot();
        }
        //World hop check
        if (PlayerHelper.WithinArea(SkillData.fruitStallArea) && Players.stream().within(SkillData.fruitStallArea).count() != 1) {
            ShouldWorldhop();
        }
        //Thieving loop
        if (Players.stream().within(SkillData.fruitStallArea).count() == 1) {
            if (shouldDropItems() && PlayerHelper.WithinArea(SkillData.fruitStallArea)) {
                dropItems();
            }
            else if (KOUREND_FAVOR.hosidiusFavorValue >= 20 && Inventory.isEmpty() && PlayerHelper.WithinArea(SkillData.fruitStallArea)) {
                ShouldThieve();
            }
        }
        return false;
    }

    private void ShouldThieve() {
        if (!Game.tab(Game.Tab.INVENTORY)) {
            Condition.wait(() -> Game.tab(Game.Tab.INVENTORY), 250, 10);
        }
        GameObject fruitStall = Objects.stream().within(2).id(STALL_ID).nearest().first();
        if (fruitStall.valid() && Players.stream().within(SkillData.fruitStallArea).count() == 1) {
            if (!fruitStall.inViewport()) { // Need to turn camera to the stall
                mMain.State = "Turning camera to tea stall";
                Camera.turnTo(fruitStall);
                Condition.wait(() -> fruitStall.inViewport(), 250, 10);
            } else { // Fruit stall isn't null and in view
                mMain.State = "Stealing fruit from stall";
                fruitStall.interact("Steal-from");
                Condition.wait(() -> !fruitStall.valid(), 30, 45); // Turns into "market stall" (id:27537) after you steal from it
            }
        } else {
            mMain.State = "Waiting for stall to restock";
        }
    }
    private void ShouldWorldhop() {
        mMain.State = "Worldhopping";
        if (Players.stream().within(SkillData.fruitStallArea).count() != 1) {
            int[] p2p = SkillData.p2p;
            int randomWorld = p2p[Random.nextInt(0, p2p.length - 1)];
            World world = new World(2, randomWorld, 1, World.Type.MEMBERS, World.Server.RUNE_SCAPE, World.Specialty.NONE);
            world.hop();
        }
    }
    private void WalkToSpot() {
        if (!Players.local().tile().equals(SkillData.movementThieving()) && !(SkillData.movementThieving().tile().distanceTo(Players.local()) < 3)) { // Need to move to our thieving spot
            mMain.State = "Walking to Thieving spot";
            PlayerHelper.WalkToTile(SkillData.movementThieving());
            Condition.wait(() -> SkillData.movementThieving().tile().distanceTo(Players.local()) < 3, 150, 20);
            if (SkillData.movementThieving().tile().distanceTo(Players.local()) < 3) {
                Movement.step(SkillData.movementThieving());
            }
        }
    }
}
