package Mining;

import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import Assets.Task;
import Assets.skillData;
import script.mMain;


public class goMining extends Task {

    @Override
    public boolean activate() {
        if (!skillData.miningCopperLocation.equals(Players.local().tile()) && Skill.Mining.realLevel() <= 25){
            return true;
        }
        if (!skillData.miningIronLocation.equals(Players.local().tile()) && Skill.Mining.realLevel() >= 26){
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        mMain.State = "Go to mining area";
        Movement.builder(skillData.movementMining()).setRunMin(45).setRunMax(75).move();
    }
}