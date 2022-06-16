package src.PastShadie.scripts.mMain.Combat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Npc;
import org.powbot.api.rt4.Npcs;
import org.powbot.api.rt4.Players;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class fightEnemy extends Task {
    public String npcName = "Seagull";
    Npc seagull = Npcs.stream().name(npcName).nearest().first();

    @Override
    public boolean activate() {
        return skillData.Seagull_area.contains(Players.local()) && !Players.local().healthBarVisible();
    }
    @Override
    public void execute() {
        if (seagull.inViewport() && seagull.interact("Attack", npcName)) {
            Condition.wait(() -> !seagull.valid(),900,20);
        }
    }
}