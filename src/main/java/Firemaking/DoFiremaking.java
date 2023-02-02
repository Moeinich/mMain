package Firemaking;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

import Helpers.ItemList;
import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class DoFiremaking extends Task {
    @Override
    public boolean activate() {
        return Inventory.stream().id(SkillData.logs).count() >= 1
                && Inventory.stream().id(ItemList.TINDERBOX_590).count() >= 1
                && SkillData.doFiremakingArea.contains(Players.local());
    }
    @Override
    public void execute() {
        mMain.State = "Do firemaking";

        Inventory.stream().id(SkillData.logs).first().interact("Use");
        Inventory.stream().id(ItemList.TINDERBOX_590).first().interact("Use");
        Condition.wait(() -> Players.local().animation() == -1, 2000,50);

        if (Inventory.stream().id(SkillData.logs).count() == 0) {
            GoFiremaking.fmSpot += 1;
        }
        if (GoFiremaking.fmSpot == 4) {
            GoFiremaking.fmSpot = 1;
        }
    }
}