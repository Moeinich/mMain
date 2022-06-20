package src.PastShadie.scripts.mMain.Woodcutting;

import org.powbot.api.rt4.walking.model.Skill;
import src.PastShadie.scripts.mMain.Assets.ItemList;
import src.PastShadie.scripts.mMain.Assets.Task;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;
import src.PastShadie.scripts.mMain.mMain;

public class getAxe extends Task {
    @Override
    public boolean activate() {
        return Bank.opened() && (
                Skill.Woodcutting.realLevel() < 6 ||
                        Skill.Woodcutting.realLevel() >= 6 && Skill.Woodcutting.realLevel() < 21 && Bank.stream().id(ItemList.STEEL_AXE_1353).count() >= 1 ||
                        Skill.Woodcutting.realLevel() >= 21 && Skill.Woodcutting.realLevel() < 31 && Bank.stream().id(ItemList.MITHRIL_AXE_1355).count() >= 1 ||
                        Skill.Woodcutting.realLevel() >= 31 && Skill.Woodcutting.realLevel() < 41 && Bank.stream().id(ItemList.ADAMANT_AXE_1357).count() >= 1 ||
                        Skill.Woodcutting.realLevel() > 41 && Bank.stream().id(ItemList.RUNE_AXE_1359).count() >= 1 ||
                        Bank.stream().id(ItemList.STEEL_AXE_1353, ItemList.MITHRIL_AXE_1355, ItemList.ADAMANT_AXE_1357, ItemList.RUNE_AXE_1359).count() == 0
        );
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Getting an axe";
        String bronzeAxe = "Bronze axe";
        String steelAxe = "Steel axe";
        String mithrilAxe = "Mithril axe";
        String adamantAxe = "Adamant axe";
        String runeAxe = "Rune axe";
        int amountToWithdraw = 1;

        if (Skill.Woodcutting.realLevel() < 6) {
            Bank.depositInventory();
            Bank.withdraw(bronzeAxe, amountToWithdraw);
        }
        if (Skill.Woodcutting.realLevel() >= 6 && Skill.Woodcutting.realLevel() < 21) {
            Bank.depositInventory();
            Bank.withdraw(steelAxe, amountToWithdraw);
        }
        if (Skill.Woodcutting.realLevel() >= 21 && Skill.Woodcutting.realLevel() < 31) {
            Bank.depositInventory();
            Bank.withdraw(mithrilAxe, amountToWithdraw);
        }
        if (Skill.Woodcutting.realLevel() >= 31 && Skill.Woodcutting.realLevel() < 41) {
            Bank.depositInventory();
            Bank.withdraw(adamantAxe, amountToWithdraw);
        }
        if (Skill.Woodcutting.realLevel() > 41) {
            Bank.depositInventory();
            Bank.withdraw(runeAxe, amountToWithdraw);
        }
        //If bank does not have an axe upgrade, just use bronze axe like a peasant.
        if (Bank.stream().id(ItemList.STEEL_AXE_1353, ItemList.MITHRIL_AXE_1355, ItemList.ADAMANT_AXE_1357, ItemList.RUNE_AXE_1359).count() == 0) {
            Bank.depositInventory();
            Bank.withdraw(bronzeAxe, amountToWithdraw);
        }
    }
}