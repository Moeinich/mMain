package Fletching;

import org.powbot.api.Condition;
import org.powbot.api.Random;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Constants;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;
import org.powbot.api.rt4.Skills;
import org.powbot.api.rt4.Widgets;
import org.powbot.mobile.script.ScriptManager;

import java.util.concurrent.Callable;

import Assets.ItemList;
import Assets.Task;
import script.mMain;

public class DoLongbow extends Task {
    int timer = 0;
    int initialCount = (int) Inventory.stream().id(ItemList.LOGS_1511).count();


    @Override
    public boolean activate() {
        return Skills.realLevel(Constants.SKILLS_FLETCHING) >= 10 && Skills.realLevel(Constants.SKILLS_FLETCHING) < 20;
    }

    @Override
    public void execute() {
        if (Inventory.stream().id(ItemList.LOGS_1511).count() == 0) {
            mMain.State = "Banking loop";
            if (!Bank.opened() && Bank.inViewport()) {
                Bank.open();
            }
            if (Inventory.stream().name("Logs").count() == 0) {
                Bank.depositAllExcept("Knife");
            }
            if (Inventory.stream().name("Knife").count() == 0) {
                Bank.withdraw("Knife", 1);
            }
            if (Bank.stream().name("Logs").first().stackSize() < 27) {
                mMain.State = "We ran out of logs";
                mMain.taskRunning.set(false); //Skip task on progressive
            } else {
                Bank.withdraw("Logs", 27);
                Bank.close();
                Condition.wait(() -> !Bank.opened(), 200,50);
            }
        } else if (Game.tab(Game.Tab.INVENTORY)) {
            while (!ScriptManager.INSTANCE.isStopping()) {
                mMain.State = "Fletching loop";
                int currentCount = (int) Inventory.stream().id(ItemList.LOGS_1511).count();
                if (currentCount >= initialCount) {
                    timer += 2;
                    if (timer >= 2) {
                        Item Knife = Inventory.stream().name("Knife").first();
                        Item Logs = Inventory.stream().name("Logs").first();

                        if (Inventory.stream().id(ItemList.LOGS_1511).count() >= 1 && Game.tab(Game.Tab.INVENTORY)) {
                            if (ScriptManager.INSTANCE.isStopping()) {
                                ScriptManager.INSTANCE.stop();
                            }
                            if (Inventory.selectedItem().id() != Knife.id() && !Widgets.widget(270).valid()) {
                                Knife.interact("Use");
                                Condition.wait((Callable<Boolean>) () -> Inventory.selectedItem().id() == Knife.id(), 150, 20);
                            }
                            if (Inventory.selectedItem().id() == Knife.id()) {
                                Logs.interact("Use");
                                Condition.wait((Callable<Boolean>) () -> Widgets.widget(270).valid(), 500, 20);
                            }
                            if (Widgets.widget(270).valid()) {
                                Widgets.widget(270).component(14).click(); //Widget is 270, We don't know component yet!
                                Condition.wait((Callable<Boolean>) () -> !Widgets.widget(270).valid(), 150, 20);
                            }
                        }
                        if (Inventory.stream().id(ItemList.LOGS_1511).count() == 0) {
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