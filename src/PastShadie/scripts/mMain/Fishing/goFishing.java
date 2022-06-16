package src.PastShadie.scripts.mMain.Fishing;

import org.powbot.api.rt4.*;
import org.powbot.api.rt4.walking.model.Skill;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;

import static src.PastShadie.scripts.mMain.Assets.skillData.*;

public class goFishing extends Task {
    @Override
    public boolean activate() {
        return ((Skill.Fishing.realLevel() <= 19 && (!AlKharidFishingSpot1.contains(Players.local()) || !AlKharidFishingSpot2.contains(Players.local()))) ||
                Skill.Fishing.realLevel() >= 20 && !BarbarianVillageFishingArea.contains(Players.local()));
    }
    @Override
    public void execute() {
        //Al Kharid fishing spot below bank
        if (Skill.Fishing.realLevel() <= 19) {
            Movement.builder(AlKharidFishingSpot1.getRandomTile()).setRunMin(45).setRunMax(75).move();

            if (!AlKharidFishingSpot1.contains(AlKharidFishingSpot)){
                Movement.builder(AlKharidFishingSpot2.getRandomTile()).setRunMin(45).setRunMax(75).move();
            }
            if (!AlKharidFishingSpot2.contains(AlKharidFishingSpot)){
                Movement.builder(AlKharidFishingSpot1.getRandomTile()).setRunMin(45).setRunMax(75).move();
            }
        }

        //Barbarian village
        if (Skill.Fishing.realLevel() >= 20) {
            Movement.builder(BarbarianVillageFishingArea.getRandomTile()).setRunMin(45).setRunMax(75).move();
        }
    }
}