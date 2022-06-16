package src.PastShadie.scripts.mMain.Cooking;

import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class doCooking extends Task {
    @Override
    public boolean activate() {
        return false;
    }
    @Override
    public void execute() {
        System.out.println("Start cooking!");
    }
}