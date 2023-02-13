package MeleeCombat;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class GoCombat extends Task {
    @Override
    public boolean activate() {
        return !SkillData.Seagull_area.contains(Players.local());
    }
    @Override
    public boolean execute() {
        mMain.State = "Go to cb area";
        Movement.builder(SkillData.Seagull_area.getRandomTile()).setRunMin(45).setRunMax(75).move();
        return false;
    }
}