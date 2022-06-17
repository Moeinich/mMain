package src.PastShadie.scripts.mMain.Cooking;

import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class goCooking extends Task {
    @Override
    public boolean activate() {
        return (!skillData.cookingAreaEdgeville.contains(Players.local()) && (Inventory.stream().id(skillData.cookedFish).count() >= 1));
    }
    @Override
    public void execute() {
        Movement.builder(skillData.cookingAreaEdgeville.getRandomTile()).setRunMin(45).setRunMax(75).move();
    }
}