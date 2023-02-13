package Woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.World;

import Helpers.PlayerHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class treeTeak extends Task {
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 35;
    }

    @Override
    public boolean execute() {
        if (!PlayerHelper.WithinArea(SkillData.teakArea)) {
            mMain.State = "Go to Teak trees";
            if (SkillData.teakLocation.distanceTo(Players.local()) < 10) {
                Movement.step(SkillData.teakLocation);
            } else PlayerHelper.WalkToTile(SkillData.movementWoodcutting());
        }

        if (PlayerHelper.WithinArea(SkillData.teakArea) && Players.local().animation() == -1) {
            if (Players.stream().within(SkillData.teakArea).count() != 1) {
                int[] p2p = SkillData.p2p;
                int randomWorld = p2p[Random.nextInt(0, p2p.length - 1)];
                World world = new World(randomWorld, randomWorld, 1, World.Type.MEMBERS, World.Server.RUNE_SCAPE, World.Specialty.NONE);
                world.hop();
            }

            GameObject treeTeak = Objects.stream().within(SkillData.teakArea).id(SkillData.teakTreeID).nearest().first();
            mMain.State = "Cutting teaks";
            if (treeTeak.interact("Chop down", "Teak")) {
                Condition.wait(() -> Objects.stream().at(treeTeak.tile()).id(SkillData.teakTreeID).isEmpty(), 500, 50);
            }
        }
        return false;
    }
}
