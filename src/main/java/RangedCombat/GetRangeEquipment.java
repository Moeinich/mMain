package RangedCombat;

import org.powbot.api.rt4.Players;

import Helpers.CombatHelper;
import Helpers.Task;
import script.mMain;

public class GetRangeEquipment extends Task {
    @Override
    public boolean activate() {
        return Players.local().isRendered() && CombatHelper.needEquipment(RangeData.rangeEquipment());
    }

    @Override
    public boolean execute() {
        mMain.state = "Gear up!";
        CombatHelper.gearUp(RangeData.rangeEquipment());
        return false;
    }
}
