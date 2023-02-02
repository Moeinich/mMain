package Firemaking;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

import Helpers.ItemList;
import Helpers.Task;
import Helpers.skillData;
import script.mMain;

public class doFiremaking extends Task {
    @Override
    public boolean activate() {
        return Inventory.stream().id(skillData.logs).count() >= 1
                && Inventory.stream().id(ItemList.TINDERBOX_590).count() >= 1
                && skillData.doFiremakingArea.contains(Players.local());
    }
    @Override
    public void execute() {
        mMain.State = "Do firemaking";

        Inventory.stream().id(skillData.logs).first().interact("Use");
        Inventory.stream().id(ItemList.TINDERBOX_590).first().interact("Use");
        Condition.wait(() -> Players.local().animation() == -1, 2000,50);

        if (Inventory.stream().id(skillData.logs).count() == 0) {
            goFiremaking.fmSpot += 1;
        }
        if (goFiremaking.fmSpot == 4) {
            goFiremaking.fmSpot = 1;
        }
    }
}