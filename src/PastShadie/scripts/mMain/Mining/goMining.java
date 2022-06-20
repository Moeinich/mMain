package src.PastShadie.scripts.mMain.Mining;

import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

public class goMining extends Task {

    @Override
    public boolean activate() {
        return !skillData.miningCopperLocation.equals(Players.local().tile()) && !skillData.miningIronLocation.equals(Players.local().tile());
    }

    @Override
    public void execute() {
        mMain.scriptStatus = "Go to mining area";
            Movement.builder(skillData.movementMining()).setRunMin(45).setRunMax(75).move();
    }
}