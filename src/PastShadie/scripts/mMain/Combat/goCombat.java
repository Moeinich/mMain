package src.PastShadie.scripts.mMain.Combat;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

public class goCombat extends Task {
    @Override
    public boolean activate() {
        return !skillData.Seagull_area.contains(Players.local());
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Go to cb area";
        Movement.builder(skillData.Seagull_area.getRandomTile()).setRunMin(45).setRunMax(75).move();
    }
}