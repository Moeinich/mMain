package MeleeCombat;

import org.powbot.api.rt4.Combat;

import Helpers.Task;
import script.mMain;

public class setAttackStyle extends Task {

    @Override
    public boolean activate() {
        return Combat.style() != meleeData.AttackStyle();
    }

    @Override
    public boolean execute() {
        mMain.state = "Set style";
        Combat.style(meleeData.AttackStyle());
        return false;
    }
}

