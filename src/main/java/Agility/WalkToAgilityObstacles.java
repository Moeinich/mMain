package Agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class WalkToAgilityObstacles extends Task {
    @Override
    public boolean activate() {
        //Gnome course
        if ( Skills.realLevel(Constants.SKILLS_AGILITY) <= 9
                && !SkillData.GnomeObstacleLowerArea.contains(Players.local())
                && !SkillData.GnomeObstacleAreaMidFloor.contains(Players.local())
                && !SkillData.GnomeObstacleAreaTopFloor.contains(Players.local()) )
        {
            return true;
        }
        //Draynor
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 10 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 29
                && !SkillData.DraynorFloorArea.contains(Players.local())
                && !SkillData.DraynorTopArea.contains(Players.local())
                && !SkillData.DraynorCrateArea.contains(Players.local()) )
        {
            return true;
        }
        //Varrock
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 30 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 80
                && !SkillData.VarrockFloorArea.contains(Players.local())
                && !SkillData.VarrockFirstArea.contains(Players.local())
                && !SkillData.VarrockMidArea.contains(Players.local())
                && !SkillData.VarrockTopArea.contains(Players.local())
                && !SkillData.VarrockGhostArea.contains(Players.local()) )
        {
            return true;
        }
        return false;
    }
    @Override
    public void execute() {
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 99) {
            mMain.taskRunning.set(false);
        }
        mMain.State = "Going to Agility course";
        Movement.moveTo(SkillData.movementAgility());
    }
}
