package Helpers;

import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;

import script.mMain;

public class GetFood extends Task{
    public boolean activate() {
        return Inventory.stream().action("Eat").isEmpty();
    }

    @Override
    public boolean execute() {
        mMain.state = "Get food";
        if (Game.tab(Game.Tab.INVENTORY)) {
            if (Inventory.stream().action("Eat").isEmpty()) {
                PlayerHelper.bankForFood(ItemList.CAKE_1891, 15);
                return false;
            }
        } return false;
    }
}
