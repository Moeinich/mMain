package Thieving;

import org.powbot.api.Condition;
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

import java.util.List;

import Assets.Task;
import Assets.skillData;
import script.mMain;

public class fruitStall extends Task {
    private enum KOUREND_FAVOR {
        ARCEUUS(4896),
        HOSIDIUS(4895),
        LOVAKENGJ(4898),
        PISCARILIUS(4899),
        SHAYZIEN(4894);
        int var;
        KOUREND_FAVOR(int var){
            this.var = var;
        }

        public int getVar(){
            return var;
        }

        public int getValue(){
            return (int) (Varpbits.value(var) * .1);
        }
        public static boolean checkedFavor = false;
        public static int hosidiusFavorValue = 0;
    }

    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_THIEVING) >= 25 && Skills.realLevel(Constants.SKILLS_THIEVING) <= 59;
    }

    public static final String[] badItems = {"Cooking apple", "Banana", "Strawberry", "Jangerberries", "Lemon", "Redberries", "Pineapple", "Lime", "Strange fruit", "Golovanova fruit top", "Papaya fruit"};
    static final int STALL_ID = 28823;
    public boolean shouldDropItems() {
        List<Item> itemsToDrop = Inventory.stream().name(badItems).list();
        return !itemsToDrop.isEmpty();
    }

    public void dropItems() {
        mMain.State = "Dropping Tea!";
        List<Item> itemsToDrop = Inventory.stream().name(badItems).list();
        Inventory.drop(itemsToDrop);
        Condition.wait(() -> itemsToDrop.isEmpty(), 20, 50);
    }
    public boolean shouldThieve() {
        return !Inventory.isFull();
    }


    @Override
    public void execute() {
        //We need to check hosidius favor!
        if (!KOUREND_FAVOR.checkedFavor) {
            mMain.State = "Checking favor!" + KOUREND_FAVOR.hosidiusFavorValue;
            KOUREND_FAVOR.hosidiusFavorValue = KOUREND_FAVOR.HOSIDIUS.getValue();
            KOUREND_FAVOR.checkedFavor = true;
        }
        if (KOUREND_FAVOR.hosidiusFavorValue <= 19) {
            mMain.taskRunning.set(false);
        }

        if (shouldDropItems()) {
            dropItems();
        }
        else if (shouldThieve() && KOUREND_FAVOR.hosidiusFavorValue >= 19) {
            if (!Game.tab(Game.Tab.INVENTORY)) {
                Condition.wait(() -> Game.tab(Game.Tab.INVENTORY), 250, 10);
            }
            if (!Players.local().tile().equals(skillData.movementThieving())) { // Need to move to our thieving spot
                mMain.State = "Walking to Thieving spot";
                Movement.walkTo(skillData.movementThieving());
                Condition.wait(() -> Players.local().tile() == skillData.movementThieving(), 150, 20);
            } else if (Players.local().animation() == -1) { // Not currently thieving
                GameObject fruitStall = Objects.stream().within(2).id(STALL_ID).nearest().first();
                if (fruitStall.valid()) {
                    if (!fruitStall.inViewport()) { // Need to turn camera to the stall
                        mMain.State = "Turning camera to tea stall";
                        Camera.turnTo(fruitStall);
                        Condition.wait(() -> fruitStall.inViewport(), 250, 10);
                    } else { // Tea stall isn't null and in view
                        mMain.State = "Stealing fruit from stall";
                        fruitStall.interact("Steal-from");
                        Condition.wait(() -> !fruitStall.valid(), 30, 45); // Turns into "market stall" (id:27537) after you steal from it
                    }
                } else {
                    mMain.State = "Waiting for stall to restock";
                }
            }
        }
    }
}
