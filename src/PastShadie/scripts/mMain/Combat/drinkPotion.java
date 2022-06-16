package src.PastShadie.scripts.mMain.Combat;

import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.Assets.Task;

public class drinkPotion extends Task {
    @Override
    public boolean activate() {
        return false;
    }
    @Override
    public void execute() {
        System.out.print("Action!");
    }
}