package Cooking;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

import org.powbot.api.rt4.Inventory;

public class GoCooking extends Task {
    @Override
    public boolean activate() {
        return (!SkillData.cookingAreaEdgeville.contains(Players.local()) && (Inventory.stream().id(SkillData.rawFish).count() >= 1));
    }
    @Override
    public void execute() {
        mMain.State = "Go to cooking area";
        Movement.builder(SkillData.cookingAreaEdgeville.getRandomTile()).setRunMin(45).setRunMax(75).move();
    }
}