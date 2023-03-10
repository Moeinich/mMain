package hunter;

import static hunter.helpers.TrapStatus.birdSnareFailedID;
import static hunter.helpers.TrapStatus.birdSnareSuccessID;

import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Locatable;
import org.powbot.api.Random;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

import java.util.List;

import helpers.InteractionsHelper;
import helpers.extentions.ItemList;
import helpers.extentions.Task;
import hunter.helpers.AmountOfTraps;
import hunter.helpers.TrapStatus;
import script.mMain;

public class BirdTrapping extends Task {
    public static Area feldipHuntingArea = new Area(new Tile(2362, 3588, 0), new Tile(2356, 3594, 0));
    int maxTraps = AmountOfTraps.getOutputValue(Skill.Hunter);
    Tile[] trapTileArray = {
            new Tile(2359, 3592, 0),
            new Tile(2359, 3590, 0),
            new Tile(2360, 3591, 0),
            new Tile(2358, 3591, 0),
            new Tile(2358, 3590, 0)
    };


    @Override
    public boolean activate() {
        return Skills.realLevel(Skill.Hunter) >= 9;
    }
    @Override
    public boolean execute() {
        if (Inventory.stream().id(ItemList.BIRD_SNARE_10006).isEmpty()) {
            bank();
        }

        if (!feldipHuntingArea.contains(Players.local())) {
            moveToFeldipHuntingArea();
        }

        if (Inventory.isFull()) {
            List<Item> itemsToDrop = Inventory.stream().name("Bones", "Raw bird meat", "Orange feather").list();
            Inventory.drop(itemsToDrop);
        }
        if (feldipHuntingArea.contains(Players.local()) && !Inventory.isFull()) {
            for (int i = 0; i < trapTileArray.length && i < maxTraps; i++) {
                checkTrapStatusOnTile(new Tile[]{trapTileArray[i]});
            }
            Condition.sleep(Random.nextInt(250, 1000));
        }
        return false;
    }

    public void moveToFeldipHuntingArea() {
        System.out.println("Moving to feldip hunting area");
        Movement.moveTo(feldipHuntingArea.getRandomTile());
    }
    
    public void checkTrapStatusOnTile(Tile[] tiles) {
        System.out.println("We are placing traps on missing tiles!");
        for (Tile tile : tiles) {
            if (TrapStatus.shouldPlaceTrap(tile)) {
                placeBirdSnare(tile);
            }
        }
    }
    private void placeBirdSnare(Tile tile) {
        if (Players.local().tile().equals(tile)) {
            GameObject birdSnareObject = Objects.stream().within(tile, 0).id(birdSnareFailedID, birdSnareSuccessID).nearest().first();
            Item birdsnareItem = Inventory.stream().id(ItemList.BIRD_SNARE_10006).first();

            if (birdSnareObject != null) {
                System.out.println("Picking up birdsnare on tile: " + tile);
                birdSnareObject.interact("Pick-up");
            } else {
                System.out.println("Setting up new trap on: " + tile);
                birdsnareItem.click();
            }
        } else {
            System.out.println("Stepping to tile: " + tile);
            Movement.step(tile);
            Condition.wait(() -> Players.local().tile().equals(tile) && !Players.local().inMotion(), 300, 10);
        }
    }
    private void bank() {
        Locatable nearestBank = Bank.nearest();
        if (Bank.inViewport() && nearestBank.tile().distanceTo(Players.local()) < 4) {
            Bank.open();
        } else {
            System.out.println("Moving to bank");
            Movement.moveToBank();
        }

        if (Bank.opened() && Inventory.stream().id(ItemList.BIRD_SNARE_10006).isEmpty()) {
            mMain.state = "Withdraw Bird snare";
            InteractionsHelper.depositAndWithdraw(ItemList.BIRD_SNARE_10006, 5);
        }
    }
}
