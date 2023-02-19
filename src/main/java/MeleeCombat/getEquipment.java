package MeleeCombat;

import Helpers.combatHelper;
import Helpers.Task;

public class getEquipment extends Task {
    @Override
    public boolean activate() {
        return combatHelper.needEquipment(meleeData.meleeEquipment());
    }

    @Override
    public boolean execute() {
        combatHelper.gearUp(meleeData.meleeEquipment());
        return false;
    }
}

