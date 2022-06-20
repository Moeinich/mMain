package src.PastShadie.scripts.mMain.Woodcutting;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

public class goWoodcutting extends Task {
    @Override
    public boolean activate() {
        if (!skillData.normalTreeLocation.contains(Players.local()) && Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 14){
            return true;
        }
        if (!skillData.oakTreeLocation.contains(Players.local()) && Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 15 && (Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 30)){
            return true;
        }
        if (!skillData.willowTreeLocation.contains(Players.local()) && Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 30){
            return true;
        }
        return false;
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Going to trees";
        Movement.builder(skillData.movementWoodcutting()).setRunMin(45).setRunMax(75).move();
    }
}