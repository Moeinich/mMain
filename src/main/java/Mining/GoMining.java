package Mining;

import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;


public class GoMining extends Task {

    @Override
    public boolean activate() {
        if (!SkillData.miningCopperLocation.equals(Players.local().tile()) && Skill.Mining.realLevel() <= 25){
            return true;
        }
        if (!SkillData.miningIronLocation.equals(Players.local().tile()) && Skill.Mining.realLevel() >= 26){
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        mMain.State = "Go to mining area";
        Movement.builder(SkillData.movementMining()).setRunMin(45).setRunMax(75).move();
    }
}