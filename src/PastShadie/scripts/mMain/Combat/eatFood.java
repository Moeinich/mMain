package src.PastShadie.scripts.mMain.Combat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.mMain;

public class eatFood extends Task {
    @Override
    public boolean activate() {
        return Skills.level(Constants.SKILLS_HITPOINTS) < 5;
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Eating food";
        Npc combatMonster = Npcs.stream().at(Players.local().interacting().tile()).first();
        Item food = Inventory.stream().action("Eat").first();

        if(food.valid()){
            if(food.interact("Eat")) {
                if (Condition.wait(() -> !(Skills.level(Constants.SKILLS_HITPOINTS) < 50), 100, 30)) {
                    combatMonster.interact("Attack");
                }
            }
        }
    }
}