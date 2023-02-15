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
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.World;

import java.util.List;

import Helpers.PlayerHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class TeaStall extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_THIEVING) >= 5 && Skills.realLevel(Constants.SKILLS_THIEVING) <= 24;
    }

    public static final String[] badItems = {"Cup of tea", "Coins"};
    static final int STALL_ID = 635;
    public boolean shouldDropItems() {
        List<Item> itemsToDrop = Inventory.stream().name(badItems).list();
        return !itemsToDrop.isEmpty();
    }

    public void dropItems() {
        mMain.state = "Dropping Tea!";
        List<Item> itemsToDrop = Inventory.stream().name(badItems).list();
        if (Inventory.drop(itemsToDrop)) {
            Condition.wait(() -> itemsToDrop.isEmpty(), 250, 50);
        }
    }
    public boolean shouldThieve() {
        return !Inventory.isFull();
    }


    @Override
    public boolean execute() {
        //Stop when thieving is done!
        if (Skills.realLevel(Constants.SKILLS_THIEVING) >= 60) {
            mMain.state = "Thieving done!";
            SkillData.setSkillDone();
            mMain.taskRunning.set(false);
        }
        //Go to thieving spot
        if (!Players.local().tile().equals(SkillData.movementThieving()) && !(SkillData.movementThieving().tile().distanceTo(Players.local()) < 3)) {
            WalkToSpot();
        }
        //World hop check
        if (PlayerHelper.withinArea(SkillData.teaStallArea) && Players.stream().within(SkillData.teaStallArea).count() != 1) {
            ShouldWorldhop();
        }
        //Thieving loop
        if (Players.stream().within(SkillData.teaStallArea).count() == 1) {
            if (shouldDropItems() && PlayerHelper.withinArea(SkillData.teaStallArea)) {
                dropItems();
            }
            else if (Inventory.isEmpty() && PlayerHelper.withinArea(SkillData.teaStallArea)) {
                ShouldThieve();
            }
        }
        return false;
    }
    private void ShouldThieve() {
        if (!Game.tab(Game.Tab.INVENTORY)) {
            Condition.wait(() -> Game.tab(Game.Tab.INVENTORY), 250, 10);
        }
        GameObject teaStall = PlayerHelper.nearestGameObject(5, STALL_ID);
        if (teaStall.valid() && Players.stream().within(SkillData.teaStallArea).count() == 1) {
            if (!teaStall.inViewport()) { // Need to turn camera to the stall
                mMain.state = "Turning camera to tea stall";
                Camera.turnTo(teaStall);
                Condition.wait(() -> teaStall.inViewport(), 250, 10);
            } else { // Fruit stall isn't null and in view
                mMain.state = "Stealing fruit from stall";
                teaStall.interact("Steal-from");
                Condition.wait(() -> !teaStall.valid(), 30, 45); // Turns into "market stall" (id:27537) after you steal from it
            }
        } else {
            mMain.state = "Waiting for stall to restock";
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
        if (!Players.local().tile().equals(SkillData.movementThieving()) && !(SkillData.movementThieving().tile().distanceTo(Players.local()) < 3)) { // Need to move to our thieving spot
            mMain.state = "Walking to Thieving spot";
            PlayerHelper.walkToTile(SkillData.movementThieving());
            Condition.wait(() -> SkillData.movementThieving().tile().distanceTo(Players.local()) < 3, 150, 20);
            if (SkillData.movementThieving().tile().distanceTo(Players.local()) < 3) {
                Movement.step(SkillData.movementThieving());
            }
        }
    }
}
