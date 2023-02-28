package agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;

import helpers.PlayerHelper;
import helpers.SkillData;
import helpers.Task;
import script.mMain;

public class WalkToAgilityObstacles extends Task {
    @Override
    public boolean activate() {
        //GNOME
        if (Skills.realLevel(Constants.SKILLS_AGILITY) <= 9
                && !PlayerHelper.withinArea(AgilityData.GnomeAreas.LOWER.getArea())
                && !PlayerHelper.withinArea(AgilityData.GnomeAreas.MID.getArea())
                && !PlayerHelper.withinArea(AgilityData.GnomeAreas.TOP.getArea()))
        {
            return true;
        }
        // DRAYNOR
        else if (Skills.realLevel(Constants.SKILLS_AGILITY) <= 10
                && !PlayerHelper.withinArea(AgilityData.DraynorAreas.FLOOR.getArea())
                && !PlayerHelper.withinArea(AgilityData.DraynorAreas.TOP.getArea())
                && !PlayerHelper.withinArea(AgilityData.DraynorAreas.CRATE.getArea()))
        {
            return true;
        }
        //VARROCK
        else if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 30
                && !PlayerHelper.withinArea(AgilityData.VarrockAreas.FLOOR.getArea())
                && !PlayerHelper.withinArea(AgilityData.VarrockAreas.FIRST.getArea())
                && !PlayerHelper.withinArea(AgilityData.VarrockAreas.MID.getArea())
                && !PlayerHelper.withinArea(AgilityData.VarrockAreas.TOP.getArea())
                && !PlayerHelper.withinArea(AgilityData.VarrockAreas.GHOST.getArea()))
        {
            return true;
        }
        //CANIFIS
        else return !SkillData.agilityDone
                    && Skills.realLevel(Constants.SKILLS_AGILITY) >= 40
                    && !PlayerHelper.withinArea(AgilityData.CanifisAreas.FLOOR.getArea())
                    && !PlayerHelper.withinArea(AgilityData.CanifisAreas.FIRST.getArea())
                    && !PlayerHelper.withinArea(AgilityData.CanifisAreas.MID.getArea())
                    && !PlayerHelper.withinArea(AgilityData.CanifisAreas.TOP.getArea());
    }
    @Override
    public boolean execute() {
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 70) {
            mMain.taskRunning.set(false);
            SkillData.setSkillDone();
        } else {
            mMain.state = "Going to Agility course";
            PlayerHelper.walkToTile(AgilityData.movementAgility());
        }
        return false;
    }
}
