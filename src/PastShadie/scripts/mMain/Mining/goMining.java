package src.PastShadie.scripts.mMain.Mining;

import org.powbot.api.rt4.walking.model.Skill;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

public class goMining extends Task {

    @Override
    public boolean activate() {
        if (!skillData.miningCopperLocation.equals(Players.local().tile()) && Skill.Mining.realLevel() <= 19){
            return true;
        }
        if (!skillData.miningIronLocation.equals(Players.local().tile()) && Skill.Mining.realLevel() >= 20){
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        mMain.scriptStatus = "Go to mining area";
        System.out.println("Mining level" + Skill.Mining.realLevel());
        Movement.builder(skillData.movementMining()).setRunMin(45).setRunMax(75).move();
    }
}