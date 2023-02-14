package Agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;

import Helpers.PlayerHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class WalkToAgilityObstacles extends Task {
    @Override
    public boolean activate() {
        //Gnome course
        if (Skills.realLevel(Constants.SKILLS_AGILITY) <= 9
                && !PlayerHelper.withinArea(SkillData.GnomeObstacleLowerArea)
                && !PlayerHelper.withinArea(SkillData.GnomeObstacleAreaMidFloor)
                && !PlayerHelper.withinArea(SkillData.GnomeObstacleAreaTopFloor))
        {
            return true;
        }
        //Draynor
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 10 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 29
                && !PlayerHelper.withinArea(SkillData.DraynorFloorArea)
                && !PlayerHelper.withinArea(SkillData.DraynorTopArea)
                && !PlayerHelper.withinArea(SkillData.DraynorCrateArea))
        {
            return true;
        }
        //Varrock
        if (!SkillData.agilityDone
                && Skills.realLevel(Constants.SKILLS_AGILITY) >= 30 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 39
                && !PlayerHelper.withinArea(SkillData.VarrockFloorArea)
                && !PlayerHelper.withinArea(SkillData.VarrockFirstArea)
                && !PlayerHelper.withinArea(SkillData.VarrockMidArea)
                && !PlayerHelper.withinArea(SkillData.VarrockTopArea)
                && !PlayerHelper.withinArea(SkillData.VarrockGhostArea))
        {
            return true;
        }
        //Canifis
        if (!SkillData.agilityDone
                && Skills.realLevel(Constants.SKILLS_AGILITY) >= 40 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 80
                && !PlayerHelper.withinArea(SkillData.CanifisFloorArea)
                && !PlayerHelper.withinArea(SkillData.CanifisFirstArea)
                && !PlayerHelper.withinArea(SkillData.CanifisMidArea)
                && !PlayerHelper.withinArea(SkillData.CanifisTopArea))
        {
            return true;
        }
        return false;
    }
    @Override
    public boolean execute() {
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 70) {
            mMain.taskRunning.set(false);
            SkillData.setSkillDone();
        } else {
            mMain.State = "Going to Agility course";
            PlayerHelper.walkToTile(SkillData.movementAgility());
        }
        return false;
    }
}
