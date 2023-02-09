package Cooking;

import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;

import Helpers.InteractionsHelper;
import Helpers.ItemList;
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
    public void execute() {
        if (!SkillData.cookingAreaEdgeville.contains(Players.local())) {
            mMain.State = "Move to edgeville";
            MoveToEdgeville();
        }
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().name("Raw sardine").isEmpty()) {
            ShouldBank();
        }
        if (Game.tab(Game.Tab.INVENTORY) && Inventory.stream().name("Raw sardine").isNotEmpty()) {
            ShouldCook();
        }
    }
    private void MoveToEdgeville() {
        Movement.moveTo(SkillData.cookingAreaEdgeville.getRandomTile());
    }
    private void ShouldBank() {
        mMain.State = "Get raw fish";
        if (!SkillData.edgevilleBank.contains(Players.local())) {
            Movement.moveTo(SkillData.edgevilleBank.getRandomTile());
        }
        if (SkillData.edgevilleBank.contains(Players.local()) && !Bank.opened() && Bank.inViewport()) {
            Bank.open();
            InteractionsHelper interactionsHelper = new InteractionsHelper();
            interactionsHelper.DepositAndWithdraw(ItemList.RAW_SARDINE_327, 28);
        }
    }
    private void ShouldCook() {
        mMain.State = "Do cooking";
        if (!SkillData.StoveAreaEdgeville.contains(Players.local())) {
            Movement.moveTo(SkillData.StoveAreaEdgeville.getRandomTile());
        }
        if (SkillData.StoveAreaEdgeville.contains(Players.local())) {
            GameObject cookingStove = Objects.stream().id(12269).first();
            InteractionsHelper interactionsHelper = new InteractionsHelper();
            interactionsHelper.InteractWithGameobject(ItemList.RAW_SARDINE_327, cookingStove, 270, 14, "Cook", "Stove");
        }
    }
}
