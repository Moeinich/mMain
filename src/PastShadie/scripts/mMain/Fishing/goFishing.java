package src.PastShadie.scripts.mMain.Fishing;

import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

import static src.PastShadie.scripts.mMain.Assets.skillData.*;

public class goFishing extends Task {
    @Override
    public boolean activate() {
        return ( (Skill.Fishing.realLevel() <= 19
                && (!AlKharidFishingSpot1.contains(Players.local()) && !AlKharidFishingSpot2.contains(Players.local())) )
                || (Skill.Fishing.realLevel() >= 20
                && !BarbarianVillageFishingArea.contains(Players.local())) );
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Go to fishing area";
        Movement.builder(skillData.movementFishing()).setRunMin(45).setRunMax(75).move();
    }
}