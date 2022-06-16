package src.PastShadie.scripts.mMain.Combat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import src.PastShadie.scripts.mMain.Assets.Task;

public class drinkPotion extends Task {
    int attackPotThreshold = 5;
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_ATTACK) + attackPotThreshold > Skills.level(Constants.SKILLS_ATTACK);
    }
    @Override
    public void execute() {
        Item potion = Inventory.stream().filtered(i -> i.name().contains("Super attack")).first();
        Npc combatMonster = Npcs.stream().at(Players.local().interacting().tile()).first();

        if(potion.valid()){
            if(potion.interact("Drink")){
                if(Condition.wait(() -> !(Skills.realLevel(Constants.SKILLS_ATTACK) + 10 > Skills.level(Constants.SKILLS_ATTACK)), 100, 30)){
                    combatMonster.interact("Attack");
                }
            }
        }
    }
}