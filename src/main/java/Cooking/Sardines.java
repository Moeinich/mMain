package Cooking;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Skills;

import Helpers.interactionHelper;
import Helpers.ItemList;
import Helpers.playerHelper;
import Helpers.skillData;
import Helpers.Task;
import script.mMain;

public class Sardines extends Task {
    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_COOKING) <= 34;
    }
    @Override
    public boolean execute() {
        if (!playerHelper.withinArea(cookingData.cookingAreaEdgeville)) {
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
        playerHelper.walkToTile(cookingData.cookingAreaEdgeville.getRandomTile());
    }
    private void ShouldBank() {
        mMain.state = "Get raw fish";
        if (!playerHelper.withinArea(skillData.edgevilleBank)) {
            playerHelper.walkToTile(skillData.edgevilleBank.getRandomTile());
        }
        if (playerHelper.withinArea(skillData.edgevilleBank) && !Bank.opened() && Bank.inViewport()) {
            Bank.open();
            interactionHelper.depositAndWithdraw(ItemList.RAW_SARDINE_327, 28);
        }
    }
    private void ShouldCook() {
        mMain.state = "Do cooking";
        if (!playerHelper.withinArea(cookingData.StoveAreaEdgeville)) {
            playerHelper.walkToTile(cookingData.StoveAreaEdgeville.getRandomTile());
        }
        if (playerHelper.withinArea(cookingData.StoveAreaEdgeville)) {
            GameObject cookingStove = playerHelper.nearestGameObject(10, 12269);
            interactionHelper.interactWithGameobject(ItemList.RAW_SARDINE_327, cookingStove, 270, 14, "Cook", "Stove", 1);
        }
    }
}
