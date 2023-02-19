package Helpers;

import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;

import script.mMain;

public class getFood extends Task{
    public boolean activate() {
        return Game.tab(Game.Tab.INVENTORY) && Inventory.stream().action("Eat").isEmpty();
    }

    @Override
    public boolean execute() {
        mMain.state = "Get food";
        playerHelper.bankForFood(ItemList.CAKE_1891, 15);
        return false;
    }
}
