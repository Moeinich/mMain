package cooking;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Skills;

import helpers.InteractionsHelper;
import helpers.extentions.ItemList;
import helpers.PlayerHelper;
import helpers.SkillData;
import helpers.extentions.Task;
import script.mMain;

public class Sardines extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_COOKING) <= 34;
    }
    @Override
    public boolean execute() {
        if (!PlayerHelper.withinArea(CookingData.cookingAreaEdgeville)) {
            mMain.state = "Move to edgeville";
            MoveToEdgeville();
        }
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().name("Raw sardine").isEmpty()) {
            ShouldBank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().name("Raw sardine").isNotEmpty()) {
            ShouldCook();
        }
        return false;
    }
    private void MoveToEdgeville() {
        System.out.println("Move to edgeville bank");
        PlayerHelper.walkToTile(SkillData.edgevilleBank.getRandomTile());
    }
    private void ShouldBank() {
        mMain.state = "Get raw fish";
        if (!PlayerHelper.withinArea(SkillData.edgevilleBank)) {
            PlayerHelper.walkToTile(SkillData.edgevilleBank.getRandomTile());
        }
        if (PlayerHelper.withinArea(SkillData.edgevilleBank) && !Bank.opened() && Bank.inViewport()) {
            Bank.open();
            InteractionsHelper.depositAndWithdraw(ItemList.RAW_SARDINE_327, 28);
        }
    }
    private void ShouldCook() {
        mMain.state = "Do cooking";
        if (!PlayerHelper.withinArea(CookingData.StoveAreaEdgeville)) {
            PlayerHelper.walkToTile(CookingData.StoveAreaEdgeville.getRandomTile());
        }
        if (PlayerHelper.withinArea(CookingData.StoveAreaEdgeville)) {
            GameObject cookingStove = PlayerHelper.nearestGameObject(10, 12269);
            InteractionsHelper.interactWithGameobject(ItemList.RAW_SARDINE_327, cookingStove, 270, 14, "Cook", 1);
        }
    }
}