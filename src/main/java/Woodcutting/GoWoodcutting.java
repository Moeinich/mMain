package Woodcutting;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class GoWoodcutting extends Task {
    @Override
    public boolean activate() {
        if (!SkillData.normalTreeLocation.contains(Players.local()) && Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 14){
            return true;
        }
        if (!SkillData.oakTreeLocation.contains(Players.local()) && Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 15 && (Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 30)){
            return true;
        }
        if (!SkillData.willowTreeLocation.contains(Players.local()) && Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 30 && (Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 50)){
            return true;
        }
        if (!SkillData.teakLocation.contains(Players.local()) && Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 50){
            return true;
        }
        return false;
    }
    @Override
    public void execute() {
        mMain.State = "Going to trees";
        Movement.builder(SkillData.movementWoodcutting()).setRunMin(45).setRunMax(75).move();
    }
}