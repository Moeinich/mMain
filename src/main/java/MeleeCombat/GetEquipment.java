package MeleeCombat;

import Helpers.CombatHelper;
import Helpers.Task;

public class GetEquipment extends Task {
    @Override
    public boolean activate() {
        return CombatHelper.needEquipment(MeleeData.meleeEquipment());
    }

    @Override
    public boolean execute() {
        CombatHelper.gearUp(MeleeData.meleeEquipment());
        return false;
    }
}

