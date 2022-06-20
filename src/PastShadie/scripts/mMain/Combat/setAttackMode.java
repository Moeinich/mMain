package src.PastShadie.scripts.mMain.Combat;

import org.powbot.api.rt4.Combat;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.mMain;

public class setAttackMode extends Task {
    @Override
    public boolean activate() {
        return !Combat.style(Combat.Style.DEFENSIVE);
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Setting cb mode";
        System.out.print("Setting combat mode to defensive!");
        Combat.style(Combat.Style.DEFENSIVE);
    }
}