package MeleeCombat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

import Helpers.Task;
import script.mMain;

public class DrinkPotion extends Task {
    int attackPotThreshold = 5;
    @Override
    public boolean activate() {
        //Currently does attack potion!
        return Skills.realLevel(Constants.SKILLS_ATTACK) + attackPotThreshold > Skills.level(Constants.SKILLS_ATTACK);
    }
    @Override
    public boolean execute() {
        mMain.State = "Drink potion";
        Item potion = Inventory.stream().filtered(i -> i.name().contains("Super attack")).first();
        Npc combatMonster = Npcs.stream().at(Players.local().interacting().tile()).first();

        if(potion.valid()){
            if(potion.interact("Drink")){
                if(Condition.wait(() -> !(Skills.realLevel(Constants.SKILLS_ATTACK) + 10 > Skills.level(Constants.SKILLS_ATTACK)), 100, 30)){
                    combatMonster.interact("Attack");
                }
            }
        }
        return false;
    }
}