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
import org.powbot.api.rt4.World;

import java.util.List;

import Helpers.PlayerHelper;
import Helpers.Task;
import Helpers.SkillData;
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
        mMain.State = "Dropping Tea!";
        List<Item> itemsToDrop = Inventory.stream().name(badItems).list();
        if (Inventory.drop(itemsToDrop)) {
            Condition.wait(() -> itemsToDrop.isEmpty(), 250, 50);
        }
    }
    public boolean shouldThieve() {
        return !Inventory.isFull();
    }


    @Override
    public void execute() {
        if (shouldDropItems()) {
            dropItems();
        }
        else if (shouldThieve()) {
            if (!Game.tab(Game.Tab.INVENTORY)) {
                Condition.wait(() -> Game.tab(Game.Tab.INVENTORY), 250, 10);
            }
            if (!Players.local().tile().equals(SkillData.movementThieving()) && !(SkillData.movementThieving().tile().distanceTo(Players.local()) < 3)) { // Need to move to our thieving spot
                mMain.State = "Walking to Thieving spot";
                PlayerHelper.WalkToTile(SkillData.movementThieving());
                Condition.wait(() -> SkillData.movementThieving().tile().distanceTo(Players.local()) < 3, 150, 20);
                if (SkillData.movementThieving().tile().distanceTo(Players.local()) < 3) {
                    Movement.step(SkillData.movementThieving());
                }
            } else if (Players.local().animation() == -1) { // Not currently thieving
                if (Players.stream().within(SkillData.teaStallArea).count() != 1) {
                    int[] p2p = SkillData.p2p;
                    int randomWorld = p2p[Random.nextInt(0, p2p.length - 1)];
                    World world = new World(2, randomWorld, 1, World.Type.MEMBERS, World.Server.RUNE_SCAPE, World.Specialty.NONE);
                    world.hop();
                }

                GameObject teaStall = Objects.stream().within(2).id(STALL_ID).nearest().first();
                if (teaStall.valid() && Players.stream().within(SkillData.teaStallArea).count() == 1) {
                    if (!teaStall.inViewport()) { // Need to turn camera to the stall
                        mMain.State = "Turning camera to tea stall";
                        Camera.turnTo(teaStall);
                        Condition.wait(() -> teaStall.inViewport(), 250, 10);
                    } else { // Tea stall isn't null and in view
                        mMain.State = "Stealing tea from stall";
                        teaStall.interact("Steal-from");
                        Condition.wait(() -> !teaStall.valid(), 150, 50); // Turns into "market stall" (id:634) after you steal from it
                    }
                } else {
                    mMain.State = "Waiting for stall to restock";
                }
            }
        }
    }
}
