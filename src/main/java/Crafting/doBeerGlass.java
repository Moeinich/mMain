package Crafting;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Widgets;
import org.powbot.mobile.script.ScriptManager;

import java.util.concurrent.Callable;

import Assets.ItemList;
import Assets.Task;
import script.mMain;

public class doBeerGlass extends Task {
    int timer = 0;
    int initialCount = (int) Inventory.stream().id(ItemList.MOLTEN_GLASS_1775).count();


    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_CRAFTING) < 5;
    }

    @Override
    public void execute() {
            if (Inventory.stream().id(ItemList.MOLTEN_GLASS_1775).count() == 0) {
                mMain.State = "Banking loop";
                if (!Bank.opened() && Bank.inViewport()) {
                    Bank.open();
                }
                if (Inventory.stream().name("Molten glass").count() == 0) {
                    Bank.depositAllExcept("Glassblowing pipe");
                }
                if (Inventory.stream().name("Glassblowing pipe").count() == 0) {
                    Bank.withdraw("Glassblowing pipe", 1);
                }
                if (Bank.stream().name("Molten glass").first().stackSize() < 27) {
                    mMain.State = "We ran out of MG";
                    mMain.taskRunning.set(false); //Skip task on progressive
                } else {
                    Bank.withdraw("Molten glass", 27);
                    Bank.close();
                    Condition.wait(() -> !Bank.opened(), 200,50);
                }
            } else if (Game.tab(Game.Tab.INVENTORY)) {
                while (!ScriptManager.INSTANCE.isStopping()) {
                    mMain.State = "Crafting loop";
                    int currentCount = (int) Inventory.stream().id(ItemList.MOLTEN_GLASS_1775).count();
                    if (currentCount >= initialCount) {
                        timer += 2;
                        if (timer >= 2) {
                            Item glassblowingPipe = Inventory.stream().name("Glassblowing pipe").first();
                            Item moltenGlass = Inventory.stream().name("Molten glass").first();

                            if (Inventory.stream().id(ItemList.MOLTEN_GLASS_1775).count() >= 1 && Game.tab(Game.Tab.INVENTORY)) {
                                if (ScriptManager.INSTANCE.isStopping()) {
                                    ScriptManager.INSTANCE.stop();
                                }
                                if (Inventory.selectedItem().id() != glassblowingPipe.id() && !Widgets.widget(270).valid()) {
                                    glassblowingPipe.interact("Use");
                                    Condition.wait((Callable<Boolean>) () -> Inventory.selectedItem().id() == glassblowingPipe.id(), 150, 20);
                                }
                                if (Inventory.selectedItem().id() == glassblowingPipe.id()) {
                                    moltenGlass.interact("Use");
                                    Condition.wait((Callable<Boolean>) () -> Widgets.widget(270).valid(), 500, 20);
                                }
                                if (Widgets.widget(270).valid()) {
                                    Widgets.widget(270).component(14).click(); //Widget is 270, Beer glass has component 14.
                                    Condition.wait((Callable<Boolean>) () -> !Widgets.widget(270).valid(), 150, 20);
                                }
                            }
                            if (Inventory.stream().id(ItemList.MOLTEN_GLASS_1775).count() == 0) {
                                break;
                            }
                            timer = 0;
                        }
                    } else {
                        initialCount = currentCount;
                        timer = 0;
                    }
                    int randomSleep = Random.nextInt(2000, 2500);
                    Condition.sleep(randomSleep);
                }
            }
    }
}