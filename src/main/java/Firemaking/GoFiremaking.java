package Firemaking;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Inventory;

import Helpers.ItemList;
import Helpers.PlayerHelper;
import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class GoFiremaking extends Task {
    @Override
    public boolean activate() {
        return Inventory.stream().id(SkillData.logs).count() >= 27
                && Inventory.stream().id(ItemList.TINDERBOX_590).isNotEmpty()
                && !SkillData.firemakingArea.contains(Players.local());
    }

    public static int fmSpot = 1;
    @Override
    public void execute() {
        mMain.State = "Go to FM spot";
        PlayerHelper.WalkToTile(SkillData.moveToFiremakingSpot());
    }
}