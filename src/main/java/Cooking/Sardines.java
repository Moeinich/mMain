package Cooking;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Skills;

import Helpers.InteractionsHelper;
import Helpers.ItemList;
import Helpers.PlayerHelper;
import Helpers.SkillData;
import Helpers.Task;
import script.mMain;

public class Sardines extends Task {
    @Override
    public boolean activate() {
        System.out.print("Activate deposit fish");
        return Skills.realLevel(Constants.SKILLS_COOKING) <= 34;
    }
    @Override
    public boolean execute() {
        if (!PlayerHelper.WithinArea(SkillData.cookingAreaEdgeville)) {
            mMain.State = "Move to edgeville";
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
        PlayerHelper.WalkToTile(SkillData.cookingAreaEdgeville.getRandomTile());
    }
    private void ShouldBank() {
        mMain.State = "Get raw fish";
        if (!PlayerHelper.WithinArea(SkillData.edgevilleBank)) {
            PlayerHelper.WalkToTile(SkillData.edgevilleBank.getRandomTile());
        }
        if (PlayerHelper.WithinArea(SkillData.edgevilleBank) && !Bank.opened() && Bank.inViewport()) {
            Bank.open();
            InteractionsHelper interactionsHelper = new InteractionsHelper();
            interactionsHelper.DepositAndWithdraw(ItemList.RAW_SARDINE_327, 28);
        }
    }
    private void ShouldCook() {
        mMain.State = "Do cooking";
        if (!PlayerHelper.WithinArea(SkillData.StoveAreaEdgeville)) {
            PlayerHelper.WalkToTile(SkillData.StoveAreaEdgeville.getRandomTile());
        }
        if (PlayerHelper.WithinArea(SkillData.StoveAreaEdgeville)) {
            GameObject cookingStove = Objects.stream().id(12269).first();
            InteractionsHelper interactionsHelper = new InteractionsHelper();
            interactionsHelper.InteractWithGameobject(ItemList.RAW_SARDINE_327, cookingStove, 270, 14, "Cook", "Stove");
        }
    }
}
