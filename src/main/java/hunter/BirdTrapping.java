package hunter;

import static hunter.helpers.TrapStatus.birdSnareFailedID;
import static hunter.helpers.TrapStatus.birdSnareSuccessID;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

import java.util.List;

import helpers.extentions.Task;
import hunter.helpers.AmountOfTraps;
import hunter.helpers.TrapStatus;

public class BirdTrapping extends Task {
    public static Area feldipHuntingArea = new Area(new Tile(2310, 3587, 0), new Tile(2313, 3584, 0));
    int maxTraps = AmountOfTraps.getOutputValue(Skill.Hunter);
    Tile[] trapTileArray = { //Note: none of these tiles are correct.
            new Tile(2474, 3438, 0),
            new Tile(2474, 3440, 0),
            new Tile(2476, 3438, 0),
            new Tile(2476, 3440, 0),
            new Tile(2478, 3438, 0),
            new Tile(2478, 3440, 0)
    };


    @Override
    public boolean activate() {
        return Skills.realLevel(Skill.Hunter) >= 9;
    }
    @Override
    public boolean execute() {
        if (!feldipHuntingArea.contains(Players.local())) {
            moveToFeldipHuntingArea();
        }

        if (Inventory.isFull()) {
            List<Item> itemsToDrop = Inventory.stream().name("Bones", "Raw bird meat", "Orange feather").list();
            Inventory.drop(itemsToDrop);
        }
        if (feldipHuntingArea.contains(Players.local()) && !Inventory.isFull()) {
            for (int i = 0; i < trapTileArray.length && i < maxTraps; i++) {
                placeTrapsOnTiles(new Tile[]{trapTileArray[i]});
            }
            return true;
        }
        return false;
    }

    public void moveToFeldipHuntingArea() {
        System.out.println("Moving to feldip hunting area");
        Movement.moveTo(feldipHuntingArea.getRandomTile());
    }
    
    public void placeTrapsOnTiles(Tile[] tiles) {
        for (Tile tile : tiles) {
            if (TrapStatus.shouldPlaceTrap(tile)) {
                placeBirdSnare(tile);
            }
        }
    }
    private void placeBirdSnare(Tile tile) {
        if (Players.local().tile().equals(tile)) {
            GameObject birdSnareObject = Objects.stream().within(tile, 0).id(birdSnareFailedID, birdSnareSuccessID).nearest().first();
            //pickup bird snare
            if (birdSnareObject != null) {
                //pickup the object!
            }

            //Place a new birdsnare

        } else {
            Movement.step(tile);
        }
    }
}
