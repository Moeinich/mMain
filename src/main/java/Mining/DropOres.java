package Mining;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;

import java.util.List;

import Helpers.Task;
import Helpers.SkillData;
import script.mMain;


public class DropOres extends Task {
    public static final String[] badItems = SkillData.badItems;
    @Override
    public boolean activate() {
        return Inventory.isFull();
    }
    @Override
    public void execute() {mMain.State = "Dropping..";
        List<Item> itemsToDrop = Inventory.stream().name(badItems).list();
        Inventory.drop(itemsToDrop);
    }
}