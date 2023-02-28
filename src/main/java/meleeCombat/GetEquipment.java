package meleeCombat;

import org.powbot.api.rt4.Players;

import helpers.CombatHelper;
import helpers.extentions.Task;
import script.mMain;

public class GetEquipment extends Task {
    @Override
    public boolean activate() {
        return Players.local().isRendered() && CombatHelper.needEquipment(MeleeData.meleeEquipment());
    }

    @Override
    public boolean execute() {
        mMain.state = "Gear up!";
        CombatHelper.gearUp(MeleeData.meleeEquipment());
        return false;
    }
}

