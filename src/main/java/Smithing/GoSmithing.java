package Smithing;

import Helpers.playerHelper;
import Helpers.Task;

public class GoSmithing extends Task {
    @Override
    public boolean activate() {
        if (!playerHelper.withinArea(smithingData.smithingArea)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean execute() {
        playerHelper.walkToTile(smithingData.smithingArea.getRandomTile());
        return false;
    }
}