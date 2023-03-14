package smithing;

import helpers.PlayerHelper;
import helpers.extentions.Task;

public class GoSmithing extends Task {
    @Override
    public boolean activate() {
        return !PlayerHelper.withinArea(SmithingData.smithingArea);
    }

    @Override
    public boolean execute() {
        PlayerHelper.walkToTile(SmithingData.smithingArea.getRandomTile());
        return false;
    }
}