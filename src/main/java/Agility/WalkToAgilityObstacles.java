package Agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.dax.api.DaxWalker;
import org.powbot.dax.teleports.Teleport;

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
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 10 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 80
                && !SkillData.DraynorFloorArea.contains(Players.local())
                && !SkillData.DraynorTopArea.contains(Players.local())
                && !SkillData.DraynorCrateArea.contains(Players.local()) )
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
        if (!SkillData.GnomeObstacleLowerArea.contains(Players.local())
                || !SkillData.DraynorFloorArea.contains(Players.local())
        ){
            mMain.State = "Going to Agility course";
            Movement.moveTo(SkillData.movementAgility());
        }
    }
}
