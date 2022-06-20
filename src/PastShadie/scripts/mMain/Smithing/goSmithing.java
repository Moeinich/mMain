package src.PastShadie.scripts.mMain.Smithing;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

public class goSmithing extends Task {
    @Override
    public boolean activate() {
        return !skillData.smithingAreaEdgeville.equals(Players.local().tile());
    }


    @Override
    public void execute() {
        mMain.scriptStatus = "Go smithing";
        Movement.builder(skillData.smithingAreaEdgeville.getRandomTile()).setRunMin(45).setRunMax(75).move();
    }
}