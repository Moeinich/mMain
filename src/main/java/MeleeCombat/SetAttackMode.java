package MeleeCombat;

import org.powbot.api.rt4.Combat;

import Helpers.Task;
import script.mMain;

public class SetAttackMode extends Task {
    @Override
    public boolean activate() {
        return !Combat.style(Combat.Style.DEFENSIVE);
    }
    @Override
    public boolean execute() {
        mMain.state = "Setting cb mode";
        System.out.print("Setting combat mode to defensive!");
        Combat.style(Combat.Style.DEFENSIVE);
        return false;
    }
}