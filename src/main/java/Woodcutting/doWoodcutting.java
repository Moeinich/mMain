package Woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

import java.util.List;

import Assets.ItemList;
import Assets.Task;
import Assets.skillData;
import script.mMain;

public class doWoodcutting extends Task {
    @Override
    public boolean activate() {
        return !Inventory.isFull()
                &&( (skillData.normalTreeLocation.contains(Players.local())
                || skillData.oakTreeLocation.contains(Players.local())
                || skillData.willowTreeLocation.contains(Players.local()) )
                && Players.local().animation() == -1
        );
    }
    @Override
    public void execute() {
        mMain.State = "Cutting trees";
        GameObject treeNormal = Objects.stream().within(6).id(skillData.normalTreeID).nearest().first();
        GameObject treeOak = Objects.stream().within(6).id(skillData.oakTreeID).nearest().first();
        GameObject treeWillow = Objects.stream().within(6).id(skillData.willowTreeID).nearest().first();
        GameObject treeTeak = Objects.stream().within(6).id(skillData.teakTreeID).nearest().first();

        //cut normal logs
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 14) {
            treeNormal.interact("Chop down", "Tree");
            Condition.wait(() -> Objects.stream().at(treeNormal.tile()).id(skillData.normalTreeID).isEmpty(), 500, 50);
        }
        //Cut oak logs
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 15 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 30) {
            treeOak.interact("Chop down", "Oak");
            Condition.wait(() -> Objects.stream().at(treeOak.tile()).id(skillData.oakTreeID).isEmpty(), 500, 50);
        }
        //Cut willow logs
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 30 && (Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 50)) {
            treeWillow.interact("Chop down", "Willow");
            Condition.wait(() -> Objects.stream().at(treeWillow.tile()).id(skillData.willowTreeID).isEmpty(), 500, 50);
        }
        //Cut Teaks
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 50) {
            if (Inventory.stream().id(ItemList.TEAK_LOGS_6333).count() >= 26) {
                while(Inventory.stream().id(ItemList.TEAK_LOGS_6333).count() >= 1) {
                    mMain.State = "dropping items";
                    List<Item> itemsToDrop = Inventory.stream().name("Teak logs").list();
                    Inventory.drop(itemsToDrop);
                    Condition.wait(() -> itemsToDrop.isEmpty(), 20, 50);
                }
            }
            treeTeak.interact("Chop down", "Teak");
            Condition.wait(() -> Objects.stream().at(treeTeak.tile()).id(skillData.willowTreeID).isEmpty(), 500, 50);
        }
    }
}