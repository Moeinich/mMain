package src.PastShadie.scripts.mMain.Mining;

import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.walking.model.Skill;

public class getPickaxe extends Task {
    @Override
    public boolean activate() {
        return Bank.opened();
    }

    @Override
    public void execute() {
        System.out.println("We are running the upgrade pickaxe sequence");
        String bronzePickaxe = "Bronze pickaxe";
        String steelPickaxe = "Steel pickaxe";
        String mithrilPickaxe = "Mithril pickaxe";
        String adamantPickaxe = "Adamant pickaxe";
        String runePickaxe = "Rune pickaxe";
        int amountToWithdraw = 1;

        if (Bank.opened()) {
            if (Skill.Mining.realLevel() < 6 && Inventory.stream().id(ItemList.BRONZE_PICKAXE_1265).count() == 0) {
                Bank.depositInventory();
                Bank.withdraw(bronzePickaxe, amountToWithdraw);
                Bank.close();
            }
            if (Skill.Mining.realLevel() >= 6 && Skill.Mining.realLevel() < 21 && Bank.stream().id(ItemList.STEEL_PICKAXE_1269).count() >= 1) {
                Bank.depositInventory();
                Bank.withdraw(steelPickaxe, amountToWithdraw);
                Bank.close();
            }
            if (Skill.Mining.realLevel() >= 21 && Skill.Mining.realLevel() < 31 && Bank.stream().id(ItemList.MITHRIL_PICKAXE_1273).count() >= 1) {
                Bank.depositInventory();
                Bank.withdraw(mithrilPickaxe, amountToWithdraw);
                Bank.close();
            }
            if (Skill.Mining.realLevel() >= 31 && Skill.Mining.realLevel() < 41 && Bank.stream().id(ItemList.ADAMANT_PICKAXE_1271).count() >= 1) {
                Bank.depositInventory();
                Bank.withdraw(adamantPickaxe, amountToWithdraw);
                Bank.close();
            }
            if (Skill.Mining.realLevel() > 41 && Bank.stream().id(ItemList.RUNE_PICKAXE_1275).count() >= 1) {
                Bank.depositInventory();
                Bank.withdraw(runePickaxe, amountToWithdraw);
                Bank.close();
            }
            //If bank does not have an axe upgrade, just use bronze axe like a peasant.
            if (Bank.stream().id(ItemList.STEEL_PICKAXE_1269, ItemList.MITHRIL_PICKAXE_1273, ItemList.ADAMANT_PICKAXE_1271, ItemList.RUNE_PICKAXE_1275).count() == 0) {
                Bank.depositInventory();
                Bank.withdraw(bronzePickaxe, amountToWithdraw);
                Bank.close();
            }
        }
    }
}