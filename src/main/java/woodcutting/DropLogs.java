package woodcutting;

import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;

import java.util.List;

import helpers.extentions.Task;
import script.mMain;

public class DropLogs extends Task {
    @Override
    public boolean activate() {
        return Inventory.isFull();
    }
    @Override
    public boolean execute() {
        mMain.state = "Dropping..";
        String[] badItems = {"Logs", "Oak logs", "Willow logs", "Teak logs"};
        List<Item> itemsToDrop = Inventory.stream().name(badItems).list();
        Inventory.drop(itemsToDrop);
        return false;
    }
}