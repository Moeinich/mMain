package Combat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class FightEnemy extends Task {
    public String npcName = "Seagull";
    Npc seagull = Npcs.stream().name(npcName).nearest().first();

    @Override
    public boolean activate() {
        mMain.State = "Fight enemy";
        return SkillData.Seagull_area.contains(Players.local()) && !Players.local().healthBarVisible();
    }
    @Override
    public void execute() {
        if (seagull.inViewport() && seagull.interact("Attack", npcName)) {
            Condition.wait(() -> !seagull.valid(),900,20);
        }
    }
}