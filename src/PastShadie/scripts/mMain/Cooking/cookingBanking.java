package src.PastShadie.scripts.mMain.Cooking;

import org.powbot.api.Locatable;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Players;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.Assets.skillData;

public class cookingBanking extends Task {
    @Override
    public boolean activate() {
        return !Bank.open() && Inventory.stream().id(skillData.rawFish).count() == 0;
    }
    @Override
    public void execute() {
        System.out.println("banking cooking");
        Locatable nearestBank = Bank.nearest();
        if (Bank.inViewport() && nearestBank.tile().distanceTo(Players.local()) < 2) {
            Bank.open();
        } else {
            System.out.print("Move to bank!");
            Movement.moveToBank();
        }
    }
}