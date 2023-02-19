package Agility;

import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Skills;

import Helpers.playerHelper;
import Helpers.skillData;
import Helpers.Task;
import script.mMain;

public class WalkToAgilityObstacles extends Task {
    @Override
    public boolean activate() {
        //Gnome course
        if (Skills.realLevel(Constants.SKILLS_AGILITY) <= 9
                && !playerHelper.withinArea(AgilityData.GnomeAreas.LOWER.getArea())
                && !playerHelper.withinArea(AgilityData.GnomeAreas.MID.getArea())
                && !playerHelper.withinArea(AgilityData.GnomeAreas.TOP.getArea()))
        {
            return true;
        }
        //Draynor
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 10 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 29
                && !playerHelper.withinArea(AgilityData.DraynorAreas.FLOOR.getArea())
                && !playerHelper.withinArea(AgilityData.DraynorAreas.TOP.getArea())
                && !playerHelper.withinArea(AgilityData.DraynorAreas.CRATE.getArea()))
        {
            return true;
        }
        //Varrock
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 30 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 39
                && !playerHelper.withinArea(AgilityData.VarrockAreas.FLOOR.getArea())
                && !playerHelper.withinArea(AgilityData.VarrockAreas.FIRST.getArea())
                && !playerHelper.withinArea(AgilityData.VarrockAreas.MID.getArea())
                && !playerHelper.withinArea(AgilityData.VarrockAreas.TOP.getArea())
                && !playerHelper.withinArea(AgilityData.VarrockAreas.GHOST.getArea()))
        {
            return true;
        }
        //Canifis
        if (!skillData.agilityDone
                && Skills.realLevel(Constants.SKILLS_AGILITY) >= 40 && Skills.realLevel(Constants.SKILLS_AGILITY) <= 80
                && !playerHelper.withinArea(AgilityData.CanifisAreas.FLOOR.getArea())
                && !playerHelper.withinArea(AgilityData.CanifisAreas.FIRST.getArea())
                && !playerHelper.withinArea(AgilityData.CanifisAreas.MID.getArea())
                && !playerHelper.withinArea(AgilityData.CanifisAreas.TOP.getArea()))
        {
            return true;
        }
        return false;
    }
    @Override
    public boolean execute() {
        if (Skills.realLevel(Constants.SKILLS_AGILITY) >= 70) {
            mMain.taskRunning.set(false);
            skillData.setSkillDone();
        } else {
            mMain.state = "Going to Agility course";
            playerHelper.walkToTile(AgilityData.movementAgility());
        }
        return false;
    }
}
