package src.PastShadie.scripts.mMain.Fishing;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;

public class getFishingEquipment extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && Inventory.stream().id(ItemList.SMALL_FISHING_NET_303).count() == 0 || (Inventory.stream().id(ItemList.FLY_FISHING_ROD_309).count() == 0 && Inventory.stream().id(ItemList.FEATHER_314).count() == 0);
    }
    @Override
    public void execute() {
        //Withdraw small fishing net, if none in inventory, if low level fishing(!)
        if (Inventory.stream().id(ItemList.SMALL_FISHING_NET_303).count() == 0 && Skills.realLevel(Constants.SKILLS_FISHING) <= 19) {
                Condition.wait(Bank::open, 50, 10);
                Bank.depositInventory();
                Bank.withdraw(ItemList.SMALL_FISHING_NET_303, 1);
        }

        //Barbarian equipment
        if (Inventory.stream().id(ItemList.FLY_FISHING_ROD_309).count() == 0 && Inventory.stream().id(ItemList.FEATHER_314).count() == 0 && Skills.realLevel(Constants.SKILLS_FISHING) >= 20) {
                Bank.depositInventory();
                Bank.withdraw(ItemList.FLY_FISHING_ROD_309, 1);
                Bank.withdraw(ItemList.FEATHER_314, Bank.Amount.ALL);
        }
    }
}