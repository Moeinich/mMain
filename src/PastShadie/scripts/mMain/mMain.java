package src.PastShadie.scripts.mMain;

import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.*;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.mobile.service.ScriptUploader;

import src.PastShadie.scripts.mMain.Combat.startCombat;
import src.PastShadie.scripts.mMain.Cooking.startCooking;
import src.PastShadie.scripts.mMain.Firemaking.startFiremaking;
import src.PastShadie.scripts.mMain.Fishing.startFishing;
import src.PastShadie.scripts.mMain.Mining.startMining;
import src.PastShadie.scripts.mMain.Progressive.startProgressive;
import src.PastShadie.scripts.mMain.Smithing.startSmithing;
import src.PastShadie.scripts.mMain.Woodcutting.startWoodcutting;

@ScriptManifest(
        name = "mMain",
        description = "Progressively levels different skills with an emphasis on ironman-mode",
        version = "0.0.1"
)
@ScriptConfiguration.List(
        {
                @ScriptConfiguration(
                        name =  "Skill",
                        description = "Which skill would you like to do?",
                        defaultValue = "Progressive",
                        allowedValues = {"Progressive", "Mining", "Combat", "Fishing", "Woodcutting", "Cooking", "Firemaking", "Smithing"},
                        optionType = OptionType.STRING
                )
        }
)

public class mMain extends AbstractScript {
    //%userprofile%\.powbot\android\platform-tools\adb.exe
    //adb devices
    //adb kill-server
    //adb.exe forward tcp:61666 tcp:61666

    public static void main(String[] args) {
        new ScriptUploader().uploadAndStart("mMain", "Account", "emulator-5554", true, false);
    }

    public static String scriptStatus;

    @Override
    public void onStart() {
        String skill = getOption("Skill");
        scriptStatus = "Starting...";

        Paint p = new PaintBuilder()
                .addString("Skill: " , () -> skill)
                .addString("Status: ", () -> scriptStatus)
                .trackSkill(Skill.Mining)
                .trackSkill(Skill.Defence)
                .trackSkill(Skill.Attack)
                .trackSkill(Skill.Strength)
                .trackSkill(Skill.Fishing)
                .trackSkill(Skill.Woodcutting)
                .trackSkill(Skill.Cooking)
                .trackSkill(Skill.Firemaking)
                .trackSkill(Skill.Smithing)
                .x(60)
                .y(100)
                .build();
        addPaint(p);
    }

    @Override
    public void poll() {
        String skill = getOption("Skill");

        if(skill.equals("Progressive")) {
            var startProgressive = new startProgressive();
            startProgressive.Progressive();
        }

        //Doing individual progressive skills!
        else if (skill.equals("Mining")) {
            var startMining = new startMining();
            startMining.Mining();
        } else if (skill.equals("Combat")) {
            var startCombat = new startCombat();
            startCombat.Combat();
        } else if (skill.equals("Fishing")) {
            var startFishing = new startFishing();
            startFishing.Fishing();
        } else if (skill.equals("Woodcutting")) {
            var startWoodcutting = new startWoodcutting();
            startWoodcutting.Woodcutting();
        } else if (skill.equals("Cooking")) {
            var startCooking = new startCooking();
            startCooking.Cooking();
        } else if (skill.equals("Firemaking")) {
            var startFiremaking = new startFiremaking();
            startFiremaking.Firemaking();
        } else if (skill.equals("Smithing")) {
            var startSmithing = new startSmithing();
            startSmithing.startSmithing();
        }
    }
}