package Woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

import java.util.List;

import Helpers.ItemList;
import Helpers.Task;
import Helpers.SkillData;
import script.mMain;

public class DoWoodcutting extends Task {
    @Override
    public boolean activate() {
        return !Inventory.isFull()
                &&( (SkillData.normalTreeLocation.contains(Players.local())
                || SkillData.oakTreeLocation.contains(Players.local())
                || SkillData.willowTreeLocation.contains(Players.local()) )
                && Players.local().animation() == -1
        );
    }
    @Override
    public void execute() {
        mMain.State = "Cutting trees";
        GameObject treeNormal = Objects.stream().within(6).id(SkillData.normalTreeID).nearest().first();
        GameObject treeOak = Objects.stream().within(6).id(SkillData.oakTreeID).nearest().first();
        GameObject treeWillow = Objects.stream().within(6).id(SkillData.willowTreeID).nearest().first();
        GameObject treeTeak = Objects.stream().within(6).id(SkillData.teakTreeID).nearest().first();

        //cut normal logs
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 14) {
            treeNormal.interact("Chop down", "Tree");
            Condition.wait(() -> Objects.stream().at(treeNormal.tile()).id(SkillData.normalTreeID).isEmpty(), 500, 50);
        }
        //Cut oak logs
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 15 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 30) {
            treeOak.interact("Chop down", "Oak");
            Condition.wait(() -> Objects.stream().at(treeOak.tile()).id(SkillData.oakTreeID).isEmpty(), 500, 50);
        }
        //Cut willow logs
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 30 && (Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 50)) {
            treeWillow.interact("Chop down", "Willow");
            Condition.wait(() -> Objects.stream().at(treeWillow.tile()).id(SkillData.willowTreeID).isEmpty(), 500, 50);
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
            Condition.wait(() -> Objects.stream().at(treeTeak.tile()).id(SkillData.willowTreeID).isEmpty(), 500, 50);
        }
    }
}