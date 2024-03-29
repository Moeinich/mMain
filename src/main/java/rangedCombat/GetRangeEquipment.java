package rangedCombat;

import helpers.CombatHelper;
import helpers.extentions.Task;
import script.mMain;

public class GetRangeEquipment extends Task {
    @Override
    public boolean activate() {
        return CombatHelper.needEquipment(RangeData.rangeEquipment());
    }

    @Override
    public boolean execute() {
        mMain.state = "Gear up!";
        CombatHelper.gearUp(RangeData.rangeEquipment());
        return false;
    }
}