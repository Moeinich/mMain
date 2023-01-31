package Cooking;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import Assets.Task;
import Assets.skillData;
import script.mMain;

import org.powbot.api.rt4.Inventory;

public class goCooking extends Task {
    @Override
    public boolean activate() {
        return (!skillData.cookingAreaEdgeville.contains(Players.local()) && (Inventory.stream().id(skillData.rawFish).count() >= 1));
    }
    @Override
    public void execute() {
        mMain.State = "Go to cooking area";
        Movement.builder(skillData.cookingAreaEdgeville.getRandomTile()).setRunMin(45).setRunMax(75).move();
    }
}