package magicCombat;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Players;

import helpers.CombatHelper;
import helpers.InteractionsHelper;
import helpers.extentions.ItemList;
import helpers.PlayerHelper;
import helpers.extentions.Task;
import script.mMain;

public class GetMagicEquipment extends Task {
    @Override
    public boolean activate() {
        return Players.local().inViewport() && CombatHelper.needEquipment(MagicData.magicEquipment()) || !PlayerHelper.hasItem(MagicData.Runes);
    }

    @Override
    public boolean execute() {
        if (CombatHelper.needEquipment(MagicData.magicEquipment())) {
            mMain.state = "Gear up!";
            CombatHelper.gearUp(MagicData.magicEquipment());
        } else if (!CombatHelper.needEquipment(MagicData.magicEquipment()) && !PlayerHelper.hasItem(MagicData.Runes)) {
            mMain.state = "Getting runes";
            if (!PlayerHelper.hasItem("Air rune")) {
                Bank.depositInventory();
                InteractionsHelper.withdrawItem(ItemList.AIR_RUNE_556, -1);
                Condition.wait(() -> PlayerHelper.hasItem("Air rune"), 150, 50);
            }
            if (!PlayerHelper.hasItem("Mind rune")) {
                InteractionsHelper.withdrawItem(ItemList.MIND_RUNE_558, -1);
                Condition.wait(() -> PlayerHelper.hasItem("Mind rune"), 150, 50);
                Bank.close();
            }
        }
        return false;
    }
}