package Combat;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import Assets.Task;
import Assets.skillData;
import script.mMain;

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