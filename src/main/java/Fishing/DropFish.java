package Fishing;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;

import java.util.List;

import Helpers.ItemList;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class DropFish extends Task {
    public static final String[] badItems = SkillData.badItems;
    @Override
    public boolean activate() {
        return Inventory.isFull();
    }
    @Override
    public void execute() {mMain.State = "Dropping fish!";
        List<Item> itemsToDrop = Inventory.stream().name(badItems).list();
        Inventory.drop(itemsToDrop);
    }
}