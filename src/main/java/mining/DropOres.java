package mining;

import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;

import java.util.List;

import helpers.extentions.Task;
import script.mMain;


public class DropOres extends Task {
    public static final String[] badItems = {
            "Copper ore",
            "Tin ore",
            "Iron ore",
            "Coal",
            "Uncut sapphire",
            "Uncut emerald",
            "Uncut ruby",
            "Uncut diamond"
    };

    @Override
    public boolean activate() {
        return Inventory.isFull();
    }
    @Override
    public boolean execute() {mMain.state = "Dropping..";
        List<Item> itemsToDrop = Inventory.stream().name(badItems).list();
        Inventory.drop(itemsToDrop);
        return false;
    }
}