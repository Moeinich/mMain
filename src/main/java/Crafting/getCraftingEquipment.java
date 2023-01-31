package Crafting;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

import Assets.ItemList;
import Assets.Task;
import script.mMain;

public class getCraftingEquipment extends Task {
    @Override
    public boolean activate() {
        return Inventory.stream().name("Glassblowing pipe").isEmpty();
    }

    @Override
    public void execute() {
        if (Inventory.stream().name("Glassblowing pipe").isEmpty()) {
            mMain.State = "Grabbing glassblowing pipe";
            if (!Bank.opened()) {
                Bank.open();
                Bank.withdraw(ItemList.GLASSBLOWING_PIPE_1785, 1);
            }
        }
    }
}
