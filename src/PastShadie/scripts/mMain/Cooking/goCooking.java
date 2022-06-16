package src.PastShadie.scripts.mMain.Cooking;

import org.powbot.api.rt4.Movement;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class goCooking extends Task {
    @Override
    public boolean activate() {
        return Inventory.stream().id(ItemList.RAW_SHRIMPS_317,
                ItemList.RAW_ANCHOVIES_321,
                ItemList.RAW_TROUT_335,
                ItemList.RAW_SALMON_331).count() >= 1;
    }
    @Override
    public void execute() {
        Movement.builder(skillData.cookingAreaEdgeville.getRandomTile()).setRunMin(45).setRunMax(75).move();
    }
}