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
        System.out.println("We are going to WC area");
        Movement.builder(skillData.movementWoodcutting()).setRunMin(45).setRunMax(75).move();
    }
}