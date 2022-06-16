package src.PastShadie.scripts.mMain.Woodcutting;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.walking.model.Skill;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class goWoodcutting extends Task {
    @Override
    public boolean activate() {
        return !skillData.normalTreeLocation.contains(Players.local()) && !skillData.oakTreeLocation.contains(Players.local()) && !skillData.willowTreeLocation.contains(Players.local());
    }
    @Override
    public void execute() {
        //go to normal logs
        if (Skill.Woodcutting.realLevel() <= 14) {
            Movement.builder(skillData.normalTreeLocation.getRandomTile()).setRunMin(45).setRunMax(75).move();
        }

        //go to oak logs
        if (Skill.Woodcutting.realLevel() >= 15 && Skill.Woodcutting.realLevel() < 30) {
            Movement.builder(skillData.oakTreeLocation.getRandomTile()).setRunMin(45).setRunMax(75).move();
        }

        //go to willow logs
        if (Skill.Woodcutting.realLevel() >= 30) {
            Movement.builder(skillData.willowTreeLocation.getRandomTile()).setRunMin(45).setRunMax(75).move();
        }
    }
}