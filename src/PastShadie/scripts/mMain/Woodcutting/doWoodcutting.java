package src.PastShadie.scripts.mMain.Woodcutting;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import src.PastShadie.scripts.mMain.Assets.Task;
import src.PastShadie.scripts.mMain.Assets.skillData;
import src.PastShadie.scripts.mMain.mMain;

public class doWoodcutting extends Task {
    @Override
    public boolean activate() {
        return !Inventory.isFull()
                &&( (skillData.normalTreeLocation.contains(Players.local())
                || skillData.oakTreeLocation.contains(Players.local())
                || skillData.willowTreeLocation.contains(Players.local()) )
        );
    }
    @Override
    public void execute() {
        mMain.scriptStatus = "Do woodcutting";
        int[] normalTreeID = {1276, 1278};
        int[] oakTreeID = {10820};
        int[] willowTreeID = {10819};
        GameObject treeNormal = Objects.stream().within(6).id(normalTreeID).nearest().first();
        GameObject treeOak = Objects.stream().within(6).id(oakTreeID).nearest().first();
        GameObject treeWillow = Objects.stream().within(6).id(willowTreeID).nearest().first();

        //cut normal logs
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 14 && Players.local().animation() == -1) {
                    treeNormal.interact("Chop down", "Tree");
                    Condition.wait(() -> Objects.stream().at(treeNormal.tile()).id(normalTreeID).isEmpty(), 150, 50);
        }

        //Cut oak logs
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) <= 14 && Skills.realLevel(Constants.SKILLS_WOODCUTTING) < 30 && Players.local().animation() == -1) {
            treeOak.interact("Chop down", "Oak");
            Condition.wait(() -> Objects.stream().at(treeOak.tile()).id(oakTreeID).isEmpty(), 150, 50);
        }

        //Cut willow logs
        if (Skills.realLevel(Constants.SKILLS_WOODCUTTING) >= 30 && Players.local().animation() == -1) {
            treeWillow.interact("Chop down", "Willow");
            Condition.wait(() -> Objects.stream().at(treeWillow.tile()).id(willowTreeID).isEmpty(), 150, 50);
        }
    }
}