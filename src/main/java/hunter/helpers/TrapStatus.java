package hunter.helpers;

import org.powbot.api.Tile;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Objects;

public class TrapStatus {
    final static int placedBirdSnareID = 9345;
    public final static int birdSnareFailedID = 9344;
    public final static int birdSnareSuccessID = 9379;
    public static boolean placedBirdSnare;
    public static boolean birdSnareFailed;
    public static boolean birdSnareSuccess;

    // Constructor to initialize tile status
    public TrapStatus(boolean placedBirdSnare, boolean birdSnareFailed, boolean birdSnareSuccess) {
        TrapStatus.placedBirdSnare = placedBirdSnare;
        TrapStatus.birdSnareFailed = birdSnareFailed;
        TrapStatus.birdSnareSuccess = birdSnareSuccess;
    }

    // Getter methods for tile status
    public boolean hasPlacedBirdSnare() {
        return placedBirdSnare;
    }

    public boolean hasBirdSnareFailed() {
        return birdSnareFailed;
    }

    public boolean hasBirdSnareSuccess() {
        return birdSnareSuccess;
    }

    public static boolean shouldPlaceTrap(Tile tile) {
        GameObject failedBirdSnareObject = Objects.stream().within(tile, 0).id(birdSnareFailedID).nearest().first();
        GameObject birdSnareSuccessObject = Objects.stream().within(tile, 0).id(birdSnareSuccessID).nearest().first();
        GameObject birdSnareObject = Objects.stream().within(tile, 0).id(placedBirdSnareID).nearest().first();

        if (failedBirdSnareObject != null) {
            birdSnareFailed = true;
            System.out.println("Trap has failed!");
            return true;
        } else if (birdSnareSuccessObject != null) {
            birdSnareSuccess = true;
            System.out.println("Trap was a success!");
            return true;
        } else if (birdSnareObject != null) {
            placedBirdSnare = true;
            System.out.println("There is a trap on the location");
            return false;
        }
        return true;
    }
}

