package MeleeCombat;

import org.powbot.api.rt4.Combat;

import Helpers.Task;
import script.mMain;

public class SetAttackStyle extends Task {

    @Override
    public boolean activate() {
        return Combat.style() != MeleeData.AttackStyle();
    }

    @Override
    public boolean execute() {
        mMain.state = "Set style";
        Combat.style(MeleeData.AttackStyle());
        return false;
    }
}

