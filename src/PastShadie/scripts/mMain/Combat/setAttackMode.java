package src.PastShadie.scripts.mMain.Combat;

import org.powbot.api.rt4.Combat;
import src.PastShadie.scripts.mMain.Assets.Task;

public class setAttackMode extends Task {
    @Override
    public boolean activate() {
        return !Combat.style(Combat.Style.DEFENSIVE);
    }
    @Override
    public void execute() {
        System.out.print("Setting combat mode to defensive!");
        Combat.style(Combat.Style.DEFENSIVE);
    }
}