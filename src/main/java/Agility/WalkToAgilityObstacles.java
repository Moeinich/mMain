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
                && !PlayerHelper.WithinArea(SkillData.GnomeObstacleLowerArea)
                && !PlayerHelper.WithinArea(SkillData.GnomeObstacleAreaMidFloor)
                && !PlayerHelper.WithinArea(SkillData.GnomeObstacleAreaTopFloor))
        {
            return true;
        }
        //Draynor
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 10 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 29
                && !PlayerHelper.WithinArea(SkillData.DraynorFloorArea)
                && !PlayerHelper.WithinArea(SkillData.DraynorTopArea)
                && !PlayerHelper.WithinArea(SkillData.DraynorCrateArea))
        {
            return true;
        }
        //Varrock
        if (!SkillData.AgilityDone
                && Skills.realLevel(Constants.SKILLS_AGILITY) >= 30 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 39
                && !PlayerHelper.WithinArea(SkillData.VarrockFloorArea)
                && !PlayerHelper.WithinArea(SkillData.VarrockFirstArea)
                && !PlayerHelper.WithinArea(SkillData.VarrockMidArea)
                && !PlayerHelper.WithinArea(SkillData.VarrockTopArea)
                && !PlayerHelper.WithinArea(SkillData.VarrockGhostArea))
        {
            return true;
        }
        //Canifis
        if (!SkillData.AgilityDone
                && Skills.realLevel(Constants.SKILLS_AGILITY) >= 40 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 80
                && !PlayerHelper.WithinArea(SkillData.CanifisFloorArea)
                && !PlayerHelper.WithinArea(SkillData.CanifisFirstArea)
                && !PlayerHelper.WithinArea(SkillData.CanifisMidArea)
                && !PlayerHelper.WithinArea(SkillData.CanifisTopArea))
        {
            return true;
        }
        return false;
    }
    @Override
    public boolean execute() {
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 70) {
            mMain.taskRunning.set(false);
            SkillData.SetSkillDone();
        } else {
            mMain.State = "Going to Agility course";
            PlayerHelper.WalkToTile(SkillData.movementAgility());
        }
        return false;
    }
}
