package src.PastShadie.scripts.mMain.Mining;

import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.walking.model.Skill;

public class goMining extends Task {
    public Tile miningCopperLocation = new Tile(3287,3366);
    public Tile miningIronLocation = new Tile(3286,3368);

    @Override
    public boolean activate() {
        return !miningCopperLocation.equals(Players.local().tile()) || !miningIronLocation.equals(Players.local().tile());
    }

    @Override
    public void execute() {
        System.out.println(("We are going mining!") + miningIronLocation.equals(Players.local().tile()) + " " + miningCopperLocation.equals(Players.local().tile()) + " " + activate());
        if (Skills.realLevel(Constants.SKILLS_MINING) <= 19) {
            Movement.builder(miningCopperLocation).setRunMin(45).setRunMax(75).move();
        }
        if (Skills.realLevel(Constants.SKILLS_MINING) >= 20) {
            Movement.builder(miningIronLocation).setRunMin(45).setRunMax(75).move();
        }
    }
}