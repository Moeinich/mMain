package agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;

import helpers.PlayerHelper;
import helpers.SkillData;
import helpers.extentions.Task;
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
        //Canifis
        else if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 40
                && !PlayerHelper.withinArea(AgilityData.CanifisAreas.FLOOR.getArea())
                && !PlayerHelper.withinArea(AgilityData.CanifisAreas.FIRST.getArea())
                && !PlayerHelper.withinArea(AgilityData.CanifisAreas.MID.getArea())
                && !PlayerHelper.withinArea(AgilityData.CanifisAreas.TOP.getArea()))
        {
            return true;
        }
        //Seers
        else return Skills.realLevel(Constants.SKILLS_AGILITY) >= 60
                    && !PlayerHelper.withinArea(AgilityData.SeersAreas.FLOOR.getArea())
                    && !PlayerHelper.withinArea(AgilityData.SeersAreas.FIRST.getArea())
                    && !PlayerHelper.withinArea(AgilityData.SeersAreas.MID.getArea())
                    && !PlayerHelper.withinArea(AgilityData.SeersAreas.TOP.getArea());
    }
    @Override
    public boolean execute() {
        mMain.state = "Going to Agility course";
        PlayerHelper.walkToTile(AgilityData.movementAgility());
        return false;
    }
}
