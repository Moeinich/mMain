package Smithing;

import Helpers.PlayerHelper;
import Helpers.Task;

public class GoSmithing extends Task {
    @Override
    public boolean activate() {
        if (!PlayerHelper.withinArea(SmithingData.smithingArea)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean execute() {
        PlayerHelper.walkToTile(SmithingData.smithingArea.getRandomTile());
        return false;
    }
}