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
        return Inventory.stream().id(SkillData.logs).isNotEmpty()
                && Inventory.stream().id(ItemList.TINDERBOX_590).isNotEmpty()
                && SkillData.doFiremakingArea.contains(Players.local());
    }
    @Override
    public void execute() {
        mMain.State = "Do firemaking";

        Inventory.stream().id(SkillData.logs).first().interact("Use");
        Inventory.stream().id(ItemList.TINDERBOX_590).first().interact("Use");
        Condition.wait(() -> Players.local().animation() == -1, 2000,50);

        if (Inventory.stream().id(SkillData.logs).isEmpty()) {
            GoFiremaking.fmSpot += 1;
        }
        if (GoFiremaking.fmSpot == 4) {
            GoFiremaking.fmSpot = 1;
        }
    }
}